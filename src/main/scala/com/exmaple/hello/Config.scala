package com.example.hello

import javax.sql.DataSource
import org.slf4j.LoggerFactory
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.{ Bean, Configuration }
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.{ EmbeddedDatabase, EmbeddedDatabaseBuilder, EmbeddedDatabaseType }
import org.springframework.orm.jpa.vendor.{ Database, HibernateJpaVendorAdapter }
import org.springframework.orm.jpa.{ JpaVendorAdapter, LocalContainerEntityManagerFactoryBean }
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{ EnableWebSecurity, WebSecurityConfigurerAdapter }


@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{
  val logger= LoggerFactory.getLogger(getClass)
  override def configure(http:HttpSecurity)={
    logger.info("customize web security started")
    http.authorizeRequests()
      .anyRequest().permitAll()
      .and()
      .formLogin()
  }
  override def configure(auth:AuthenticationManagerBuilder)={
    auth.inMemoryAuthentication()
      .withUser("user").password("{noop}pass").roles("USER")
  }

}

@Configuration
// @EnableJpaRepositories(basePackages=Array("com.example.hello"))
class Config{
  @Bean def jdbcOperation(dataSource:DataSource)=new JdbcTemplate(dataSource)
  @Bean def dataSoure=new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build()
  @Bean def entityManagerFactory(dataSource:DataSource,jpaVendorAdapter:JpaVendorAdapter)={
      val emfb=new LocalContainerEntityManagerFactoryBean()
      // emfb.setDataSource(dataSource)
      // emfb.setJpaVendorAdapter(jpaVendorAdapter)
      // emfb.setPackagesToScan("com.exmample.hello")
      emfb
    }
  @Bean def jpaVendorAdapter={
      val adapter=new HibernateJpaVendorAdapter
      adapter.setDatabase(Database.H2)
      adapter.setShowSql(true)
      adapter.setGenerateDdl(false)
      adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect")
      adapter
    }
}
