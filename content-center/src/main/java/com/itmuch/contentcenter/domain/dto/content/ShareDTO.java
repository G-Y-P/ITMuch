package com.itmuch.contentcenter.domain.dto.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GEYP
 * @version 1.0
 * @date 2021/1/3 0003 16:38
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareDTO {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否原创0:否1:是
     */
    private Boolean isOriginal;

    /**
     * 作者
     */
    private String author;

    /**
     * 封面
     */
    private String cover;

    /**
     * 概要信息
     */
    private String summary;

    /**
     * 价格（需要的积分)
     */
    private BigDecimal price;

    /**
     * 下载地址
     */
    private String downloadUrl;

    /**
     * 下载数
     */
    private Integer buyCount;

    /**
     * 是否显示0:否1:是
     */
    private Boolean showFlag;

    /**
     * 审核状态NOT_YET:待审教
     */
    private String auditStatus;

    /**
     * 审核不通过原因
     */
    private String reason;

    /**
     * 发布人Id
     */
    private Integer userId;

    /**
     * 微信名称
     */
    private String wxNickName;
}
