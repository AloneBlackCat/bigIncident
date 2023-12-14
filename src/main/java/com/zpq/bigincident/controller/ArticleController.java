package com.zpq.bigincident.controller;

import com.zpq.bigincident.pojo.Article;
import com.zpq.bigincident.pojo.PageBean;
import com.zpq.bigincident.pojo.Result;
import com.zpq.bigincident.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public <T> Result<T> list(Integer pageNum,
                              Integer pageSize,
                              /*@RequestParam(required = false)*/ Integer categoryId,
                              /*@RequestParam(required = false)*/ String state) {
        PageBean<Article> articleList = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(articleList);
    }

    @GetMapping
    public <T> Result<T> getArticle(Integer id) {
        return Result.success(articleService.getArticle(id));
    }

    @PostMapping
    public <T> Result<T> add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @PutMapping
    public <T> Result<T> update(@RequestBody @Validated(Article.Update.class) Article article) {
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public <T> Result<T> delete(Integer id) {
        articleService.delete(id);
        return Result.success();
    }
}
