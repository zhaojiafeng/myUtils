package com.zjf.demo.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "promotion_info")
public class Promotion implements Serializable {

    //AUTO--可以是identity类型的字段,或者sequence类型或者table类型,取决于不同的底层数据库
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_start_amount")
    private Integer groupStartAmount = 0;

    //@Lob 如果是文章内容可以使用 只需要把length=200去掉就可以了
    @Column(name = "promotion_remark", length = 200)
    private String remark;

    //DATE - java.sql.Date
    //TIME - java.sql.Time
    //TIMESTAMP - java.sql.Timestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startTime;

    //显示0 隐藏1
    public static enum DisplayType {
        hide, show
    }

    @Enumerated(value = EnumType.ORDINAL) //ORDINAL序数
    private DisplayType displayType = DisplayType.show;

    @Version
    private Integer version;

    //CascadeType.PERSIST -- 触发级联创建(create)
    //CascadeType.MERGE -- 触发级联合并(update)
    //CascadeType.All
    //FetchType.LAZY -- 延迟加载
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH}, fetch = FetchType.LAZY)
    private PromotionGroup promotionGroup;

    //单向ManyToMany
    //@JoinTable(关联的表名)
    //joinColumns -- promotion关联的列的外键
    //inverseJoinColumns -- largess 关联列的外键
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "promotion_largess", joinColumns = {@JoinColumn(name = "promotion_id")}, inverseJoinColumns = {@JoinColumn(name = "largess_id")})
    private Set<Largess> largess;

    //get set 省略....

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupStartAmount() {
        return groupStartAmount;
    }

    public void setGroupStartAmount(Integer groupStartAmount) {
        this.groupStartAmount = groupStartAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public PromotionGroup getPromotionGroup() {
        return promotionGroup;
    }

    public void setPromotionGroup(PromotionGroup promotionGroup) {
        this.promotionGroup = promotionGroup;
    }

    public Set<Largess> getLargess() {
        return largess;
    }

    public void setLargess(Set<Largess> largess) {
        this.largess = largess;
    }
}