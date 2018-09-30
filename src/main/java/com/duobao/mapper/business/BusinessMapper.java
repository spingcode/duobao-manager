package com.duobao.mapper.business;

import com.duobao.entity.Business;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessMapper {

    int insert(Business business);


    Business selectByOrgId(String orgId);

    List<Business> getAllBusiness();

    Business getBusinessById(int id);

    int updateBusinessById(Business business);

    List<Business> getAllBusinessByOrgId(String orgId);


}