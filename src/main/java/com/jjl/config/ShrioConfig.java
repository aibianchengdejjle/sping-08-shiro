package com.jjl.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShrioConfig {
    //创建realm对象
    @Bean
    public UserReaim userReaim(){
        return new UserReaim();
    }
    //
    @Bean
    public DefaultWebSecurityManager getdfaultWebSecurityManager(@Qualifier("userReaim") UserReaim userReaim){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userReaim);
        return  securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("getdfaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /*
        *   anon  无需认证
        *   authc   必须认证才能
        *   user  必须记住我才可以
        *   perms  拥有对某个资源的权限才能
        *   role  拥有某个角色权限
        */
        Map<String,String> filterMap=new LinkedHashMap<>();
        //需要进行授权操作了 正常情况下跳转到未授权页面 而且具体的请求需要相应的请求
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/*","authc");
        filterMap.put("/toLogout","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //拦截成功后转移到登陆界面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauthc");

        return  shiroFilterFactoryBean;
    }
    @Bean
    public ShiroDialect getShiroDialect(){
        return   new ShiroDialect();
    }
}
