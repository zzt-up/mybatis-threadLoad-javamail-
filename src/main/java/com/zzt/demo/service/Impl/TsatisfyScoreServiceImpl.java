package com.zzt.demo.service.Impl;/**
 * @author 14034
 * @date 2020/10/21 15:28
 */

import com.zzt.demo.controller.TSatisfyScoreController;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *@Project demo
 *@PackageName com.zzt.demo.service.Impl
 *@ClassName TsatisfyScoreServiceImpl
 *@Author zzt
 *@Date 2020/10/21 15:28
 *@Description TODO
 */
@Service
public class TsatisfyScoreServiceImpl {


    public void testThreadLocal() {
        Map<String, Integer> stringIntegerMap = TSatisfyScoreController.mapThreadLocal.get();
        try {
            System.out.println(stringIntegerMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TSatisfyScoreController.mapThreadLocal.remove();
        }



    }
}
