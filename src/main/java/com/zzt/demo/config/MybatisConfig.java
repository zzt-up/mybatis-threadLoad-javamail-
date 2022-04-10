package com.zzt.demo.config;/**
 * @author 14034
 * @date 2020/10/15 09:53
 */

import com.zzt.demo.interceptor.ExamplePluginInterceptor;
import com.zzt.demo.interceptor.MyPageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 *@Project demo
 *@PackageName com.zzt.demo
 *@ClassName MybatisConfig
 *@Author zzt
 *@Date 2020/10/15 09:53
 *@Description mybatis 配置
 */
@Configuration
public class MybatisConfig {

    /**
     * @Description:  将mybatis自定义拦截器,记录日志使用的 注入spring
     * @Author: zhouzhengtao
     * @Date: 2020/10/15

     * @return: com.zzt.demo.interceptor.ExamplePluginInterceptor
     **/
    @Bean
    public ExamplePluginInterceptor examplePlugin(){
        ExamplePluginInterceptor sqlStatsInterceptor = new ExamplePluginInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        sqlStatsInterceptor.setProperties(properties);
        return sqlStatsInterceptor;
    }
    /**
     * @Description:  将mybatis 自定义分页插件注入spring，并设置默认分页属性
     * @Author: zhouzhengtao
     * @Date: 2020/10/20
     * @return: com.zzt.demo.interceptor.MyPageInterceptor
     **/
    @Bean
    public MyPageInterceptor myPageInterceptor(){
        MyPageInterceptor sqlStatsInterceptor = new MyPageInterceptor();
        Properties properties = new Properties();
        // 预先设置好需要的一些预定属性，参数中没有就走这里取参数
        properties.setProperty("dialect", "mysql");
        properties.setProperty("limit", "10");
        properties.setProperty("currPage", "1");
        sqlStatsInterceptor.setProperties(properties);
        return sqlStatsInterceptor;
    }
}
