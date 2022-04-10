package com.zzt.demo.reflect;

import lombok.Data;

import java.util.Date;

/**
 * 工单信息表(WorkOrderMessage)实体类
 *
 * @author zy
 * @since 2020-07-02 17:21:19
 */
@Data
public class WorkOrderVO {
    private Date createDate;
    public Date getCreateDate() {
        return createDate == null ? null : (Date) createDate.clone();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate == null ? null : (Date) createDate.clone();
    }
}
