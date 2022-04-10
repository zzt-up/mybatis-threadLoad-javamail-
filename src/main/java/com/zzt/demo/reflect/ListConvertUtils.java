package com.zzt.demo.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Project workstream
 * @PackageName com.isoftstone.util
 * @ClassName ListConvertUtil
 * @Author zzt
 * @Date 2020/12/15 16:33
 * @Description 复制list工具类
 */
public final class ListConvertUtils {
    /**
     * 日志对象
     **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ListConvertUtil.class);

    private ListConvertUtils() {
    }


    public static <T, K extends List> void copyList(K k, List<T> targetList, Class<T> targetClass) {
        copyListField(k, targetList, targetClass);

    }

    public static <T, K extends List> List<T> copyListResult(K k, List<T> targetList, Class<T> targetClass) {
        copyListField(k, targetList, targetClass);
        return targetList;
    }
    public static <T, K extends List> List<T> copyListResultWithOutList(K k, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        copyListField(k, targetList, targetClass);
        return targetList;
    }

    public static <T, K extends List>void copyListResultWithOutType(K k, T targetList) {

        Class<T> clazz = getSuperClassGenricType(targetList.getClass(),0);
        System.out.println(clazz);

    }

    public static void main(String[] args) {
        List<WorkOrderDTO> myCreateWoDTOS = new ArrayList<>();
        copyListResultWithOutType(myCreateWoDTOS,new WorkOrderVO());
    }



    private static <T, K extends List> void copyListField(K k, List<T> targetList, Class<T> targetClass) {
        if ((!Objects.isNull(k)) && (!Objects.isNull(targetList))) {
            k.forEach(item -> {
                try {
                    T data = targetClass.newInstance();
                    BeanUtils.copyProperties(item, data);
                    targetList.add(data);
                } catch (InstantiationException | IllegalAccessException e) {
                    LOGGER.error("copy properties error：{}", e.getMessage());
                }
            });
        }
    }

    private static Class getSuperClassGenricType(Class clazz, int index)
            throws IndexOutOfBoundsException {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }


}
