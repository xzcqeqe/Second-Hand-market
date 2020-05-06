package com.xzc.seccondhandmarket.domain;

import javax.naming.Name;
import javax.persistence.*;
import java.io.Serializable;
//等待后台审核通过的商品
@Entity
@Table(name = "waitcommodity")
public class WaitCommodity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "w_id")
    private Integer w_id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "des")
    private String des;
    @Column(name = "money")
    private Double money;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "u_w_id",referencedColumnName = "u_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WaitCommodity{" +
                "w_id=" + w_id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", des='" + des + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getW_id() {
        return w_id;
    }

    public void setW_id(Integer w_id) {
        this.w_id = w_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
