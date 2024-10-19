package edu.whu.recirco.dao;

import edu.whu.recirco.domain.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 公告信息表 Mapper 接口
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Mapper
public interface NoticeDao extends BaseMapper<Notice> {

    List<Notice> selectAll(Notice notice);
}
