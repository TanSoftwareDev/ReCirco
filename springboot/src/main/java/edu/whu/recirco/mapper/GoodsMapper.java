package edu.whu.recirco.mapper;

import edu.whu.recirco.entity.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsMapper {

    /**
     * 新增
     */
    int insert(Goods goods);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Goods goods);

    /**
     * 根据ID查询
     */
    Goods selectById(Integer id);

    @Select("select * from goods where name like concat('%',#{name},'%')")
    List<Goods> selectByName(String name);
    /**
     * 查询所有
     */
    List<Goods> selectAll(Goods goods);

}
