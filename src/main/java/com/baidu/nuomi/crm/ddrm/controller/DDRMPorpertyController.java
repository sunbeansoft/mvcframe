package com.baidu.nuomi.crm.ddrm.controller;

import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMDomain;
import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMProperty;
import com.baidu.nuomi.crm.ddrm.server.model.DDRMResult;
import com.baidu.nuomi.crm.ddrm.server.model.FieldResult;
import com.baidu.nuomi.crm.ddrm.server.service.DDRMService;
import com.baidu.nuomi.crm.ddrm.service.IDDRMDomainService;
import com.baidu.nuomi.crm.ddrm.service.IDDRMPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mazhen01
 * Date: 2014/7/24
 * Time: 17:05
 */
@Controller
@RequestMapping("/property")
public class DDRMPorpertyController {

    private final static Logger logger = LoggerFactory.getLogger(DDRMPorpertyController.class);

    @Autowired
    private IDDRMPropertyService iddrmPropertyService;

    @Autowired
    private IDDRMDomainService iddrmDomainService;

    @Autowired
    private DDRMService ddrmService;


    /**
     * 可以通过.htm和.json的扩展名，返回视图和json格式的数据
     *
     * @return ModelAndView
     */
    @RequestMapping("/propertyIndex")
    public ModelAndView propertyView(String domain, String user) {

        List<DDRMProperty> ddrmPropertyList = iddrmPropertyService.getAllPropertiesByDomain(domain);

        ModelAndView view = new ModelAndView("propertyIndex");
        view.addObject("ddrmPropertyList", ddrmPropertyList);

        return view;
    }

    @RequestMapping("/updateProperty")
    public ModelAndView updateProperty(String domain, String propertyKey, String propertyValue) {
        ResultModel resultModel = new ResultModel(false);
        int result = iddrmPropertyService.changePropertyValue(domain, propertyKey, propertyValue);
        if (result > 0) {
            DDRMResult ddrmResult = new DDRMResult();
            DDRMDomain ddrmDomain = iddrmDomainService.getDDRMDomainInfo(domain);
            ddrmResult.setSuccess(true);
            ddrmResult.setClassFullName(ddrmDomain.getClassFullName());
            FieldResult fieldResult = new FieldResult();
            fieldResult.setName(propertyKey);
            fieldResult.setValue(propertyValue);
            List<FieldResult> fieldResultList = new ArrayList<FieldResult>();
            ddrmResult.setResult(fieldResultList);
            ddrmService.pushPropertiesToDomain(domain, ddrmResult);
            resultModel.setSuccess(true);
            resultModel.setMemo("成功");
        }
        ModelAndView view = new ModelAndView("propertyIndex");
        view.addObject("result", resultModel);
        return view;
    }
}
