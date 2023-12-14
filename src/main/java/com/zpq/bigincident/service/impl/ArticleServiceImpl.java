package com.zpq.bigincident.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zpq.bigincident.mapper.ArticleMapper;
import com.zpq.bigincident.pojo.Article;
import com.zpq.bigincident.pojo.PageBean;
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

    // 条件分页查询
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1. 创建PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        // 2. 开启分页
        PageHelper.startPage(pageNum,pageSize);
        // 3. 调用mapper,并强转为Page对象
        Page<Article> list = (Page<Article> ) articleMapper.list(categoryId, state, ThreadLocalUtil.getUserId());
        // 4. 填充数据
        pageBean.setTotal(list.getTotal());
        pageBean.setItems(list.getResult());
        return pageBean;
    }
}
