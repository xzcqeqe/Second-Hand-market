package com.xzc.seccondhandmarket.domain;
//商品类
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "commodity")
@Component
public class Commodity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Integer c_id;
    @Column(name = "name")
    private String  name;
    @Column(name = "category")
    private String  category;
    @Column(name = "des")
    private String  des;
    @Column(name = "money")
    private Double money;

//    private Integer uid;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "u_c_id",referencedColumnName = "u_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "c_id=" + c_id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", desc='" + des + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getId() {
        return c_id;
    }

    public void setId(Integer c_id) {
        this.c_id = c_id;
    }

//    public Integer getUid() {
//        return uid;
//    }
//
//    public void setUid(Integer uid) {
//        this.uid = uid;
//    }

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
