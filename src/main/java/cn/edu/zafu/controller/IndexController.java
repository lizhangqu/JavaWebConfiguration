package cn.edu.zafu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lizhangqu on 16/1/24.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index/index");
    }


}
