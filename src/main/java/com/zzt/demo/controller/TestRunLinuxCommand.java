package com.zzt.demo.controller;

import com.zzt.demo.javamail.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
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
@RequestMapping("linux")
@Slf4j
public class TestRunLinuxCommand {

    @Autowired
    private EmailServiceImpl emailService;

    @Qualifier(value = "taskExecutor")
    private TaskExecutor taskExecutor;

    @GetMapping("command")
    public String sendEmail(){
        Process process;
        String command = null;
        try {
             command = "tar -zxf tomcat8.tar.gz";

            process = Runtime.getRuntime().exec(command,null,new File("/www"));
        } catch (IOException e) {
            e.printStackTrace();
            return command+e.getMessage();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return command+e.getMessage();
        }
        return command;

    }


}
