package com.zpq.bigincident.service.impl;

import com.zpq.bigincident.mapper.CategoryMapper;
import com.zpq.bigincident.pojo.Category;
import com.zpq.bigincident.service.CategoryService;
import com.zpq.bigincident.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        category.setCreateUser(ThreadLocalUtil.getUserId());
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        return categoryMapper.selectList(ThreadLocalUtil.getUserId());
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryMapper.selectById(id,ThreadLocalUtil.getUserId());
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
