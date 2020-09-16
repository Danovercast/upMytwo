package com.dabai.dto.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.output.eps.EPSCanvasProvider;
import org.krysalis.barcode4j.output.svg.SVGCanvasProvider;
import org.krysalis.barcode4j.tools.MimeTypes;
import org.apache.avalon.framework.configuration.Configuration;

import javax.imageio.ImageIO;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * User: Pfatman
 * Date: 2020/8/25
 * Time: 10:52
 * Description: BarCodeUtil
 */
public class BarCodeUtil {


    // 工具类不可创建 私有化构造函数
    private BarCodeUtil() {

    }

    /**
     *
     * <p>
     * <b>功能描述和使用场景:</b>
     * </p>
     * 创建二维码图片对象
     * <p>
     * <b>实现流程:</b>
     * </p>
     * <p>
     * 1、将二维码内容编码图像数据
     * </p>
     * <p>
     * 2、把二维码数据写入创建的RGB类型的图像对象中
     * </p>
     * </br>
     *
     * @param contents
     *            二维码内容
     * @param width
     *            图像 宽度
     * @param height
     *            图像高
     * @param encodeHintType
     *            二维码参数设置
     * @return image 返回生成的图像对象
     * @throws WriterException
     */
    private static BufferedImage createQREncode(String contents, int width, int height,
                                                Map<EncodeHintType, Object> encodeHintType) throws WriterException {
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix matrix = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, encodeHintType);
        int w = matrix.getWidth();
        int h = matrix.getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        return image;
    }

    /**
     *
     * <p>
     * <b>功能描述和使用场景:</b>
     * </p>
     * 获取网络地址的的图片对象
     * <p>
     * <b>实现流程:</b>
     * </p>
     * </br>
     *
     * @param urldest
     *            网络地址
     * @return bi1 返回生成的图像对象
     * @throws IOException
     */
    private static BufferedImage getUrlImage(String urldest) throws IOException {

        if (!StringUtils.isBlank(urldest) && urldest.startsWith("http")) {// 如果是http请求
            URL url = new URL(urldest);
            return ImageIO.read(url);
        } else {// 本地路径直接读取文件
            return ImageIO.read(new File(urldest));
        }
    }

    /**
     * <p>
     * <b>功能描述和使用场景:</b>
     * </p>
     * 设置二维码图标对象
     * <p>
     * <b>实现流程:</b>
     * </p>
     * </br>
     *
     * @param codeImage
     *            二维码图片对象
     * @param logoImage
     *            二维码图标对象
     * @throws IOException
     */
    private static void setCodeLogo(BufferedImage codeImage, BufferedImage logoImage) throws IOException {
        Graphics2D gs = codeImage.createGraphics();
        int widthLogo = logoImage.getWidth(null) > codeImage.getWidth() * 2 / 10 ? (codeImage.getWidth() * 2 / 10)
                : logoImage.getWidth(null),
                heightLogo = logoImage.getHeight(null) > codeImage.getHeight() * 2 / 10
                        ? (codeImage.getHeight() * 2 / 10) : logoImage.getWidth(null);
        // 设置图标
        int x = (codeImage.getWidth() - widthLogo) / 2;
        int y = (codeImage.getHeight() - heightLogo) / 2;
        // 设置图标显示
        gs.drawImage(logoImage, x, y, widthLogo, heightLogo, null);
        gs.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
        gs.setStroke(new BasicStroke(2));
        gs.setColor(Color.WHITE);
        gs.drawRect(x, y, widthLogo, heightLogo);

        gs.dispose();
        codeImage.flush();
    }

    /**
     *
     * <p>
     * <b>功能描述和使用场景:</b>
     * </p>
     * 通过初始化条码参数类，获得条码BufferedImage
     * <p>
     * <b>实现流程:</b>
     * </p>
     * </br>
     *
     * @param barCodeParam
     *            条码参数VO
     * @return BufferedImage 图像对象
     * @throws Exception
     *             BufferedImage 结果VO，其内容描述如下：
     *
     *             <pre>
     */
    public static BufferedImage getCodeBufferedImage(BarCodeParamDto barCodeParam) throws Exception {
        BufferedImage image = null;
        // 区分类型调用不通的条码生产方法
        if ("QR".equalsIgnoreCase(barCodeParam.getType())) {
            image = createQREncode(barCodeParam.getMsg(), Integer.valueOf(barCodeParam.getMw()),
                    Integer.valueOf(barCodeParam.getHeight()), barCodeParam.getHints());
            if (!StringUtils.isBlank(barCodeParam.getLogourl())) { // 如果存在图标路径则设置图标
                BufferedImage logoImage = getUrlImage(barCodeParam.getLogourl());
                setCodeLogo(image, logoImage); // 设置图标
            }
        } else {
            image = createBarEncode(barCodeParam);
        }
        return image;
    }

    /**
     *
     * <p>
     * <b>功能描述和使用场景:</b>
     * </p>
     * 通过参数对象创建 条形条码码的BufferedImage
     * <p>
     * <b>实现流程:</b>
     * </p>
     * </br>
     *
     * @param barCodeParam
     * @return BufferedImage 图像对象
     * @throws Exception
     */
    private static BufferedImage createBarEncode(BarCodeParamDto barCodeParam) throws Exception {
        // 设置生产的格式
        String format = MimeTypes.expandFormat(barCodeParam.getFmt());
        if (format == null) {
            format = MimeTypes.MIME_JPEG;
        }
        int orientation = 0;
        // 通过参数对象创建条形码的配置对象
        Configuration cfg = buildCfg(barCodeParam);
        // 编码的内容
        String msg = barCodeParam.getMsg();
        BarcodeUtil util = BarcodeUtil.getInstance();
        BarcodeGenerator gen = util.createBarcodeGenerator(cfg);
        // 创建编码输出流
        ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);

        if (format.equals(MimeTypes.MIME_SVG)) {
            // Create Barcode and render it to SVG
            SVGCanvasProvider svg = new SVGCanvasProvider(false, orientation);
            gen.generateBarcode(svg, msg);
            org.w3c.dom.DocumentFragment frag = svg.getDOMFragment();
            // Serialize SVG barcode
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer trans = factory.newTransformer();
            Source src = new javax.xml.transform.dom.DOMSource(frag);
            Result res = new javax.xml.transform.stream.StreamResult(bout);
            trans.transform(src, res);
        } else if (format.equals(MimeTypes.MIME_EPS)) {
            EPSCanvasProvider eps = new EPSCanvasProvider(bout, orientation);
            gen.generateBarcode(eps, msg);
            eps.finish();
        } else {
            String resText = barCodeParam.getRes();
            int resolution = 300; // dpi
            if (resText != null) {
                resolution = Integer.parseInt(resText);
            }
            if (resolution > 2400) {
                throw new IllegalArgumentException("res的值不能超过2400");
            }
            if (resolution < 10) {
                throw new IllegalArgumentException("res的值不能小于10");
            }
            String gray = barCodeParam.getGray();
            BitmapCanvasProvider bitmap = ("true".equalsIgnoreCase(gray)
                    ? new BitmapCanvasProvider(bout, format, resolution, BufferedImage.TYPE_BYTE_GRAY, true,
                    orientation)
                    : new BitmapCanvasProvider(bout, format, resolution, BufferedImage.TYPE_BYTE_BINARY, false,
                    orientation));
            gen.generateBarcode(bitmap, msg);
            bitmap.finish();
        }
        // z组织返回 BufferedImage 对象
        ByteArrayInputStream in = new ByteArrayInputStream(bout.toByteArray());
        BufferedImage image = ImageIO.read(in);
        in.close();
        bout.close();
        return image;
    }

    /**
     *
     * <p>
     * <b>功能描述和使用场景:</b>
     * </p>
     * 通过参数对象创建 配置对象
     * <p>
     * <b>实现流程:</b>
     * </p>
     * </br>
     *
     * @param barCodeParam
     * @return Configuration 结果VO，其内容描述如下： 配置提供创建条形码使用
     *
     *         <pre>
     */
    private static Configuration buildCfg(BarCodeParamDto barCodeParam) {
        DefaultConfiguration cfg = new DefaultConfiguration("barcode");
        // 设置类型
        String type = barCodeParam.getType();
        DefaultConfiguration child = new DefaultConfiguration(type);
        cfg.addChild(child);
        // Get additional attributes
        DefaultConfiguration attr;
        String height = barCodeParam.getHeight();
        if (height != null) {
            attr = new DefaultConfiguration("height");
            attr.setValue(height);
            child.addChild(attr);
        }
        String moduleWidth = barCodeParam.getMw();
        if (moduleWidth != null) {
            attr = new DefaultConfiguration("module-width");
            attr.setValue(moduleWidth);
            child.addChild(attr);
        }
        String wideFactor = barCodeParam.getWf();
        if (wideFactor != null) {
            attr = new DefaultConfiguration("wide-factor");
            attr.setValue(wideFactor);
            child.addChild(attr);
        }
        String quietZone = barCodeParam.getQz();
        if (quietZone != null) {
            attr = new DefaultConfiguration("quiet-zone");
            if (quietZone.startsWith("disable")) {
                attr.setAttribute("enabled", "false");
            } else {
                attr.setValue(quietZone);
            }
            child.addChild(attr);
        }
        // 字体设置
        String humanReadablePosition = barCodeParam.getHrp();
        String pattern = barCodeParam.getHrpattern();
        String humanReadableSize = barCodeParam.getHrsize();
        String humanReadableFont = barCodeParam.getHrfont();

        if (!((humanReadablePosition == null) && (pattern == null) && (humanReadableSize == null)
                && (humanReadableFont == null))) {
            attr = new DefaultConfiguration("human-readable");

            DefaultConfiguration subAttr;
            if (pattern != null) {
                subAttr = new DefaultConfiguration("pattern");
                subAttr.setValue(pattern);
                attr.addChild(subAttr);
            }
            if (humanReadableSize != null) {
                subAttr = new DefaultConfiguration("font-size");
                subAttr.setValue(humanReadableSize);
                attr.addChild(subAttr);
            }
            if (humanReadableFont != null) {
                subAttr = new DefaultConfiguration("font-name");
                subAttr.setValue(humanReadableFont);
                attr.addChild(subAttr);
            }
            if (humanReadablePosition != null) {
                subAttr = new DefaultConfiguration("placement");
                subAttr.setValue(humanReadablePosition);
                attr.addChild(subAttr);
            }

            child.addChild(attr);
        }

        return cfg;
    }

    /**
     *
     * <p><b>功能描述和使用场景:</b></p>
     * 解析条码码二维码的的图片
     * <p><b>实现流程:</b></p>
     *      </br>
     * @param image   条形码二维码图片对象
     * @return  解析结果
     * @throws IOException
     * @throws NotFoundException
     * @throws ChecksumException
     * @throws FormatException
     * com.google.zxing.Result 结果VO，其内容描述如下：
     * <pre>
     */
    public static com.google.zxing.Result decode(BufferedImage image)
            throws IOException, NotFoundException, ChecksumException, FormatException {
        MultiFormatReader reader = new MultiFormatReader();
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);
        com.google.zxing.Result result = reader.decode(imageBinaryBitmap);
        return result;
    }

    public static void main(String[] args) throws Exception {
        // 实例化参数对象
        BarCodeParamDto barCodeParam = new BarCodeParamDto();
        barCodeParam.setMsg("http://www.baidu.com");
        // barCodeParam.setType("QR");
        BufferedImage image = BarCodeUtil.getCodeBufferedImage(barCodeParam);
        // 写到本地
        ImageIO.write(image, "png", new File("bar.png"));

        // 解析图片
        BufferedImage imagePare = ImageIO.read(new File("bar.png"));
        com.google.zxing.Result result = BarCodeUtil.decode(imagePare);
        System.out.println("resultFormat = " + result.getBarcodeFormat());
        System.out.println("resultText =  " + result.getText());

    }

}

