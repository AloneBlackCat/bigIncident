package com.zpq.bigincident.mapper;

import com.zpq.bigincident.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    void add(Article article);

    Article getArticle(Integer id);

    void update(Article article);

    void delete(@Param("id") Integer id, @Param("createUser") Integer createUser);

    List<Article> list(@Param("categoryId") Integer categoryId, @Param("state") String state, @Param("createUser") Integer userId);
}
