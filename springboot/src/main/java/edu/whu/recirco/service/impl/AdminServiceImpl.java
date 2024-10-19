package edu.whu.recirco.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.whu.recirco.common.Constants;
import edu.whu.recirco.common.enums.ResultCodeEnum;
import edu.whu.recirco.common.enums.RoleEnum;
import edu.whu.recirco.domain.Account;
import edu.whu.recirco.domain.Admin;
import edu.whu.recirco.dao.AdminDao;
import edu.whu.recirco.exception.CustomException;
import edu.whu.recirco.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements IAdminService {
    /**
     * 新增
     */
    public void add(Admin admin) {
        LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Account::getUsername,admin.getUsername());
        Admin dbAdmin = this.baseMapper.selectByUsername(admin.getUsername());
        if (ObjectUtil.isNotNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            admin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        admin.setRole(RoleEnum.ADMIN.name());
        this.baseMapper.insert(admin);
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
    public boolean updateById(Admin admin) {
        this.baseMapper.updateById(admin);
        return true;
    }

    /**
     * 根据ID查询
     */
    public Admin selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Admin> selectAll() {
        return this.baseMapper.selectList();
    }

    /**
     * 分页查询
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = this.baseMapper.selectList();
        return PageInfo.of(list);
    }

}
