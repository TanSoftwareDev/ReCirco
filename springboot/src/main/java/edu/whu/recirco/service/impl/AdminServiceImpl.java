package edu.whu.recirco.service.impl;

import edu.whu.recirco.domain.Admin;
import edu.whu.recirco.dao.AdminDao;
import edu.whu.recirco.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
