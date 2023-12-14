package com.zpq.bigincident.service;

import com.zpq.bigincident.pojo.Article;

public interface ArticleService {

    void add(Article article);

    Article getArticle(Integer id);

    void update(Article article);

    void delete(Integer id);
}
