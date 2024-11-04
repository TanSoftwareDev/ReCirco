package edu.whu.recirco.service;


import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.whu.recirco.common.enums.RoleEnum;
import edu.whu.recirco.entity.Account;
import edu.whu.recirco.entity.Comment;
import edu.whu.recirco.mapper.CommentMapper;
import edu.whu.recirco.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * comment业务处理
 **/
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * 新增
     */
    public void add(Comment comment) {
        comment.setTime(DateUtil.now());
        commentMapper.insert(comment);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        commentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            commentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Comment comment) {
        commentMapper.updateById(comment);
    }

    /**
     * 根据ID查询
     */
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Comment> selectAll(Comment comment) {
        return commentMapper.selectAll(comment);
    }

    /**
     * 分页查询
     */
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            comment.setUserId(currentUser.getId());
        }
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            comment.setBusinessId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> list = commentMapper.selectAll(comment);
        return PageInfo.of(list);
    }

    public List<Comment> selectByGoodsId(Integer id) {
        return commentMapper.selectByGoodsId(id);
    }
}