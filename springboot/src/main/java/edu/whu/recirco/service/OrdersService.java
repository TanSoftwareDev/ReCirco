package edu.whu.recirco.service;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.whu.recirco.common.enums.RoleEnum;
import edu.whu.recirco.entity.Account;
import edu.whu.recirco.entity.Cart;
import edu.whu.recirco.entity.Orders;
import edu.whu.recirco.mapper.CartMapper;
import edu.whu.recirco.mapper.OrdersMapper;
import edu.whu.recirco.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 购物车业务处理
 **/
@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Autowired
    private CartMapper cartMapper;

    /**
     * 新增
     */
    public void add(Orders orders) {
        orders.setOrderId(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        for(Cart cart : orders.getCartData()){
            Orders dbOrders = new Orders();
            BeanUtils.copyProperties(orders, dbOrders);
            dbOrders.setGoodsId(cart.getGoodsId());
            dbOrders.setBusinessId(cart.getBusinessId());
            dbOrders.setNum(cart.getNum());
            dbOrders.setPrice(cart.getNum()*cart.getGoodsPrice());
            System.out.println(orders);
            System.out.println(dbOrders);
            ordersMapper.insert(dbOrders);

            //删掉选中的商品
            cartMapper.deleteById(cart.getId());
        }
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ordersMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Orders orders) {
        ordersMapper.updateById(orders);
    }

    /**
     * 根据ID查询
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders) {
        return ordersMapper.selectAll(orders);
    }

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if(RoleEnum.USER.name().equals(currentUser.getRole())){
            orders.setUserId(currentUser.getId());
        }
        if(RoleEnum.BUSINESS.name().equals(currentUser.getRole())){
            orders.setBusinessId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        return PageInfo.of(list);
    }


}