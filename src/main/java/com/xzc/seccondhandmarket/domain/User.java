package com.xzc.seccondhandmarket.domain;
//用户类
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Integer u_id;
    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "sex")
    private String sex;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "address")
    private String address;


//    @OneToMany(targetEntity = Commodity.class,fetch=FetchType.LAZY)
//    @JoinColumn(name ="u_c_id" ,referencedColumnName = "u_id")
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Commodity> commodityList = new HashSet<>();//一个用户拥有多个商品

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Order> orderList = new HashSet<>();      //一个用户也可以有多个订单

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<WaitCommodity> waitCommodities = new HashSet<>();//一个用户也可以有多个发布请求

    public Set<WaitCommodity> getWaitCommodities() {
        return waitCommodities;
    }

    public void setWaitCommodities(Set<WaitCommodity> waitCommodities) {
        this.waitCommodities = waitCommodities;
    }


    public Set<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Order> orderList) {
        this.orderList = orderList;
    }

    public Set<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(Set<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public Integer getId() {
        return u_id;
    }

    public void setId(Integer u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
