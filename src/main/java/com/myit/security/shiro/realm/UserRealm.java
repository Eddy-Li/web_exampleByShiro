package com.myit.security.shiro.realm;

import com.myit.dao.PermissionDAO;
import com.myit.dao.RoleDAO;
import com.myit.dao.UserDAO;
import com.myit.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserRealm extends AuthorizingRealm {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private PermissionDAO permissionDAO;

    @Override
    public String getName() {
        return "UserRealm";
    }

    //认证操作
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中获取登录的用户名， 查询数据库返回用户信息
        String username = (String) token.getPrincipal();
        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
//                ByteSource.Util.bytes(user.getUsername()),
//                getName());
        return simpleAuthenticationInfo;
    }


    //授权操作
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //就是SimpleAuthenticationInfo的第一个参数(上面doGetAuthenticationInfo方法中)
        User user = (User) principals.getPrimaryPrincipal();

        List<String> permissions = new ArrayList<String>();
        List<String> roles = new ArrayList<>();

        if ("admin".equals(user.getUsername())) {
            //拥有所有权限
            permissions.add("*:*");
            //查询所有角色
            roles = roleDAO.getAllRoleSn();
        } else {
            //根据用户id查询该用户所具有的角色
            roles = roleDAO.getRoleSnByUserId(user.getId());
            //根据用户id查询该用户所具有的权限
            permissions = permissionDAO.getPermissionResourceByUserId(user.getId());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }

    //清除缓存
    public void clearCached() {
        //获取当前等的用户凭证，然后清除
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


}
