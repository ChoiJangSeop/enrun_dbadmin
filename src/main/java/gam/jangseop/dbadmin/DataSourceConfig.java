package gam.jangseop.dbadmin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;
//
//@EnableJpaRepositories(
//        basePackages = "gam.jangseop.dbadmin.repository",
//        entityManagerFactoryRef = "entityManager",
//        transactionManagerRef = "transactionManager"
//)
//@Configuration
//@PropertySource(value = { "classpath:application.yml"})
//public class DataSourceConfig {
//
//    @Primary
//    @Bean(name = "entityManager")
//    public LocalContainerEntityManagerFactoryBean entityManger() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan(new String[] { "gam.jangseop.dbadmin.domain" });
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//
//
//        Map<String, String> properties = Map.of(
//                "hibernate.show_sql", "true",
//                "hibernate.format_sql", "true",
//                "hibernate.dialect", "org.hibernate.dialect.H2Dialect",
//                "hibernate.ddl-auto", "create-drop"
//        );
//
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Primary
//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager tm = new JpaTransactionManager();
//        tm.setEntityManagerFactory(entityManger().getObject());
//        return tm;
//    }
//}
