package com.baidu.nuomi.crm.ddrm.controller;

import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMDomain;
import com.baidu.nuomi.crm.ddrm.service.IDDRMDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mazhen01
 * Date: 2014/7/24
 * Time: 17:05
 */
@Controller
@RequestMapping("/ddrm")
public class DDRMController {

    private final static Logger logger = LoggerFactory.getLogger(DDRMController.class);

    @Autowired
    private IDDRMDomainService iddrmDomainService;

    /**
     * 可以通过.htm和.json的扩展名，返回视图和json格式的数据
     *
     * @return ModelAndView
     */
    @RequestMapping("/domain")
    public ModelAndView domainView() {

        List<DDRMDomain> ddrmDomainList = iddrmDomainService.getAllDomain();

        ModelAndView view = new ModelAndView("domain");
        view.addObject("ddrmDomainList", ddrmDomainList);

        return view;
    }
}
