package com.dabai.dto.jyjs;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * User: dbo
 * Date: 2020/9/7
 * Time: 15:51
 * Description: SdsxfSqdSubmitDto
 */
@Data
public class SdsxfSqdSubmitDto implements Serializable {
    private static final long serialVersionUID = -3181153512034544936L;
    //结办单明细id
    private List<String> jbdMxId;
    //银行账户
    private String yhzh;
    //联系人
    private String lxr;
    //联系电话
    private String lxdh;
    //是否确认无疑 Y/N
    private String confirm;
    //依据、联系方式
    private String yjLxfs;
    //登记序号
    private String djxh;
    //纳税人识别号
    private String nsrsbh;
    //纳税人名称
    private String nsrmc;
    //主管税务所(科、分局)代码
    private String zgswskfjDm;
}
