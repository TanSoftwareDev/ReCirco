package edu.whu.recirco.dao;

import edu.whu.recirco.domain.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商家信息表 Mapper 接口
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Mapper
public interface BusinessDao extends BaseMapper<Business> {
    @Select("select * from business where username = #{username}")
    Business selectByUsername(String username);

    List<Business> selectAll(Business business);
}
