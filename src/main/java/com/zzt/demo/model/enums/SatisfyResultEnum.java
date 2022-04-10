package com.zzt.demo.model.enums;/**
 * @author 14034
 * @date 2020/10/14 16:38
 */

/**
 *@Project demo
 *@PackageName com.zzt.demo.model
 *@ClassName SatisfyResultEnum
 *@Author zzt
 *@Date 2020/10/14 16:38
 *@Description TODO
 */
public enum  SatisfyResultEnum implements EnumStatus {
    satisfy(101,"满意"),
    most_satisfy(102,"非常满意"),
    un_satisfy(103,"不满意");
    private int code;
    private String description;

    SatisfyResultEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
