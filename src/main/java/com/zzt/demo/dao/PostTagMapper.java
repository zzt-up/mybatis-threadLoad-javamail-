package com.zzt.demo.dao;

import com.zzt.demo.model.PostTag;

public interface PostTagMapper {
    int insert(PostTag record);

    int insertSelective(PostTag record);
}