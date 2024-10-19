package edu.whu.recirco.controller;


import edu.whu.recirco.domain.Account;
import edu.whu.recirco.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 账户 前端控制器
 * </p>
 *
 * @author susong
 * @since 2024-10-19
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    public IAccountService accountService;
    @GetMapping("/get/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id){
        Account account = accountService.getAccount(id);
        if(account == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(account);
    }

    @GetMapping("/get")
    public ResponseEntity<Account> getAccountByName(String name){
        Account account = accountService.getAccountByName(name);
        if(account == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(account);
    }

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        Account usr = accountService.addAccount(account);
        if(usr == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(usr);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateAccount(@RequestBody Account account){
        if(accountService.updateAccount(account))
            return ResponseEntity.ok().build();
        else
            //修改失败，不存在对应id的用户
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id){
        if(accountService.removeAccount(id))
            return ResponseEntity.ok().build();
        else
            //删除失败，不存在对应id的用户
            return ResponseEntity.noContent().build();
    }
}

