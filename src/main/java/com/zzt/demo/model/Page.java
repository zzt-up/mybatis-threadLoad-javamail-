package com.zzt.demo.model;/**
 * @author 14034
 * @date 2020/10/20 14:19
 */

import java.util.List;

/**
 *@Project demo
 *@PackageName com.zzt.demo.model
 *@ClassName Page
 *@Author zzt
 *@Date 2020/10/20 14:19
 *@Description TODO
 */
public class Page {


    private String pageNo = null;
    private String pageSize = null;
    private String total = null;
    private List rows = null;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
