package com.zzt.demo.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;

/**
 * @Project demo
 * @PackageName com.zzt.demo.reflect
 * @ClassName ProxyNewProxyInstance
 * @Author zzt
 * @Date 2020/10/23 09:42
 * @Description 动态代理，在运行期动态创建某个interface的实例
 *
 *
 * 在运行期动态创建一个interface实例的方法如下：
定义一个InvocationHandler实例，它负责实现接口的方法调用；
通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
使用的ClassLoader，通常就是接口类的ClassLoader；
需要实现的接口数组，至少需要传入一个接口进去；
用来处理接口方法调用的InvocationHandler实例。
将返回的Object强制转型为接口。
动态代理实际上是JDK在运行期动态创建class字节码并加载的过程
 *
 *
 *
 *
 */
public class ProxyNewProxyInstance {
    private static final Logger  logger= LoggerFactory.getLogger(ProxyNewProxyInstance.class);

    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                logger.info(method.getName());
                if(method.getName().equals("morning")){
                    logger.info("god morning:{}",args[0]);
                }
                return args[0];
            }
        };
        //传入ClassLoader   // 传入要实现的接口    // 传入处理调用方法的InvocationHandler
        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(),new Class[]{Hello.class},handler);
        String bob = hello.morning("bob");
        System.out.println(bob);
    }

}
