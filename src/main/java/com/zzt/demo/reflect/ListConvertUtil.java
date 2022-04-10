package com.zzt.demo.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Project workstream
 * @PackageName com.isoftstone.util
 * @ClassName ListConvertUtil
 * @Author zzt
 * @Date 2020/12/15 16:33
 * @Description 复制list工具类,思考泛型使用场景， 一般穿object都可以对其进行限定
 */
public final class ListConvertUtil {
    /**
     * 日志对象
     **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ListConvertUtil.class);

    private ListConvertUtil() {
    }

    public static <T> void copyListObject(Object object,List<T> targetList, Class<T> targetClass) {
        if ((!Objects.isNull(object)) && (!Objects.isNull(targetList))) {
            List sourceList = (List)object;
            sourceList.forEach(item -> {
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


    public static <T,K> void copyListKList(List<K> k,List<T> targetList, Class<T> targetClass) {
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

    public static <T, K extends List> void copyList(K k, List<T> targetList, Class<T> targetClass) {
        copyListField(k, targetList, targetClass);

    }

    public static <T, K extends List> List<T> copyListResult(K k, List<T> targetList, Class<T> targetClass) {
        copyListField(k, targetList, targetClass);
        return targetList;
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

    public static void main(String[] args) {
        WorkOrderDTO taskTextVariableDTO = new WorkOrderDTO();
        taskTextVariableDTO.setCreateDate(new Date());
        List<WorkOrderDTO> list = new ArrayList<>();
        list.add(taskTextVariableDTO);
        List<WorkOrderDTO> textVariableVOS = new ArrayList<>();
        //ListConvertUtil.copyList(list,textVariableVOS,WorkOrderDTO.class);
        //ListConvertUtil.copyListKList(list,textVariableVOS,WorkOrderDTO.class);
        ListConvertUtil.copyListObject(list,textVariableVOS,WorkOrderDTO.class);
        textVariableVOS.forEach(a->System.out.print(a.getCreateDate()));
    }
}
