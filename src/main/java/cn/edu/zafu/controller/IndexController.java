package cn.edu.zafu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lizhangqu on 16/1/24.
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping("index")
    public ModelAndView index(){
        Logger logger = LoggerFactory.getLogger("lizhangqu");
        logger.error("index error");
        return new ModelAndView("index/index");
    }

    @RequestMapping("test")
    public String test(){
        Logger logger = LoggerFactory.getLogger("lizhangqu");
        logger.error("test error");
        return "index/test";
    }

}
