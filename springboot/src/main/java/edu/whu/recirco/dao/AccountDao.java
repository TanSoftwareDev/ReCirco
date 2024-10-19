package edu.whu.recirco.dao;

import edu.whu.recirco.domain.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.recirco.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 账户 Mapper 接口
 * </p>
 *
 * @author susong
 * @since 2024-10-19
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {
    @Select("select * from account where username = #{username}")
    Account selectByUsername(String username);
}
