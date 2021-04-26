package ccomp.engsoft.loja;

import ccomp.engsoft.loja.config.JpaConfiguration;
import ccomp.engsoft.loja.controller.ProdutoController;
import ccomp.engsoft.loja.converter.ProdutoConverter;
import ccomp.engsoft.loja.dao.GenericDao;
import ccomp.engsoft.loja.service.GenericCrudService;
import ccomp.engsoft.loja.validator.ConfirmacaoSenhaValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackageClasses = {JpaConfiguration.class, ProdutoController.class, GenericDao.class, ConfirmacaoSenhaValidator.class,GenericCrudService.class, ProdutoConverter.class}, exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories
public class SistemaLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaLojaApplication.class, args);
    }

}
