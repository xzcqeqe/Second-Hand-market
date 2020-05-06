package com.xzc.seccondhandmarket.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

//分页对象
@Component
public class pageBean <T>{//采用泛型，商品分页，客户分页，等等。。。。
    private Integer totalCount;//总记录
    private Integer totalPage;//总页数
    private List<T> list;  //每页展示的数据
    private Integer currentPage; //当前页码
    private Integer rows;     //每页显示记录数

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
