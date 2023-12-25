package com.zpq.bigincident.mapper;

import com.zpq.bigincident.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    void add(Category category);

    List<Category> selectList(Integer createUser);

    Category selectById(@Param("id") Integer id, @Param("createUser") Integer createUser);

    void update(Category category);

    void delete(Integer id);
}
