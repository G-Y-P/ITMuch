package com.itmuch.contentcenter.domain.entity.content;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "share")
public class Share {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否原创0:否1:是
     */
    @Column(name = "is_original")
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
    @Column(name = "download_url")
    private String downloadUrl;

    /**
     * 下载数
     */
    @Column(name = "buy_count")
    private Integer buyCount;

    /**
     * 是否显示0:否1:是
     */
    @Column(name = "show_flag")
    private Boolean showFlag;

    /**
     * 审核状态NOT_YET:待审教
     */
    @Column(name = "audit_status")
    private String auditStatus;

    /**
     * 审核不通过原因
     */
    private String reason;

    /**
     * 发布人Id
     */
    @Column(name = "user_id")
    private Integer userId;
}