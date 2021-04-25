package ccomp.engsoft.loja.controller;

import org.springframework.stereotype.Controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("navbarController")
@ApplicationScoped
@Controller
public class NavbarController {
    public String redirect(String pagePath){
        return pagePath+"?faces-redirect=true";
    }
}
