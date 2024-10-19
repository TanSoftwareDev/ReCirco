package edu.whu.recirco.dao;

import edu.whu.recirco.domain.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Mapper
public interface AdminDao extends BaseMapper<Admin> {
    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);

    List<Admin> selectList();
}
