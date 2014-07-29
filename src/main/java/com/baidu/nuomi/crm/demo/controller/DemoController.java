package com.baidu.nuomi.crm.demo.controller;

import com.baidu.nuomi.crm.demo.service.ICategoryService;
import com.baidu.nuomi.crm.demo.service.model.CategoryRelation;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mazhen01
 * Date: 2014/7/24
 * Time: 17:05
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private ICategoryService categoryService;

    @ResponseBody
    @RequestMapping("/string")
    public Object string(@RequestParam(value = "aString", required = true) String aString) {
        return String.format("Hello %s world!", aString);
    }

    @ResponseBody
    @RequestMapping("/json")
    public Object json() {
        List<Integer> aList = Lists.newArrayList();
        aList.add(1);
        aList.add(2);
        aList.add(3);
        return aList;
    }

    /**
     * 可以通过.htm和.json的扩展名，返回视图和json格式的数据
     * @return ModelAndView
     */
    @RequestMapping("/view")
    public ModelAndView view() {

        CategoryRelation categoryRelation = categoryService.packageCategoryRelation(320);

        ModelAndView view = new ModelAndView("index");
        view.addObject("aString", "isMiddle:" + categoryRelation.isMiddle());
        view.addObject("categoryRelation", categoryRelation);

        logger.info("demo info {}", categoryRelation);
        logger.error("demo error", new RuntimeException("mistake"));
        return view;
    }
}
