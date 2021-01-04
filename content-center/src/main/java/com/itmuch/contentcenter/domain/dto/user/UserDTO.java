package com.itmuch.contentcenter.domain.dto.user;

import lombok.Data;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author GEYP
 * @version 1.0
 * @date 2021/1/3 0003 16:26
 * @description
 */

@Data
public class UserDTO {
    private Integer id;

    /**
     * 微信ID
     */
    private String wxId;

    /**
     * 微信昵称
     */
    private String wxNickname;

    /**
     * 角色
     */
    private String roles;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 积分
     */
    private Integer bonus;
}