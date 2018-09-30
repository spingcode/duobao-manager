package com.duobao.service.impl;

import com.duobao.entity.Business;
import com.duobao.mapper.business.BusinessMapper;
import com.duobao.service.BusinessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("businessServiceImpl")
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public boolean insertBusiness(Business business) {
        if (business == null || StringUtils.isBlank(business.getOrgid())) {
            return false;
        }
        initBusinessAmount(business);
        if (business.getRemainAmount() == null || business.getRemainAmount() <= 0) {
            //1、表示无效
            business.setVaild(1);
        } else {
            //0、表示有效
            business.setVaild(0);
        }
        if (StringUtils.isBlank(business.getReturnUrl())) {
            business.setReturnUrl("www.baidu.com");
        }
        return businessMapper.insert(business)>0;
    }

    private void initBusinessAmount(Business business) {
        if (business.getTotalAmount() == null) {
            business.setTotalAmount(Double.valueOf(0));
        }
        if (business.getRemainAmount() == null) {
            business.setRemainAmount(Double.valueOf(0));
        }
        if (business.getZmfPrice() == null) {
            business.setZmfPrice(0.5);
        }
        if (business.getTotalAmount() == null) {
            business.setHygzPrice(0.3);
        }
    }





    @Override
    public boolean becomeValidOrInVaild(Integer id) {
        if (id == null) {
            return false;
        }
        Business business1 = getBusinessById(id);
        Business business = new Business();
        business.setId(id);
        if (business1.getVaild().equals(0)) {
            business.setVaild(1);
        } else {
            business.setVaild(0);
        }
        return businessMapper.updateBusinessById(business)>0;
    }



    @Override
    public List<Business> getAllBusiness() {
        return businessMapper.getAllBusiness();
    }

    @Override
    public Business getBusinessById(int id) {
        return businessMapper.getBusinessById(id);
    }

    @Override
    public boolean updateBusiness(Business business) {
        return businessMapper.updateBusinessById(business)>0;
    }


    @Override
    public List<Business> getAllBusinessByOrgId(String orgId) {
        return businessMapper.getAllBusinessByOrgId(orgId);
    }
}
