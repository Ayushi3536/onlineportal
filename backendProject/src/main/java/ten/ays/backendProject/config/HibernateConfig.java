package ten.ays.backendProject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@Configuaration annotation tells that we are doing some configuration and spring framwork will take care of its beann
@ComponentScan tells where entity classes reside which hibernate requires
@EnableTransactionManagement manages transaction*/

@Configuration
@ComponentScan(basePackages={"net.ays.backendProject.dto"})
@EnableTransactionManagement
public class HibernateConfig {
	private static final String DATABASE_URL="jdbc:h2:tcp://localhost/~/OnlinePortal";
	private static final String DATABASE_DRIVER="org.h2.Driver";
	private static final String DATABASE_DIALECT="org.hibernate.dialect.H2Dialect";
	private static final String DATABASE_USERNAME="user";
	private static final String DATABASE_PASSWORD="";

	//datasource bean will be available
	@Bean
	public DataSource getDataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		return dataSource;
		
	}
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("net.ays.backendProject.dto");
		return builder.buildSessionFactory(); 
	}
	//All Hibernate Properties will be returned by this method
	private Properties getHibernateProperties() {
		// TODO Auto-generated method stub
		Properties properties = new Properties();
		properties.put("hibernate.dialect",DATABASE_DIALECT );
		properties.put("hibernate.show_sql","true"); 
		properties.put("hibernate.format_sql","true"); 
		
		return properties;
	}
	
	//transaction manager bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
		
	}
}
