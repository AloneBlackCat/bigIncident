package com.zpq.bigincident.controller;

import com.zpq.bigincident.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result<String> list() {
        // 验证token
        return Result.success("所以的文章数据...");
    }
}
