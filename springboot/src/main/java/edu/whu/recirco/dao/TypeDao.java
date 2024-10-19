package edu.whu.recirco.dao;

import edu.whu.recirco.domain.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品分类表 Mapper 接口
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Mapper
public interface TypeDao extends BaseMapper<Type> {

    List<Type> selectAll(Type type);
}
