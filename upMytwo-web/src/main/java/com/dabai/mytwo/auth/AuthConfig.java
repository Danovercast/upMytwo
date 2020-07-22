package com.dabai.mytwo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>@author     ：dbo </p>
 * <p>@date       ：Created in 2020/7/22 13:12</p>
 * <p>@description：认证配置数据</p>
 * <p>@version:     1.0$</p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthConfig {
    /** 代表这个JWT的接收对象 */
    private String clientId;
    /** 密钥, 经过Base64加密 */
    private String base64Secret;
    /**  JWT的签发主体 */
    private String name;
    /** 过期时间，时间戳 */
    private Long expiresSecond;
}
