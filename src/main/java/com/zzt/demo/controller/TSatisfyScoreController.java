package com.zzt.demo.controller;/**
 * @author 14034
 * @date 2020/10/14 13:54
 */

import com.zzt.demo.config.ThreadLocalContext;
import com.zzt.demo.dao.TSatisfyScoreMapper;
import com.zzt.demo.model.enums.SatisfyResultEnum;
import com.zzt.demo.model.TSatisfyScore;
import com.zzt.demo.service.Impl.TsatisfyScoreServiceImpl;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *@Project demo
 *@PackageName com.zzt.demo.controller
 *@ClassName TProductController
 *@Author zzt
 *@Date 2020/10/14 13:54
 *@Description TODO
 */
@RequestMapping("score")
@RestController
public class TSatisfyScoreController {
    @Autowired
    private TSatisfyScoreMapper tSatisfyScoreMapper;

    @Autowired
    private TsatisfyScoreServiceImpl tsatisfyScoreService;

    public static final ThreadLocal<Map<String,Integer>> mapThreadLocal = new ThreadLocal<>();

    //mybatis sql 语句构建器
    private String selectPersonSql() {
        return new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
            SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
            FROM("PERSON P");
            FROM("ACCOUNT A");
            INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
            INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
            WHERE("P.ID = A.ID");
            WHERE("P.FIRST_NAME like ?");
            OR();
            WHERE("P.LAST_NAME like ?");
            GROUP_BY("P.ID");
            HAVING("P.LAST_NAME like ?");
            OR();
            HAVING("P.FIRST_NAME like ?");
            ORDER_BY("P.ID");
            ORDER_BY("P.FULL_NAME");
        }}.toString();
    }

    @GetMapping("insert")
    public TSatisfyScore insert() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
        TSatisfyScore tSatisfyScore = new TSatisfyScore();
        // tSatisfyScore.setId(1111111111);
        tSatisfyScore.setInCall("1111111");
        tSatisfyScore.setUserId("1111111");
        tSatisfyScore.setCreateTime(simpleDateFormat.format(new Date()));
        tSatisfyScore.setSatisfyResultEnum(SatisfyResultEnum.satisfy);
        int insert = tSatisfyScoreMapper.insert(tSatisfyScore);
        return tSatisfyScore;
    }

    @GetMapping("insertSelective")
    public int insertSelective() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
        TSatisfyScore tSatisfyScore = new TSatisfyScore();
        tSatisfyScore.setInCall("1111111");
        tSatisfyScore.setUserId("1111111");
        tSatisfyScore.setCreateTime(simpleDateFormat.format(new Date()));
        tSatisfyScore.setSatisfyResultEnum(SatisfyResultEnum.satisfy);
        return tSatisfyScoreMapper.insertSelective(tSatisfyScore);
    }

    @GetMapping("findById/{id}")
    public TSatisfyScore findById(@PathVariable("id") String id) {
        TSatisfyScore tSatisfyScore = tSatisfyScoreMapper.selectByPrimaryKey(Integer.parseInt(id));
        return tSatisfyScore;
    }

    @PostMapping("update")
    public int findById(@RequestBody TSatisfyScore tSatisfyScore) {
        int i = tSatisfyScoreMapper.updateByPrimaryKeySelective(tSatisfyScore);
        return i;
    }

    /**
     *
     ThreadLocal表示线程的“局部变量”，它确保每个线程的ThreadLocal变量都是各自独立的；
     ThreadLocal适合在一个线程的处理流程中保持上下文（避免了同一参数在所有方法中传递）；
     使用ThreadLocal要用try ... finally结构，并在finally中清除。
     */

    @PostMapping("testThreadLocal")
    public void testThreadLocal() {
       Map<String,Integer> map = new HashMap<>(16);
       map.put("name",222);
       mapThreadLocal.set(map);
       try (ThreadLocalContext localContext = new ThreadLocalContext("测试")){
            String ctx = ThreadLocalContext.getCtx();
            System.out.println(ctx);
       } catch (Exception e) {
            e.printStackTrace();
        }
         testParam();
       //tsatisfyScoreService.testThreadLocal();
    }
   private void testParam(){
       Map<String, Integer> stringMap = mapThreadLocal.get();
       System.out.println(stringMap.toString());
       mapThreadLocal.remove();
   }

}





