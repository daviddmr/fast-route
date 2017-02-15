package br.com.trixlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kaynan on 19/01/17.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }
}
