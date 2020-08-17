package com.dabai.mytwo;

import com.dabai.mytwo.auth.JwtAuthFilter;
import com.dabai.mytwo.auth.JwtInfoFilter;
import com.dabai.mytwo.auth.MytwoRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>@author     ：dbo </p>
 * <p>@date       ：Created in 2020/7/23 13:25</p>
 * <p>@description：shior权限控制器配置</p>
 * <p>@version:     1.0</p>
 */
@Configuration
@Slf4j
public class ShiroFilterBeanConfig {
    @Bean
    public ShiroFilterFactoryBean giveaShiroFilterFactoryBean(@Autowired SecurityManager securityManager){
        ShiroFilterFactoryBean sffb=new ShiroFilterFactoryBean();
        sffb.setSecurityManager(securityManager);
        sffb.setLoginUrl("/html/login.html");
        sffb.setSuccessUrl("/index.html");
        sffb.setUnauthorizedUrl("/html/error.html");
        Map<String,String> map=new LinkedHashMap<>();
        map.put("/pagePic/**", "anon");
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/html/**", "anon");
        map.put("/_js/**", "anon");
        map.put("/_css/**", "anon");
        map.put("/vendors/**", "anon");
        map.put("/ins/**", "anon");
        map.put("/logout/**", "logout");
        map.put("/user/**", "anon");
        map.put("/manager/**", "perms[\"manager\"]");
        map.put("/jwt/**", "jwtAuth,jwtInfo,perms[\"normaluser\"]");
        map.put("/**", "anon");
        sffb.setFilterChainDefinitionMap(map);
        Map<String, Filter>filterMap=new LinkedHashMap<>(2);
        filterMap.put("jwtAuth",new JwtAuthFilter());
        filterMap.put("jwtInfo",new JwtInfoFilter());
        sffb.setFilters(filterMap);
        return sffb;
    }

    @Bean
    public SecurityManager giveaSecurityManager(MytwoRealm realm, @Qualifier("cookieRememberMeManager") CookieRememberMeManager cookieRememberMeManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setRememberMeManager(cookieRememberMeManager);
        return securityManager;
    }
    @Bean("mytwoRealm")
    public MytwoRealm giveaMytwoRealm(@Autowired HashedCredentialsMatcher matcher){
        MytwoRealm mytwoRealm=new MytwoRealm();
        mytwoRealm.setCredentialsMatcher(matcher);
        return mytwoRealm;
    }

    /**
     * 记住我cookie
     * @return SimpleCookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        log.info("rememberMeCookie()");
        // 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie 管理器
     * @return CookieRememberMeManager
     */
    @Bean(name = "cookieRememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * 散列算法
     * @return HashedCredentialsMatcher
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        log.info("hashedCredentialsMatcher()");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于
        // md5(md5(""));
        return hashedCredentialsMatcher;
    }
    /**
     *  注解配置
     *  <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
     *           depends-on="lifecycleBeanPostProcessor">
     *         <property name="proxyTargetClass" value="true"></property>
     *     </bean>
     *     <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
     *         <property name="securityManager" ref="securityManager"/>
     *     </bean>
     *

     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持; Controller才能使用@RequiresPermissions
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired SecurityManager securityManager) {
        log.info("authorizationAttributeSourceAdvisor()");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}
