package com.xzc.seccondhandmarket.dao;
import com.xzc.seccondhandmarket.domain.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommodityDao  extends JpaRepository<Commodity,Integer>, JpaSpecificationExecutor<Commodity> {


}
