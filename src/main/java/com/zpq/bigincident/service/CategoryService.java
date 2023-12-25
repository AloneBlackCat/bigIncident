package com.zpq.bigincident.service;

import com.zpq.bigincident.pojo.Category;

import java.util.List;

public interface CategoryService {

    void add(Category category);

    List<Category> list();

    Category getCategory(Integer id);

    void update(Category category);

    void delete(Integer id);
}
