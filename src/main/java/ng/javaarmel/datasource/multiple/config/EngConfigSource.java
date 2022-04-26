package ng.javaarmel.datasource.multiple.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "engEntityManagerFactory",
basePackages = {"ng.javaarmel.datasource.multiple.repository.eng"},
transactionManagerRef = "engTransactionManager")
public class EngConfigSource {

	@ConfigurationProperties(prefix = "spring.engineer.datasource")
	@Bean(name = "engDatasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "engEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("engDatasource")DataSource datasource) {
		
		Map<String, Object> properties=new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(datasource).properties(properties)
				.packages("ng.javaarmel.datasource.multiple.entity.eng")
				.persistenceUnit("Engineer").build();
	}
	
	
	@Bean(name = "engTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("engEntityManagerFactory")EntityManagerFactory entityManagerFactory) {
		
		return new JpaTransactionManager(entityManagerFactory);
		
	}
	
}
