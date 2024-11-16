package edu.whu.recirco.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.whu.recirco.common.enums.RoleEnum;
import edu.whu.recirco.entity.Account;
import edu.whu.recirco.entity.Address;
import edu.whu.recirco.mapper.AddressMapper;
import edu.whu.recirco.utils.TokenUtils;
import org.apache.el.parser.Token;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地址业务处理
 **/
@Service
public class AddressService {

    @Resource
    private AddressMapper addressMapper;

    /**
     * 新增
     */
    public void add(Address address) {
        addressMapper.insert(address);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        addressMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            addressMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Address address) {
        addressMapper.updateById(address);
    }

    /**
     * 根据ID查询
     */
    public Address selectById(Integer id) {
        return addressMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Address> selectAll(Address address) {
        //动态的sql语句 提前先把userid设置好，然后只查询本用户的ID
        Account currentUser = TokenUtils.getCurrentUser();
        if(RoleEnum.USER.name().equals(currentUser.getRole())){
            address.setUserId(currentUser.getId());
        }
        return addressMapper.selectAll(address);
    }

    /**
     * 分页查询
     */
    public PageInfo<Address> selectPage(Address address, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if(RoleEnum.USER.name().equals(currentUser.getRole())){
            address.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Address> list = addressMapper.selectAll(address);
        return PageInfo.of(list);
    }


}