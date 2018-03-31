package com.example.djran.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author d.djran@gmail.com
 */
//@Data
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

    @Column(name = "IMAGE_URL_BAK")
    private String imageUrlBak;

    /**
     * 搜索开始时间
     */
    private String searchStartTime;
    /**
     * 搜索结束时间
     */
    private String searchEndTime;
    /**
     * 最后一个栏目节点id
     */
    private String categoryId;
    /**
     * 获取栏目名称
     */
    private String categoryName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageNetUrl() {
        return imageNetUrl;
    }

    public void setImageNetUrl(String imageNetUrl) {
        this.imageNetUrl = imageNetUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Date getTopTime() {
        return topTime;
    }

    public void setTopTime(Date topTime) {
        this.topTime = topTime;
    }

    public Boolean getPush() {
        return isPush;
    }

    public void setPush(Boolean push) {
        isPush = push;
    }

    public Boolean getHasPush() {
        return hasPush;
    }

    public void setHasPush(Boolean hasPush) {
        this.hasPush = hasPush;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Long isConfirm) {
        this.isConfirm = isConfirm;
    }

    public Long getCountView() {
        return countView;
    }

    public void setCountView(Long countView) {
        this.countView = countView;
    }

    public Long getCountLike() {
        return countLike;
    }

    public void setCountLike(Long countLike) {
        this.countLike = countLike;
    }

    public Long getCountComment() {
        return countComment;
    }

    public void setCountComment(Long countComment) {
        this.countComment = countComment;
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getComment() {
        return isComment;
    }

    public void setComment(Boolean comment) {
        isComment = comment;
    }

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getOuter() {
        return isOuter;
    }

    public void setOuter(Boolean outer) {
        isOuter = outer;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOutLink() {
        return outLink;
    }

    public void setOutLink(String outLink) {
        this.outLink = outLink;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImageUrlBak() {
        return imageUrlBak;
    }

    public void setImageUrlBak(String imageUrlBak) {
        this.imageUrlBak = imageUrlBak;
    }

    public String getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(String searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
