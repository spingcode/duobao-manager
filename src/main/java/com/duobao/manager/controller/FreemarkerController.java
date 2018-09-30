package com.duobao.manager.controller;

import com.duobao.entity.Business;
import com.duobao.service.BusinessService;
import com.duobao.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("freemarker")
public class FreemarkerController {

    @Autowired
    private BusinessService businessService;

    String getAllBusinessUrl="http://118.24.127.29:8089/freemarker/getAllBusiness";
    @RequestMapping("getAllBusiness")
    public String getAllBusiness(Map<String,Object> map) {
        List<Business> allBusiness = businessService.getAllBusiness();
        Map<String,Business> mapBusiness = new LinkedHashMap<>();
        if (CollectionUtils.isNotEmpty(allBusiness)) {
            for (Business business : allBusiness) {
                Business business1 = mapBusiness.get(business.getOrgid());
                if (business1 == null|| DateUtil.compare(business1.getCreateTime().getTime(),business.getCreateTime().getTime())<0) {
                    mapBusiness.put(business.getOrgid(),business);
                }
            }
        }
        map.put("msg", mapBusiness.values());
        return "hello";
    }


    @RequestMapping("edit")
    public String editBusiness(Map<String,Object> map,Integer businessId) {
        Business business = businessService.getBusinessById(businessId);
        map.put("msg", business);
        return "editBusiness";
    }

    @RequestMapping("details")
    public String detailsBusiness(Map<String,Object> map,Integer businessId) {
        Business business = businessService.getBusinessById(businessId);
        map.put("msg", business);
        return "detailsBusiness";
    }
    @RequestMapping("saveBusiness")
    public String saveBusiness(Map<String,Object> map,Business business,HttpServletResponse response) {
        businessService.updateBusiness(business);
        try {
            response.sendRedirect(getAllBusinessUrl);
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    @RequestMapping("getHistoryByOrgid")
    public String getHistoryByOrgid(Map<String,Object> map,String orgid) {
        if (StringUtils.isBlank(orgid)) {
            return null;
        }
        List<Business> businessList = businessService.getAllBusinessByOrgId(orgid);
        map.put("msg", businessList);
        return "history";
    }

    @RequestMapping("addNewBusiness")
    public String addBusiness(Business business,HttpServletResponse response) {
        if (business == null || StringUtils.isBlank(business.getOrgid())) {
            try {
                response.sendRedirect(getAllBusinessUrl);
            } catch (IOException e) {
                return null;
            }
            return null;
        }
        businessService.insertBusiness(business);
        try {
            response.sendRedirect(getAllBusinessUrl);
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    @RequestMapping("newPage")
    public String newPage() {
        return "addBusinesses";
    }

    @RequestMapping("becomeInValid")
    public String changeValid(Integer businessId,HttpServletResponse response) {
        if (businessId == null) {
            try {
                response.sendRedirect(getAllBusinessUrl);
            } catch (IOException e) {
                return null;
            }
            return null;
        }
        businessService.becomeValidOrInVaild(businessId);
        try {
            response.sendRedirect(getAllBusinessUrl);
        } catch (IOException e) {
            return null;
        }
        return null;
    }


}
