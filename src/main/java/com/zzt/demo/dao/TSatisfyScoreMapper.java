package com.zzt.demo.dao;

import com.zzt.demo.model.TSatisfyScore;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface TSatisfyScoreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_satisfy_score
     *
     * @mbggenerated Wed Oct 14 16:23:25 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_satisfy_score
     *
     * @mbggenerated Wed Oct 14 16:23:25 CST 2020
     */
    int insert(TSatisfyScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_satisfy_score
     *
     * @mbggenerated Wed Oct 14 16:23:25 CST 2020
     */
    int insertSelective(TSatisfyScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_satisfy_score
     *
     * @mbggenerated Wed Oct 14 16:23:25 CST 2020
     */
    TSatisfyScore selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_satisfy_score
     *
     * @mbggenerated Wed Oct 14 16:23:25 CST 2020
     */
    int updateByPrimaryKeySelective(TSatisfyScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_satisfy_score
     *
     * @mbggenerated Wed Oct 14 16:23:25 CST 2020
     */
    int updateByPrimaryKey(TSatisfyScore record);




}