package com.dabai.mytwo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;

/**
 * <p>@author     ：dbo </p>
 * <p>@date       ：Created in 2020/7/22 17:59</p>
 * <p>@description：webconfig配置类</p>
 * <p>@version:     1.0$</p>
 */
public class WebConfig// implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
{
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    //设置web静态资源目录
    @Value("${static-web.dir}")
    private File webDir;
    public void customize(ConfigurableServletWebServerFactory factory) {
        if (!webDir.exists()) {
            if (!webDir.mkdir()) {
                logger.debug("webdir already exists :----"+webDir+"---- finished");
            }else{
                logger.debug("webdir created :----"+webDir+"---- finished");
            }
        }
        factory.setDocumentRoot(webDir);
    }
}
