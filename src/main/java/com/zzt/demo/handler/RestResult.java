package com.zzt.demo.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzt.demo.constant.ErrorMessage;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * @ClassName RestResult
 * @Description TODO(统一返回Rest风格的数据结构)
 * @Author huxiaolAong
 * @Date 2020/5/15 9:13
 * @Version 1.0
 **/
@Getter
@Setter
public class RestResult<T> {

    public static final String SUCCESS_CODE = "0";

    public static final String FAIL_CODE = "-1";

    public static final String ERROR_DATE = "-3";

    private String code;                    //成功或者失败的code错误码
    private T data;                    //成功时返回的数据，失败时返回具体的异常信息
    private String msg;                     //请求失败返回的提示信息，给前端进行页面展示的信息
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Timestamp currentTime;          //服务器当前时间

    public RestResult() {
    }

    public RestResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.currentTime = new Timestamp(new DateTime().getMillis());
    }

    public RestResult(Integer code, String msg) {
        this.code = code.toString();
        this.msg = msg;
        this.currentTime = new Timestamp(new DateTime().getMillis());
    }

    public RestResult(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.currentTime = new Timestamp(new DateTime().getMillis());
    }

    public RestResult(ErrorMessage message, T data) {
        this.code = message.getCode();
        this.data = data;
        this.msg = message.getMsg();
        this.currentTime = new Timestamp(new DateTime().getMillis());
    }

    @Override
    public String toString() {
        return "RestResult{" +
                ", code='" + code + '\'' +
                ", data=" + data +
                ", msg=" + msg +
                ", currentTime=" + currentTime +
                '}';
    }

}
