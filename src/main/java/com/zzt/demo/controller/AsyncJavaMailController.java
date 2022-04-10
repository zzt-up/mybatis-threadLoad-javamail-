package com.zzt.demo.controller;

import com.zzt.demo.javamail.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @Project mybatis, threadLoad, Stream, 自定义线程池，javamail练习
 * @PackageName com.zzt.demo.controller
 * @ClassName AsyncJavaMailController
 * @Author zzt
 * @Date 2020/11/04 11:54
 * @Description 测试异步发送邮件
 */
@RestController
@RequestMapping("asyncJavamail")
public class AsyncJavaMailController {

    @Autowired
    private EmailServiceImpl emailService;

    @Qualifier(value = "taskExecutor")
    private TaskExecutor taskExecutor;

    @GetMapping("sendEmail")
    public void sendEmail(){
        try {
            System.out.println("111111");
            int i  = 10;
            for (int i1 = 0; i1 < i; i1++) {
                emailService.sendEmail(i1);
            }
            System.out.println("22222222222222");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @GetMapping("sendEmail1")
    public void sendEmail1(){
        try {
            System.out.println("111111");
            int i  = 10;
            ThreadPoolTaskExecutor threadPool = (ThreadPoolTaskExecutor) taskExecutor;
            List<Integer> stringList = new ArrayList<>();
            for (int i1 = 0; i1 < i; i1++) {
                Future<Integer> submit = threadPool.submit(() -> 2);
                stringList.add(submit.get());
            }
            stringList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
