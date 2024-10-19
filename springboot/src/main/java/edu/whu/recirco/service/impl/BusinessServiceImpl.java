package edu.whu.recirco.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.whu.recirco.common.Constants;
import edu.whu.recirco.common.enums.ResultCodeEnum;
import edu.whu.recirco.common.enums.RoleEnum;
import edu.whu.recirco.domain.Business;
import edu.whu.recirco.dao.BusinessDao;
import edu.whu.recirco.exception.CustomException;
import edu.whu.recirco.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商家信息表 服务实现类
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessDao, Business> implements IBusinessService {
   
    
/**
 * 新增
 */

    public void add(Business business) {
        Business dbBusiness = this.baseMapper.selectByUsername(business.getUsername());
        if (ObjectUtil.isNotNull(dbBusiness)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(business.getPassword())) {
            business.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(business.getName())) {
            business.setName(business.getUsername());
        }
        business.setRole(RoleEnum.BUSINESS.name());
        this.baseMapper.insert(business);
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

    public boolean updateById(Business business) {
        this.baseMapper.updateById(business);
        return true;
    }

/**
 * 根据ID查询
 */

    public Business selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

/**
 * 查询所有
 */

    public List<Business> selectAll(Business business) {
        return this.baseMapper.selectAll(business);
    }

/**
 * 分页查询
 */

    public PageInfo<Business> selectPage(Business business, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Business> list = this.baseMapper.selectAll(business);
        return PageInfo.of(list);
    }


/**
 * 登录
 *//*

         */
/*public Account login(Account account) {
        Account dbBusiness = this.baseMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbBusiness)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbBusiness.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbBusiness.getId() + "-" + RoleEnum.BUSINESS.name();
        String token = JwtTokenUtil.generateToken(tokenData, dbBusiness.getPassword());
        dbBusiness.setToken(token);
        return dbBusiness;
    }
*//*

         */
/**
 * 注册
 *//*

    public void register(Account account) {
        Business business = new Business();
        BeanUtils.copyProperties(account, business);
        add(business);
    }

    */
/**
 * 修改密码
 *//*

    public void updatePassword(Account account) {
        Business dbBusiness = this.baseMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbBusiness)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbBusiness.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbBusiness.setPassword(account.getNewPassword());
        this.baseMapper.updateById(dbBusiness);
    }
*/
}
