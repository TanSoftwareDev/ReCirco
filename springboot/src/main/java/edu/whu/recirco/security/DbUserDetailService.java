package edu.whu.recirco.security;

import edu.whu.recirco.domain.Account;
import edu.whu.recirco.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 创建一个UserDetailsService的Bean，从数据库中读取用户和权限信息
 */
@Service
public class DbUserDetailService implements UserDetailsService {

    @Autowired
    IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getAccountByName(username);
        if (account == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        return User.builder()
                .username(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
   
    //检查用户是否存在
    public boolean isUserExists(String username) {
        Account user = accountService.getAccountByName(username);
        return user != null;
    }
    //检查密码强度
    public String getPasswordStrengthErrorMessage(String password) {
        // 密码最小长度
        int minLength = 6;
        // 密码包含至少一个大写字母
        boolean requiresUpperCase = true;
        // 密码包含至少一个小写字母
        boolean requiresLowerCase = true;
        // 密码包含至少一个数字
        boolean requiresDigit = true;
        // 密码包含至少一个特殊字符
        boolean requiresSpecialChar = true;
        // 验证密码长度
        if (password.length() < minLength) {
            return "密码长度不能少于 " + minLength + " 个字符";
        }
        // 验证密码是否包含大写字母
        if (requiresUpperCase && !password.matches(".*[A-Z].*")) {
            return "密码必须包含至少一个大写字母";
        }
        // 验证密码是否包含小写字母
        if (requiresLowerCase && !password.matches(".*[a-z].*")) {
            return "密码必须包含至少一个小写字母";
        }
        // 验证密码是否包含数字
        if (requiresDigit && !password.matches(".*\\d.*")) {
            return "密码必须包含至少一个数字";
        }
        // 验证密码是否包含特殊字符（非字母和数字）
        if (requiresSpecialChar && !password.matches(".*[^a-zA-Z0-9].*")) {
            return "密码必须包含至少一个特殊字符";
        }
        // 密码强度符合要求
        return null;
    }
    //保存用户信息
    public void saveUser(Account user){
        accountService.addAccount(user);
    }

}
