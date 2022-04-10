package com.zzt.demo.javamail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Project mybatis, threadLoad, Stream, 自定义线程池，javamail练习
 * @PackageName com.zzt.demo.javamail
 * @ClassName EmailServiceImpl
 * @Author zzt
 * @Date 2020/11/04 11:41
 * @Description 异步调用邮件发送
 */
@Component
@Service
public class EmailServiceImpl {
    @Qualifier(value = "taskExecutor")
   //@Autowired
    private TaskExecutor taskExecutor;
    public static ConcurrentHashMap<String, List<String>> customerServerUserMap = new ConcurrentHashMap<>();

    @Async()
    //@Async("taskExecutor")
    public void sendEmail(int i) throws Exception {
   /* SendMailWithAttachment.main(new String []{});*/
        Thread.sleep(5000);
        //String name = Thread.currentThread().getName();
        /*ThreadPoolTaskScheduler taskExecutor = (ThreadPoolTaskScheduler) this.taskExecutor;
        taskExecutor.submit(()-> System.out.println(Thread.currentThread().getName()));*/
        System.out.println(Thread.currentThread().getName()+"---"+i);
        //ThreadPoolTaskExecutor threadPool = (ThreadPoolTaskExecutor) taskExecutor;
        //threadPool.submit(()-> System.out.println(Thread.currentThread().getName()));
        //System.out.println("---"+ name);
    }

    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("a","b");
        map.remove("c");
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        customerServerUserMap.put("aa", stringList);
        List<String> stringList1 = customerServerUserMap.get("aa");
        stringList1.remove("2");
        System.out.println(customerServerUserMap.get("aa"));
        //输出---> [1,3]
    }
}
