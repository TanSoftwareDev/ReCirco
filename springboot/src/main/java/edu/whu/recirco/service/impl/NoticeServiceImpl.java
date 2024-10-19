package edu.whu.recirco.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.whu.recirco.domain.Account;
import edu.whu.recirco.domain.Notice;
import edu.whu.recirco.dao.NoticeDao;
import edu.whu.recirco.security.JwtTokenUtil;
import edu.whu.recirco.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公告信息表 服务实现类
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, Notice> implements INoticeService {
    /**
     * 新增
     */
    public void add(Notice notice) {
        notice.setTime(DateUtil.today());
        Account currentUser = JwtTokenUtil.getCurrentUser();
        notice.setUser(currentUser.getUsername());
        this.baseMapper.insert(notice);
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
    public boolean updateById(Notice notice) {
        this.baseMapper.updateById(notice);
        return true;
    }

    /**
     * 根据ID查询
     */
    public Notice selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Notice> selectAll(Notice notice) {
        return this.baseMapper.selectAll(notice);
    }

    /**
     * 分页查询
     */
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> list = this.baseMapper.selectAll(notice);
        return PageInfo.of(list);
    }

}
