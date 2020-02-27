package me.jar.scw.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Date 2020/2/16-9:58
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String Hello() {
        return "/success.jsp";
    }
}
