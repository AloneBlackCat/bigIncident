package com.zpq.bigincident.service.impl;

import com.zpq.bigincident.mapper.ArticleMapper;
import com.zpq.bigincident.pojo.Article;
import com.zpq.bigincident.service.ArticleService;
import com.zpq.bigincident.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateUser(ThreadLocalUtil.getUserId());
        articleMapper.add(article);
    }

    @Override
    public Article getArticle(Integer id) {
        return articleMapper.getArticle(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id,ThreadLocalUtil.getUserId());
    }
}
