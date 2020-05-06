package com.xzc.seccondhandmarket.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "m_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_id")
    private Integer o_id;//订单号
    @Column(name = "o_name")
    private String o_name;//订单商品
    @Column(name = "o_money")
    private Double o_money;//订单价格
    @Column(name = "o_seller")
    private String o_seller;//卖家
    @Column(name = "o_buyer")
    private String o_buyer;//买家
    @Column(name = "o_date")
    private String date;//购买时间
    @Column(name = "message")
    private String message;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "u_o_id",referencedColumnName = "u_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Order{" +
                "o_id=" + o_id +
                ", o_name='" + o_name + '\'' +
                ", o_nomey='" + o_money + '\'' +
                ", o_seller='" + o_seller + '\'' +
                ", o_buyer='" + o_buyer + '\'' +
                ", o_message='"+message+'\''+
                ", date=" + date +
                '}';
    }

    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public Double getO_money() {
        return o_money;
    }

    public void setO_money(Double o_nomey) {
        this.o_money = o_nomey;
    }

    public String getO_seller() {
        return o_seller;
    }

    public void setO_seller(String o_seller) {
        this.o_seller = o_seller;
    }

    public String getO_buyer() {
        return o_buyer;
    }

    public void setO_buyer(String o_buyer) {
        this.o_buyer = o_buyer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
