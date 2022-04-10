package com.zzt.demo.config;/**
 * @author 14034
 * @date 2020/10/21 16:11
 */

/**
 *@Project demo
 *@PackageName com.zzt.demo.config
 *@ClassName ThreadLocalContext
 *@Author zzt
 *@Date 2020/10/21 16:11
 *@Description 保证能释放ThreadLocal关联的实例，我们可以通过AutoCloseable接口配合try (resource) {...}结构，让编译器自动为我们关闭
 */
public class ThreadLocalContext implements AutoCloseable {

   private static final ThreadLocal<String> CTX = new ThreadLocal<>();

    public ThreadLocalContext(String str){
        CTX.set(str);
    }

    public static String getCtx(){
        return CTX.get();
    }

    @Override
    public void close() throws Exception {
        System.out.println("CTX remove");
        CTX.remove();
    }
}
