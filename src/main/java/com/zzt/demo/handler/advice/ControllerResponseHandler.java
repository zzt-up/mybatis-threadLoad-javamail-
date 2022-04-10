package com.zzt.demo.handler.advice;

import com.alibaba.fastjson.JSON;
import com.zzt.demo.constant.ErrorMessage;
import com.zzt.demo.handler.RestResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Project workstream
 * @PackageName com.isoftstone.aspect
 * @ClassName ControllerResponseHandler
 * @Author zzt
 * @Date 2020/12/16 15:22
 * @Description TODO
 */
@ControllerAdvice
public class ControllerResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
       if(o instanceof RestResult){
           return o;
       }
       //返回string类型需要特殊处理
       if(o instanceof String){
           RestResult restResult = new RestResult(RestResult.SUCCESS_CODE, o, ErrorMessage.SUCCESS_OPTION.getMsg());
           return JSON.toJSONString(restResult);

       }
        RestResult restResult = new RestResult(RestResult.SUCCESS_CODE, o, ErrorMessage.SUCCESS_OPTION.getMsg());
        System.out.println("---------"+JSON.toJSON(restResult));
        return restResult;

    }
}
