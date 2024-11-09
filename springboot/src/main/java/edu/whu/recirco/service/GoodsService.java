package edu.whu.recirco.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import edu.whu.recirco.entity.*;
import edu.whu.recirco.mapper.*;
import edu.whu.recirco.utils.UserCF;
import org.springframework.stereotype.Service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.whu.recirco.common.enums.RoleEnum;
import edu.whu.recirco.utils.TokenUtils;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Random;

@Service
public class GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CartMapper cartMapper;
    @Resource
    private OrdersMapper ordersMapper;

    /**
     * 新增
     */
    public void add(Goods goods) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            goods.setBusinessId(currentUser.getId());
        }
        goodsMapper.insert(goods);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        goodsMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            goodsMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Goods goods) {
        goodsMapper.updateById(goods);
    }

    /**
     * 根据ID查询
     */
    public Goods selectById(Integer id) {
        return goodsMapper.selectById(id);
    }

    public List<Goods> selectTop15() {return goodsMapper.selectTop15();}

    public List<Goods> selectByTypeId(Integer id) {
        return goodsMapper.selectByTypeId(id);
    }
    /**
     * 查询所有
     */
    public List<Goods> selectAll(Goods goods) {
        return goodsMapper.selectAll(goods);
    }

    /**
     * 分页查询
     */
    public PageInfo<Goods> selectPage(Goods goods, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goodsMapper.selectAll(goods);
        return PageInfo.of(list);
    }
    public List<Goods> selectByName(String name) {
        return goodsMapper.selectByName(name);
    }

    public List<Goods> selectByBusinessId(Integer id) {
        return goodsMapper.selectByBusinessId(id);
    }

//    public List<Goods> recommend() {
//        Account currentUser = TokenUtils.getCurrentUser();
//        if (ObjectUtil.isEmpty(currentUser)) {
//            // 没有用户登录
//            return new ArrayList<>();
//        }
//        //用户的哪些行为可以认为他跟商品产生了关系?收藏、加入购物车、下单、评论
//        //1.获取所有的收藏信息
//        List<Collect> allCollects = collectMapper.selectAll(null);
//        //2.获取所有的购物车信息
//        List<Cart> allCart = cartMapper.selectAll(null);
//        //3.获取所有的订单信息
//        List <Orders> allOrders = ordersMapper.selectAllOKOrders();
//        //4.获取所有的评论信息
//        List<Comment> allComments = commentMapper.selectAll(null);
//        //5.获取所有的用户信息
//        List<User> allUser = userMapper.selectAll(null);
//        //6.获取所有的商品信息
//        List<Goods> allGoods = goodsMapper.selectAll(null);
//
//        // 定义存储存储每个用户和每个商品关系的list
//        List<RelateDTO> data = new ArrayList<>();
//        // 定义最后返回给前端的商品List
//        List<Goods> result = new ArrayList<>();
//
//        // 计算用户和商品的关系数据
//        for(Goods goods : allGoods) {
//            Integer goodsId = goods.getId();
//            for(User user : allUser) {
//                Integer userId = user.getId();
//                int index = 1;
//                //判断用户有没有收藏
//                Optional<Collect> collectOptional = allCollects.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
//                if(collectOptional.isPresent()){
//                    index+=1;
//                }
//                //判断用户是否将该商品加入购物车
//                Optional<Cart> cartOptional = allCart.stream().filter(x -> x.getUserId().equals(userId) && x.getGoodsId().equals(goodsId)).findFirst();
//                if(cartOptional.isPresent()){
//                    index+=2;
//                }
//                //判断用户是否下过单
//                Optional<Orders> ordersOptional = allOrders.stream().filter(x -> x.getUserId().equals(userId) && x.getGoodsId().equals(goodsId)).findFirst();
//                if(ordersOptional.isPresent()){
//                    index+=3;
//                }
//                //判断用户是否评论过
//                Optional<Comment> commentOptional = allComments.stream().filter(x -> x.getUserId().equals(userId) && x.getGoodsId().equals(goodsId)).findFirst();
//                if(commentOptional.isPresent()){
//                   index+=2;
//                }
//                // 把用户-商品数据补进去
//                if(index>1){
//                    RelateDTO relateDTO = new RelateDTO(userId,goodsId,index);
//                    data.add(relateDTO);
//                }
//            }
//        }
//
//        //数据准备结束后，就可以把数据喂给推荐算法了
//        Account currentUser =TokenUtils.getCurrentUser();
//        List<Integer> goodsIds = UserCF.recommend(currentUser.getId(), data);
//
//        //对算法结果进行处理: 把商品id转换成商品
//        List<Goods> recommendResult = goodsIds.stream().map(goodsId -> allGoods.stream()
//                        .filter(x->x.getId().equals(goodsId)).findFirst().orElse( null))
//                .limit(10).collect(Collectors.toList());
//        if(CollectionUtil.isEmpty(recommendResult)){
//            //随机给它推荐10个
//            return getRandomGoods(10);
//        }
//
//        if(recommendResult.size()<10){
//            int num=10-recommendResult.size();
//            List<Goods> list = getRandomGoods(num);
//            result.addAll(list);
//        }
//        return result;
//    }
    public List<Goods> recommend() {

        Account currentUser= TokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(currentUser)) {
            // 没有用户登录
            return new ArrayList<>();
        }

        // 用户的哪些行为可以认为他跟商品产生了关系？收藏、加入购物车、下单、评论
        // 1. 获取所有的收藏信息
        List<Collect> allCollects = collectMapper.selectAll(null);
        // 2. 获取所有的购物车信息
        List<Cart> allCarts = cartMapper.selectAll(null);
        // 3. 获取所有的订单信息
        List<Orders> allOrders = ordersMapper.selectAllOKOrders();
        // 4. 获取所有的评论信息
        List<Comment> allComments = commentMapper.selectAll(null);
        // 5. 获取所有的用户信息
        List<User> allUsers = userMapper.selectAll(null);
        // 6. 获取所有的商品信息
        List<Goods> allGoods = goodsMapper.selectAll(null);

        // 定义一个存储每个商品和每个用户关系的List
        List<RelateDTO> data = new ArrayList<>();
        // 定义一个存储最后返回给前端的商品List
        List<Goods> result = new ArrayList<>();
        // 开始计算每个商品和每个用户之间的关系数据
        for (Goods goods : allGoods) {
            Integer goodsId = goods.getId();
            for (User user : allUsers) {
                Integer userId = user.getId();
                int index = 1;
                // 1. 判断该用户有没有收藏该商品，收藏的权重我们给 1
                Optional<Collect> collectOptional = allCollects.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
                if (collectOptional.isPresent()) {
                    index += 1;
                }
                // 2. 判断该用户有没有给该商品加入购物车，加入购物车的权重我们给 2
                Optional<Cart> cartOptional = allCarts.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
                if (cartOptional.isPresent()) {
                    index += 2;
                }
                // 3. 判断该用户有没有对该商品下过单（已完成的订单），订单的权重我们给 3
                Optional<Orders> ordersOptional = allOrders.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
                if (ordersOptional.isPresent()) {
                    index += 3;
                }
                // 4. 判断该用户有没有对该商品评论过，评论的权重我们给 2
//                Optional<Comment> commentOptional = allComments.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
//                if (commentOptional.isPresent()) {
//                    index += 2;
//                }
                if (index > 1) {
                    RelateDTO relateDTO = new RelateDTO(userId, goodsId, index);
                    data.add(relateDTO);
                }
            }
        }

//        return getRandomGoods(10);

// 数据准备结束后，就把这些数据一起喂给这个推荐算法
        List<Integer> goodsIds = UserCF.recommend(currentUser.getId(), data);
        // 把商品id转换成商品
        List<Goods> recommendResult = goodsIds.stream().map(goodsId -> allGoods.stream().filter(x -> x.getId().equals(goodsId)).findFirst().orElse(null)).limit(10).collect(Collectors.toList());
        result.addAll(recommendResult);
        if (CollectionUtil.isEmpty(recommendResult)) {
            // 随机给它推荐10个
            return getRandomGoods(10);
        }
        if (recommendResult.size() < 10) {
            int num = 10 - recommendResult.size();
            List<Goods> list = getRandomGoods(num);
            result.addAll(list);
        }
        return result;
    }

    private List<Goods> getRandomGoods(int num) {
        List<Goods> list = new ArrayList<>(num);
        List<Goods> goods = goodsMapper.selectAll(null);
        for (int i = 0; i < num; i++) {
            int index = new Random().nextInt(goods.size());
            list.add(goods.get(index));
        }
        return list;
    }

}
