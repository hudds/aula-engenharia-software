package ccomp.engsoft.loja;

import ccomp.engsoft.loja.controller.IndexController;
import org.joinfaces.autoconfigure.jpa.JpaWebAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackageClasses = IndexController.class, exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class, JpaWebAutoConfiguration.class})

public class SistemaLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaLojaApplication.class, args);
    }

}
