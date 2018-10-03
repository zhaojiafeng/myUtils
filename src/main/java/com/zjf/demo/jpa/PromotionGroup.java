package com.zjf.demo.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name= "promotion_group" )
public class PromotionGroup implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    //mappedBy的值"promotionGroup"指向owner(Promotion)端的关联属性,并且是双向关系
    @OneToMany (mappedBy= "promotionGroup" ,cascade=CascadeType.ALL)
    private List<Promotion> promotion;

    //get set 省略....

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Promotion> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<Promotion> promotion) {
        this.promotion = promotion;
    }
}

