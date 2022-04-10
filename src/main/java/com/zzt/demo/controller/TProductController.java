package com.zzt.demo.controller;/**
 * @author 14034
 * @date 2020/10/14 13:54
 */

import cn.hutool.json.JSONUtil;
import com.zzt.demo.dao.TProductMapper;
import com.zzt.demo.model.Blog;
import com.zzt.demo.model.Page;
import com.zzt.demo.model.TProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *@Project demo
 *@PackageName com.zzt.demo.controller
 *@ClassName TProductController
 *@Author zzt
 *@Date 2020/10/14 13:54
 *@Description TODO
 */
@RequestMapping("product")
@RestController
public class TProductController {
    @Autowired
    private TProductMapper productMapper;
    @GetMapping("findById/{id}")
    public TProduct findById(@PathVariable("id") String id){
        //return productMapper.selectByPrimaryKey1(id);
        return productMapper.selectByPrimaryKey(id);
    }
    @RequestMapping("/getAllBook")
    @ResponseBody
    public Page getAllBook(String pageNo, String pageSize, HttpServletRequest request, HttpServletResponse response){
       // pageNo=pageNo==null?"1":pageNo;   //当前页码
        //pageSize=pageSize==null?"5":pageSize;   //页面大小
        //获取当前页数据
        List<TProduct> list = productMapper.getAllBookByPage(pageNo,pageSize);
        //获取总数据大小
       // int totals = productMapper.getAllBook();
        //封装返回结果
        Page page = new Page();
        //page.setTotal(totals+"");
        page.setRows(list);
        return page;
    }
    @RequestMapping("/getAllBook1")
    @ResponseBody
    public boolean getAllBook1(@RequestBody Blog blog){
        System.out.println(JSONUtil.toJsonStr(blog));
        return true;
    }
}
