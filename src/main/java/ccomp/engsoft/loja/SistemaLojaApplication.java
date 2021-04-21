package ccomp.engsoft.loja;

import ccomp.engsoft.loja.config.JpaConfiguration;
import ccomp.engsoft.loja.controller.IndexController;
import ccomp.engsoft.loja.dao.GenericDao;
import ccomp.engsoft.loja.service.GenericCrudService;
import org.joinfaces.autoconfigure.jpa.JpaWebAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackageClasses = {JpaConfiguration.class,IndexController.class, GenericDao.class, GenericCrudService.class}, exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories
public class SistemaLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaLojaApplication.class, args);
    }

}
