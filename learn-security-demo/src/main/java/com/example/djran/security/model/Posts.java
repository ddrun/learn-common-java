package com.example.djran.security.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2018/3/29
 *
 * @author d.djran@gmail.com
 */
@Data
@Entity
@Table(name = "C_POSTS")
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 逻辑主键
     */
    @Id
    @Column(name="ID")
    private String id;
    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;
    /**
     * 关键词
     */
    @Column(name = "KEYWORDS")
    private String keywords;
    /**
     * 缩略图（保存的是图片ID，数据来源为C_ATTACHMENT.id）
     */
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    /**
     * 缩略图(前台显示地址)
     */
    @Column(name = "IMAGE_NET_URL")
    private String imageNetUrl;
    /**
     * 新闻来源
     */
    @Column(name = "SOURCE")
    private String source;
    /**
     * 内容
     */
    @Column(name = "CONTENT")
    private String content;
    /**
     * 开始时间
     */
    @Column(name = "START_TIME")
    private Date startTime;
    /**
     * 结束时间
     */
    @Column(name = "END_TIME")
    private Date endTime;
    /**
     * 是否置顶头条：1是0否
     */
    @Column(name = "IS_TOP")
    private Boolean isTop;
    /**
     * 置顶时间
     */
    @Column(name = "TOP_TIME")
    private Date topTime;
    /**
     * 是否推送：1是0否
     */
    @Column(name = "IS_PUSH")
    private Boolean isPush;
    /**
     * 是否已推送：1是0否
     */
    @Column(name = "HAS_PUSH")
    private Boolean hasPush;
    /**
     * created已创建\\published已发布
     */
    @Column(name = "STATUS")
    private String status;
    /**
     * 是否已审核:：1是0否
     */
    @Column(name = "IS_CONFIRM")
    private Long isConfirm;
    /**
     * 浏览量
     */
    @Column(name = "COUNT_VIEW")
    private Long countView;
    /**
     * 收藏数
     */
    @Column(name = "COUNT_LIKE")
    private Long countLike;
    /**
     * 评论数
     */
    @Column(name = "COUNT_COMMENT")
    private Long countComment;
    /**
     * 发布者
     */
    @Column(name = "PUBLISH_USER")
    private String publishUser;
    /**
     * 发布时间
     */
    @Column(name = "PUBLISH_TIME")
    private Date publishTime;
    /**
     * 排序
     */
    @Column(name = "SORT")
    private String sort;
    /**
     * 是否允许评论：1是0否
     */
    @Column(name = "IS_COMMENT")
    private Boolean isComment;
    /**
     * 是否允许回复：1是0否
     */
    @Column(name = "IS_RECOMMEND")
    private Boolean isRecommend;
    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    /**
     * 是否外部新闻:：1是0否
     */
    @Column(name = "IS_OUTER")
    private Boolean isOuter;
    /**
     * 外部主键
     */
    @Column(name = "OUTER_ID")
    private String outerId;
    /**
     * 开发者账号
     */
    @Column(name = "APP_ID")
    private String appId;
    /**
     * 外部链接
     */
    @Column(name = "OUT_LINK")
    private String outLink;
    /**
     * 摘要
     */
    @Column(name = "REMARK")
    private String remark;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;
    /**
     * 创建人
     */
    @Column(name = "CREATOR")
    private String creator;
    /**
     * 提交审核时间\r\n
     */
    @Column(name = "SUBMIT_TIME")
    private Date submitTime;
    /**
     * 标签，主要用于精确推送
     */
    @Column(name = "TAGS")
    private String tags;

}
