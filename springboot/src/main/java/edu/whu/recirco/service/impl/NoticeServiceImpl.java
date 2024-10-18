package edu.whu.recirco.service.impl;

import edu.whu.recirco.domain.Notice;
import edu.whu.recirco.dao.NoticeDao;
import edu.whu.recirco.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
