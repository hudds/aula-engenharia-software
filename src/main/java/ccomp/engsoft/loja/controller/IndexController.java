package ccomp.engsoft.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping(path = "controllesTeste")
    @ResponseBody
    public String index(){
        return "oiii";
    }
}
