package edu.whu.recirco.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商家信息表
 * </p>
 *
 * @author susong
 * @since 2024-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 店铺名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色标识
     */
    private String role;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 商家介绍
     */
    private String description;

    /**
     * 审核状态
     */
    private String status;


}
