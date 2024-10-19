package edu.whu.recirco.service;

import edu.whu.recirco.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * <p>
 * 账户 服务类
 * </p>
 *
 * @author susong
 * @since 2024-10-19
 */
public interface IAccountService extends IService<Account> {
    //添加账户
    public Account addAccount(Account myAccount);
    //根据id删除账户
    public boolean removeAccount(int id);
    //根据id查询账户
    public Account getAccount(int id);
    //更新账户信息
    public boolean updateAccount(Account account);
    public Account getAccountByName(String username);
}
