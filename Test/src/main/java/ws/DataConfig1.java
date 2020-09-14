package ws;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Configuration("DataConfig1")
@MapperScan(value = "ws.mapper1",sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataConfig1 {
    @Bean("druid1")
    @ConfigurationProperties("spring.datasource.druid1")
    public DataSource druid1(){
        return  new DruidDataSource();
    }

    //@Bean("transactionMange1")
    public DataSourceTransactionManager transactionMange1(@Qualifier("druid1") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("druid1") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
}
