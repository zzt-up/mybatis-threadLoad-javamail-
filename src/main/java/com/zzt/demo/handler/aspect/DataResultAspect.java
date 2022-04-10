package com.zzt.demo.handler.aspect;

import com.alibaba.fastjson.JSON;
import com.zzt.demo.constant.ErrorMessage;
import com.zzt.demo.handler.RestResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @Project workstream
 * @PackageName com.isoftstone.util
 * @ClassName DataResultAspect
 * @Author zzt
 * @Date 2020/12/16 09:53
 * @Description controller返回数据封装切面
 */
@Aspect
@Component
@Order(-99)
public class DataResultAspect {

    private final static Logger logger = LoggerFactory.getLogger(DataResultAspect.class);

    @Pointcut("execution(public * com.zzt.demo.controller.*.*(..))")
    public void dataResult(){

    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restPointcut() {
    }
    /**
     * @Description: 返回通知：目标方法正常执行完毕时执行以下代码
     * @Author: zhouzhengtao
     * @Date: 2020/12/16
     * @param proceedingJoinPoint:
     * @return: java.lang.Object
     **/
    @Around(value = "dataResult() && restPointcut()")
    public Object addDataResult(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object result = proceedingJoinPoint.proceed();
        if(!(result instanceof RestResult)) {
            if(result instanceof String){
                RestResult restResult = new RestResult(RestResult.SUCCESS_CODE, result, ErrorMessage.SUCCESS_OPTION.getMsg());
                return JSON.toJSONString(restResult);
            }
            return new RestResult(RestResult.SUCCESS_CODE, result, ErrorMessage.SUCCESS_OPTION.getMsg());
        }
        //todo 强制转换成其它类型会报错，ControllerResponseHandler（@ControllerAdvice）不会
        logger.info("--------------------------:{}", JSON.toJSONString(result));
        return result;
    }

    }
