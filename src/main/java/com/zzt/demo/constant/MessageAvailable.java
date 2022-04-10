package com.zzt.demo.constant;

/**
 * 可提取异常信息接口类
 *
 * @author fzw
 * @date 2020/05/22 14:03
 */
public interface MessageAvailable {

    /**
     * 取得错误信息
     *
     * @return 错误信息
     */
    String getMsg();

    /**
     * 返回状态码
     *
     * @return 状态码
     */
    String getCode();

}
