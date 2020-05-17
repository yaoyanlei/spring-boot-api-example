package com.apelab.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
@Configuration
@MapperScan(basePackages = DataSourceConfig.MAPPER_PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

	@Value("${datasource.driver-class-name}")
	private String driverClassName;

	@Value("${datasource.url}")
	private String connectUrl;

	@Value("${datasource.userName}")
	private String userName;

	@Value("${datasource.password}")
	private String password;

	@Value("${datasource.maxActive}")
	private int maxActive;

	@Value("${datasource.maxWait}")
	private long maxWait;

	public final static String MAPPER_PACKAGE = "com.apelab.example.repository.mapper";

	private final static String MAPPER_LOCATION = "classpath:sql/*.xml";

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(this.driverClassName);
		dataSource.setUrl(this.connectUrl);
		dataSource.setUsername(this.userName);
		dataSource.setPassword(this.password);
		dataSource.setMaxWait(this.maxWait);
		dataSource.setMaxActive(this.maxActive);
		dataSource.setValidationQuery("SELECT 1 FROM DUAL");
		dataSource.setTestOnReturn(false);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestWhileIdle(true);
		return dataSource;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
		registerPlugins(factoryBean);
		return factoryBean.getObject();
	}

	private void registerPlugins(SqlSessionFactoryBean sessionFactory) {
		List<Interceptor> plugins = new ArrayList<>();
		OffsetLimitInterceptor offsetLimitInterceptor = new OffsetLimitInterceptor();
//		offsetLimitInterceptor.setDialect(new MySQLDialect());
		plugins.add(offsetLimitInterceptor);
		if (CollectionUtils.isNotEmpty(plugins)) {
			sessionFactory.setPlugins(plugins.toArray(new Interceptor[plugins.size()]));
		}
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
