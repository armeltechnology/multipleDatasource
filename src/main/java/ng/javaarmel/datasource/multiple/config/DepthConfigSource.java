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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "depthEntityManagerFactory",
basePackages = {"ng.javaarmel.datasource.multiple.repository.depth"}, 
transactionManagerRef = "depthTransactionManager")
public class DepthConfigSource {
	
	@Primary
	@ConfigurationProperties(prefix = "spring.department.datasource")
	@Bean(name = "depthDatasource")
	public DataSource datasource() {
				
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name = "depthEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("depthDatasource")DataSource datasource) {
		Map<String, Object> properties=new HashMap<>();
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hdm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(datasource)
				.properties(properties).packages("ng.javaarmel.datasource.multiple.entity.depth")
				.persistenceUnit("Department").build();
	}
	
	@Primary
	@Bean(name = "depthTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("depthEntityManagerFactory")EntityManagerFactory entityManagerFactory) {
		
		return new JpaTransactionManager(entityManagerFactory);
		
	}
	

}
