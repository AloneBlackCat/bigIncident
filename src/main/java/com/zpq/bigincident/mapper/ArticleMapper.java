package com.zpq.bigincident.mapper;

import com.zpq.bigincident.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {

    void add(Article article);

    Article getArticle(Integer id);

    void update(Article article);

    void delete(@Param("id") Integer id, @Param("createUser") Integer createUser);
}
