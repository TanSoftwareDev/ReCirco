package edu.whu.recirco.controller;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.whu.recirco.common.Result;

import edu.whu.recirco.common.enums.RoleEnum;
import edu.whu.recirco.entity.Account;
import edu.whu.recirco.entity.Goods;
import edu.whu.recirco.service.GoodsService;
import edu.whu.recirco.service.OcrService;
import edu.whu.recirco.utils.TokenUtils;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private OcrService ocrService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Goods goods) throws TesseractException, NoApiKeyException, InputRequiredException {
        Account currentUser = TokenUtils.getCurrentUser();
        if(RoleEnum.BUSINESS.name().equals(currentUser.getRole())){
            goods.setBusinessId(currentUser.getId());
        }
        goodsService.add(goods);
        System.out.println(goods.getId());
        int goodsId = goods.getId();
        String path = goods.getImg();
        ocrService.ocrProcess(path,goodsId);

        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        goodsService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        goodsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Goods goods) {
        goodsService.updateById(goods);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById")
    public Result selectById(@RequestParam Integer id) {
        Goods goods = goodsService.selectById(id);
        return Result.success(goods);
    }

    @GetMapping("/selectTop15")
    public Result SelectTop15(){
        List<Goods> list = goodsService.selectTop15();
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Goods goods ) {
        List<Goods> list = goodsService.selectAll(goods);
        return Result.success(list);
    }

    @GetMapping("/selectByTypeId")
    public Result selectByTypeId(@RequestParam Integer id) {
        List<Goods> list = goodsService.selectByTypeId(id);
        return Result.success(list);
    }

    @GetMapping("/selectByBusinessId")
    public Result selectByBusinessId(@RequestParam Integer id) {
        List<Goods> list = goodsService.selectByBusinessId(id);
        return Result.success(list);
    }

    @GetMapping("/selectByName")
    public Result selectByName(@RequestParam String name) {
        List<Goods> list = goodsService.selectByName(name);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
//    @GetMapping("/selectPage")
//    public Result selectPage(Goods goods,
//                             @RequestParam(defaultValue = "1") Integer pageNum,
//                             @RequestParam(defaultValue = "10") Integer pageSize) {
//        PageInfo<Goods> page = goodsService.selectPage(goods, pageNum, pageSize);
//        return Result.success(page);
//    }

    //商家只能访问到自己的商品，管理员可以访问所有
//    @GetMapping("/selectPage")
//    public PageInfo<Goods> selectPage(Goods goods,
//                             @RequestParam(defaultValue = "1") Integer pageNum,
//                             @RequestParam(defaultValue = "10") Integer pageSize) {
//
//        Account currentUser = TokenUtils.getCurrentUser();
//        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
//            goods.setBusinessId(currentUser.getId());
//        }
//        PageHelper.startPage(pageNum,pageSize);
//        List<Goods> list = goodsService.selectAll(goods);
//        return PageInfo.of(list);
//    }
    @GetMapping("/selectPage")
    public Result selectPage(Goods goods,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize) {

        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            goods.setBusinessId(currentUser.getId());
        }
        PageInfo<Goods> page = goodsService.selectPage(goods, pageNum, pageSize);
        return Result.success(page);
    }

}
