package com.zzt.demo.annotation;
import com.zzt.demo.controller.TSatisfyScoreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.lang.reflect.Method;

/**
 * @Project demo
 * @PackageName com.zzt.demo.annotation
 * @ClassName RequestParamUtil
 * @Author zzt
 * @Date 2020/10/22 14:15
 * @Description 注解练习
 */

public class RequestParamUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestParamUtil.class);

    public static String getRequestParamByAnnotation() {

        Method[] declaredMethods = TSatisfyScoreController.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(PostMapping.class)) {
                PostMapping declaredAnnotation = declaredMethod.getDeclaredAnnotation(PostMapping.class);
                String[] value = declaredAnnotation.value();
                for (String s : value) {
                    logger.info(s);
                }
            } else if (declaredMethod.isAnnotationPresent(GetMapping.class)) {
                GetMapping declaredAnnotation = declaredMethod.getDeclaredAnnotation(GetMapping.class);
                String[] value = declaredAnnotation.value();
                for (String s : value) {
                    logger.info(s);
                }
            }

        }
        return null;

    }

    public static void main(String[] args) {
        getRequestParamByAnnotation();
    }
}
