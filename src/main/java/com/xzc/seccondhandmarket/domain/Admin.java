package com.xzc.seccondhandmarket.domain;

import javax.persistence.*;
import java.io.Serializable;

//后台管理员 上传商品得经过他同意
@Entity
@Table(name="admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Integer a_id;
    @Column(name = "a_username")
    private String  a_username;
    @Column(name = "a_password")
    private String  a_password;

    @Override
    public String toString() {
        return "Admin{" +
                "a_id=" + a_id +
                ", a_username='" + a_username + '\'' +
                ", a_password='" + a_password + '\'' +
                '}';
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public String getA_username() {
        return a_username;
    }

    public void setA_username(String a_username) {
        this.a_username = a_username;
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }
}
