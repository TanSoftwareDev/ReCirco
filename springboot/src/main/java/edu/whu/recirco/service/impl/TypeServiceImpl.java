package edu.whu.recirco.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.whu.recirco.domain.Type;
import edu.whu.recirco.dao.TypeDao;
import edu.whu.recirco.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeDao, Type> implements ITypeService {
    /**
     * 新增
     */
    public void add(Type type) {
        this.baseMapper.insert(type);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.baseMapper.deleteById(id);
        }
    }

    /**
     * 修改
     *
     * @return
     */
    public boolean updateById(Type type) {
        this.baseMapper.updateById(type);
        return true;
    }

    /**
     * 根据ID查询
     */
    public Type selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Type> selectAll(Type type) {
        return this.baseMapper.selectAll(type);
    }

    /**
     * 分页查询
     */
    public PageInfo<Type> selectPage(Type type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = this.baseMapper.selectAll(type);
        return PageInfo.of(list);
    }

}
