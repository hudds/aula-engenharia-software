package ccomp.engsoft.loja.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:local-dev-environment.properties")
public class JpaConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public HikariConfig hikariConfigDev() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername(environment.getProperty("database.username"));
        hikariConfig.setPassword(environment.getProperty("database.password"));
        hikariConfig.setJdbcUrl(environment.getProperty("database.url"));
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setMaxLifetime(1200000);

        return hikariConfig;

    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfigDev) throws PropertyVetoException {
        HikariDataSource dataSource = new HikariDataSource(hikariConfigDev);
        return dataSource;
    }

}
