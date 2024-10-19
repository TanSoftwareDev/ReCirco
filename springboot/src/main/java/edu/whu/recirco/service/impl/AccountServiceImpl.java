package edu.whu.recirco.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.whu.recirco.common.enums.RoleEnum;
import edu.whu.recirco.domain.Account;
import edu.whu.recirco.dao.AccountDao;
import edu.whu.recirco.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 账户 服务实现类
 * </p>
 *
 * @author susong
 * @since 2024-10-19
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements IAccountService {

    @Override
    public Account addAccount(Account myAccount) {
        myAccount.setPassword(new BCryptPasswordEncoder().encode(myAccount.getPassword()));
        this.baseMapper.insert(myAccount);
        /*if(myAccount.getRole().equals(RoleEnum.ADMIN.name())){
            Admin admin = new Admin();
            admin.setName(myAccount.getUsername());
            admin.setPassword(myAccount.getPassword());
            admin.setRole(myAccount.getRole());
            admin.setAvatar(myAccount.getAvatar());
            admin.setEmail(myAccount.getEmail());
            admin.setPhone(myAccount.getPhone());
            admin.setUsername(myAccount.getUsername());
            adminService.add(admin);
        }
        else if(myAccount.getRole().equals(RoleEnum.BUSINESS.name())){
            Business business = new Business();
            business.setName(myAccount.getUsername());
            business.setPassword(myAccount.getPassword());
            business.setRole(myAccount.getRole());
            business.setAvatar(myAccount.getAvatar());
            business.setEmail(myAccount.getEmail());
            business.setPhone(myAccount.getPhone());
            business.setUsername(myAccount.getUsername());
            businessService.add(business);
        }*/
        return myAccount;
    }

    @Override
    public boolean removeAccount(int id) {
        this.baseMapper.deleteById(id);
        return true;
    }

    @Override
    public Account getAccount(int id) {
        Account account = this.baseMapper.selectById(id);
        return account;
    }

    @Override
    public boolean updateAccount(Account account) {
        this.baseMapper.updateById(account);
        return true;
    }

    @Override
    public Account getAccountByName(String username) {

        return this.baseMapper.selectByUsername(username);
    }
}
