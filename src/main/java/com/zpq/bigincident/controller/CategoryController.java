package com.zpq.bigincident.controller;

import com.zpq.bigincident.pojo.Category;
import com.zpq.bigincident.pojo.Result;
import com.zpq.bigincident.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public <T> Result<T> list() {
        List<Category> categoryList = categoryService.list();
        return Result.success(categoryList);
    }

    @GetMapping("/detail")
    public <T> Result<T> getCategory(@RequestParam Integer id) {
        return Result.success(categoryService.getCategory(id));
    }

    @PostMapping
    public <T> Result<T> add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @PutMapping
    public <T> Result<T> update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return  Result.success();
    }

}
