package org.cis.config;

import org.cis.backend.DummyEJurnalDAO;
import org.cis.backend.EJournalDAO;
import org.cis.backend.JdbcEJurnalDAO;
import org.cis.backend.TestBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class ContextConfig {

    @Bean
    public DataSource dataSource(@Value("${jdbc.driver}") String driver,
                                 @Value("${jdbc.url}") String url,
                                 @Value("${jdbc.username}") String user,
                                 @Value("${jdbc.password}") String password){
        url = url +"?encoding=UTF8&sql_dialect=3";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return  dataSource;
    }

    @Bean
    public JdbcEJurnalDAO jdbcEJurnalDAO(DataSource dataSource){
        JdbcEJurnalDAO jurnalDAO = new JdbcEJurnalDAO();
        jurnalDAO.setDataSource(dataSource);
        return jurnalDAO;
    }

    @Bean
    public EJournalDAO eJournalDAO(DataSource dataSource){
        JdbcEJurnalDAO jurnalDAO = new JdbcEJurnalDAO();
        jurnalDAO.setDataSource(dataSource);
        return jurnalDAO;
        //return new DummyEJurnalDAO();
    }

    @Bean
    public TestBean testBean(){
        return new TestBean();
    }

}
