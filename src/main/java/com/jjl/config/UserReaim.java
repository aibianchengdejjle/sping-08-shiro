package com.jjl.config;

import com.jjl.mapper.UserMapper;
import com.jjl.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserReaim extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        //可以给用户授予权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //用来放置session
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        info.addStringPermission(user.getPerms());
        return info;
    }
    //和认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user = userMapper.queryByName(Integer.parseInt(userToken.getUsername()));
        if(user==null){
            return  null;
        }
        //密码认证 你不用写 shiro自己写   这里我们把user对象传回到subject中
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
