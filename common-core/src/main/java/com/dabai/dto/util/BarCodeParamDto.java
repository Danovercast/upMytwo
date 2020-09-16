package com.dabai.dto.util;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Pfatman
 * Date: 2020/8/25
 * Time: 10:47
 * Description: BarCodeParamDto
 */
public class BarCodeParamDto {
    private String msg = "12345678"; // 二维码编码内容
    private String type = "code128"; // 编码类型
    private String height; // 生成图片高度
    private Map<EncodeHintType, Object> hints; // 设置参数
    private String logourl; // Logo图片路径
    private String fmt = "jpeg"; // 图片输出格式，默认jpeg
    private String mw;// 条形码
    private String wf;// 宽度因子
    private String qz; // 条形码两边的空白区域
    private String hrp; // 条形码显示的位置 ，默认显示在底部 none top bottom
    private String res; // 图像分辨率（位图）,默认300 （dpi） ，不能超过2400
    private String gray; // 灰度或黑白图像，默认false
    private String hrsize; // 显示的条码内容，字体大小 默认 8pt ,需要带上单位
    private String hrfont; // 条形码字体
    private String hrpattern; // 格式化条形码字体，每一个"_"代码表条形码的一个码。如\_patterned\_:__/__/____，这个格式化12345678
    // 将会把条形码格式化为“_patterned_:12/34/5678”

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 设置参数
     *
     * @return hints 设置参数
     */
    public Map<EncodeHintType, Object> getHints() {
        if (null == hints) {
            hints = getDecodeHintType();
        }
        return hints;
    }

    /**
     * 设置 设置参数
     *
     * @param hints
     *            设置参数
     */
    public void setHints(Map<EncodeHintType, Object> hints) {
        this.hints = hints;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getHeight() {
        // 如果是二维码默认 200像素
        if (StringUtils.isBlank(height) && "QR".equalsIgnoreCase(type)) {// 高度为设置情况下
            height = "200";
        }
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMw() {
        if (StringUtils.isBlank(mw) && "QR".equalsIgnoreCase(type)) {// 高度为设置情况下
            mw = "200";
        }
        return mw;
    }

    public void setMw(String mw) {
        this.mw = mw;
    }

    public String getWf() {
        return wf;
    }

    public void setWf(String wf) {
        this.wf = wf;
    }

    public String getQz() {
        return qz;
    }

    public void setQz(String qz) {
        this.qz = qz;
    }

    public String getHrp() {
        return hrp;
    }

    public void setHrp(String hrp) {
        this.hrp = hrp;
    }

    public String getFmt() {
        return fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getGray() {
        return gray;
    }

    public void setGray(String gray) {
        this.gray = gray;
    }

    public String getHrsize() {
        return hrsize;
    }

    public void setHrsize(String hrsize) {
        this.hrsize = hrsize;
    }

    public String getHrfont() {
        return hrfont;
    }

    public void setHrfont(String hrfont) {
        this.hrfont = hrfont;
    }

    public String getHrpattern() {
        return hrpattern;
    }

    public void setHrpattern(String hrpattern) {
        this.hrpattern = hrpattern;
    }

    private Map<EncodeHintType, Object> getDecodeHintType() {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 400);
        hints.put(EncodeHintType.MIN_SIZE, 50);

        return hints;
    }
}

