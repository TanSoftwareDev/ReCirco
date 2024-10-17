package edu.whu.recirco.service.impl;

import edu.whu.recirco.domain.Type;
import edu.whu.recirco.dao.TypeDao;
import edu.whu.recirco.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeDao, Type> implements ITypeService {

}
