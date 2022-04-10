package com.zzt.demo.constant;


/**
 * @ClassName ErrorMessage
 * @Description TODO(错误提示集合类)
 * @Author huxiaolong
 * @Date 2020/5/15 9:33
 * @Version 1.0
 **/
public enum ErrorMessage implements MessageAvailable {

    SYSTEM_ERROR("-001","系统异常"),
    BAD_REQUEST("-002","错误的请求参数"),
    NOT_FOUND("-003","找不到请求路径！"),
    CONNECTION_ERROR("-004","网络连接请求失败！"),
    METHOD_NOT_ALLOWED("-005","不合法的请求方式"),
    DATABASE_ERROR("-004","数据库异常"),
    BOUND_STATEMENT_NOT_FOUNT("-006","找不到方法！"),
    REPEAT_REGISTER("001","重复注册"),
    NO_USER_EXIST("002","用户不存在"),
    INVALID_PASSWORD("003","密码错误"),
    NO_PERMISSION("004","非法请求！"),
    SUCCESS_OPTION("0","操作成功！"),
    NOT_MATCH("007","用户名和密码不匹配"),
    FAIL_GETDATA("008","获取信息失败"),
    BAD_REQUEST_TYPE("009","错误的请求类型"),
    INVALID_MOBILE("010","无效的手机号码"),
    INVALID_EMAIL("011","无效的邮箱"),
    INVALID_GENDER("012","无效的性别"),
    REPEAT_MOBILE("014","已存在此手机号"),
    REPEAT_EMAIL("015","已存在此邮箱地址"),
    NO_RECORD("016","没有查到相关记录"),
    LOGIN_SUCCESS("017","登陆成功"),
    LOGOUT_SUCCESS("018","已退出登录"),
    SENDEMAIL_SUCCESS("019","邮件已发送，请注意查收"),
    EDITPWD_SUCCESS("020","修改密码成功"),
    No_FileSELECT("021","未选择文件"),
    FILEUPLOAD_SUCCESS("022","上传成功"),
    NOLOGIN("023","未登陆"),
    ILLEGAL_ARGUMENT("024","参数不合法"),
    ERROR_IDCODE("025","验证码不正确"),
    ACCOUNT_DISABLE("026","账户已被禁用"),
    LOGIN_DATE_TIPS("034","请5分钟后再登陆！"),
    AD_VERIFICATION_FAIL("035","域验证失败！"),
    USER_NOT_REGISTER("036","用户未注册！"),

    ADD_SUCCESS("027","新增成功"),
    ADD_FAIL("028","新增失败"),
    UPDATE_SUCCESS("029","修改成功"),
    UPDATE_FAIL("030","修改失败"),
    SELECT_SUCCESS("031","查询成功"),
    SELECT_FAIL("032","查询失败"),
    TRAFFIC_DISTRIBUTION("033","客服全忙，请稍后..."),

    ALREADY_LOGIN("303","您已经登录了"),
    NULL_ERROR("404","空指针异常"),
    OTHER_ERROR("500","服务器内部错误，请查看日志");

    private String code;
    private String msg;

    ErrorMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
