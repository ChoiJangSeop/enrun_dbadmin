package gam.jangseop.dbadmin.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
//        basePackages = "gam.jangseop.dbadmin.auth.repository",
//        entityManagerFactoryRef = "authEntityManager",
//        transactionManagerRef = "authTransactionManager"
//)
//@Configuration
//@PropertySource(value = {"classpath:application.yml"})
//public class DataSourceAuthConfig {
//
//    @Bean(name = "authEntityManager")
//    public LocalContainerEntityManagerFactoryBean authEntityManager() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//
//        em.setDataSource(authDataSource());
//        em.setPackagesToScan(new String[] { "gam.jangseop.dbadmin.auth.domain" });
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        Map<String, String> properties = Map.of(
//                "hibernate.show_sql", "true",
//                "hibernate.format_sql", "true",
//                "hibernate.dialect", "org.hibernate.dialect.H2Dialect",
//                "hibernate.ddl-auto", "create"
//        );
//
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Bean(name = "authDataSource")
//    @ConfigurationProperties(prefix = "spring")
//    public DataSource authDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "authTransactionManager")
//    public PlatformTransactionManager authTransactionManager() {
//        JpaTransactionManager tm = new JpaTransactionManager();
//        tm.setEntityManagerFactory(authEntityManager().getObject());
//        return tm;
//    }
//}
