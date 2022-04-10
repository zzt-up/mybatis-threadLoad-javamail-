package com.zzt.demo.interceptor;/**
 * @author 14034
 * @date 2020/10/15 09:47
 */

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 *@Project demo
 *@PackageName com.zzt.demo.interceptor
 *@ClassName ExamplePluginInterceptor
 *@Author zzt
 *@Date 2020/10/15 09:47
 *@Description MyBatis 用自定义插件来拦截的方法调用   把Mybatis所有执行的sql都记录下来。
 */
/*@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class})})*/
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})

public class ExamplePluginInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler= (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        System.out.println("mybatis 自定义拦截器，记录sql: "+boundSql.getSql());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("dialect");
        System.out.println("dialect="+dialect);
    }
}
