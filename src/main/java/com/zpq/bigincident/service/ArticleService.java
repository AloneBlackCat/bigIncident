package com.zpq.bigincident.service;

import com.zpq.bigincident.pojo.Article;
import com.zpq.bigincident.pojo.PageBean;

public interface ArticleService {

    void add(Article article);

    Article getArticle(Integer id);

    void update(Article article);

    void delete(Integer id);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
