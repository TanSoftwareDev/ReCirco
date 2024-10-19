package edu.whu.recirco.security;


import edu.whu.recirco.domain.Account;
import edu.whu.recirco.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import edu.whu.recirco.common.Result;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping
public class AuthenticationController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private DbUserDetailService userDetailsService;
    @Autowired
    private IAccountService accountService;


    // 根据User生成保存结果的Map类
    private void fillMapResult(Map<String, String> result, Account user, String token) {
        System.out.print(user);
        if (user != null) {
            result.put("id", String.valueOf(user.getId()));
            result.put("username", user.getUsername() != null ? user.getUsername() : "default_username");
            result.put("name", user.getName() != null ? user.getName() : "default_name");
            result.put("email", user.getEmail() != null ? user.getEmail() : "default_email");
            result.put("password", user.getPassword() != null ? user.getPassword() : "default_password");
            result.put("role", user.getRole() != null ? user.getRole() : "default_role");
        }
        result.put("token", token); // 假设 token 一定不为空
    }

    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Account myuser) {
        Map<String,String> result = new HashMap<>();
        try {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(myuser.getUsername());
            if (passwordEncoder.matches(myuser.getPassword(), userDetails.getPassword()) && userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_" + myuser.getRole()))) {
                final String token = jwtTokenUtil.generateToken(userDetails);
                Account user = accountService.getAccountByName(myuser.getUsername());
                fillMapResult(result, user, token);
                return ResponseEntity.ok(result);
            } else {
                result.put("error","用户认证未通过");
                return ResponseEntity.badRequest().body(result);
            }
        }catch (UsernameNotFoundException e){
            result.put("error","用户不存在");
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody Account myuser) {
        Map<String,String> result = new HashMap<>();
        // 检查用户名是否存在
        if (userDetailsService.isUserExists(myuser.getUsername())) {
            result.put("error","用户名已存在");
            return ResponseEntity.badRequest().body(result);
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(myuser.getUsername())
                .password(myuser.getPassword())
                .roles(myuser.getRole())
                .build();
        final String token = jwtTokenUtil.generateToken(userDetails);
        String message = checkAndSave(myuser);
        if(!Objects.equals(message, "注册成功")){
            result.put("error",message);
            return ResponseEntity.badRequest().body(result);
        }
        Account user = accountService.getAccountByName(myuser.getUsername());
        fillMapResult(result, user, token);
        return ResponseEntity.ok(result);

    }

    // 检查密码强度并保存到数据库
    private String checkAndSave(Account user) {
        // 验证密码强度
        String passwordErrorMessage = userDetailsService.getPasswordStrengthErrorMessage(user.getPassword());
        if (passwordErrorMessage != null) {
            return passwordErrorMessage;
        }
        // 将新用户保存到数据库中
        userDetailsService.saveUser(user);
        // 返回注册成功的响应
        return "注册成功";
    }
}