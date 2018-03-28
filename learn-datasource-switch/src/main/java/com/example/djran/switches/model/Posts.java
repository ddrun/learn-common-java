package com.example.djran.switches.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dengjr.
 */
@Data
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;

    private String title;

    private String keywords;

    private String imageUrl;

    private String imageNetUrl;

    private String source;
    private String content;

    private Date startTime;

    private Date endTime;

    private Boolean isTop;

    private Date topTime;
    private Boolean isPush;

    private Boolean hasPush;
    private String status;


    private Long isConfirm;

    private Long countView;
    private Long countLike;
    private Long countComment;

    private String publishUser;
    private Date publishTime;
    private String sort;
    /**
     * 是否允许评论
     */
    private Boolean isComment;
    /**
     * 是否允许回复
     */
    private Boolean isRecommend;
    private Date updateTime;
    private Boolean isOuter;
    private String outerId;

    private String appId;

    /**
     * 外部链接
     */
    private String outLink;
    /**
     * 摘要
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;
    /**
     * 提交审核时间
     */
    private Date submitTime;

    private String searchStartTime;//搜索开始时间
    private String searchEndTime;//搜索结束时间

    private String categoryId;//最后一个栏目节点id
    private String categoryName;//获取栏目名称


}
