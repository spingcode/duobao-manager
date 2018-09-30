package com.duobao.service;

import com.duobao.entity.Business;

import java.util.List;

public interface BusinessService {


    public boolean insertBusiness(Business business);

    /**改变商户的状态*/
    public boolean becomeValidOrInVaild(Integer id);


    List<Business> getAllBusiness();

    Business getBusinessById(int id);

    boolean updateBusiness(Business business);

    List<Business> getAllBusinessByOrgId(String orgId);
}
