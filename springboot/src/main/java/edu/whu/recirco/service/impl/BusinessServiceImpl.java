package edu.whu.recirco.service.impl;

import edu.whu.recirco.domain.Business;
import edu.whu.recirco.dao.BusinessDao;
import edu.whu.recirco.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
