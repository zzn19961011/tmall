package com.how2java.tmall.mapper;

import com.how2java.tmall.util.Page;
import com.how2java.tmall.pojo.Category;

import java.util.List;

public interface CategoryMapper {
     List<Category> list();


     void add(Category category);

     void delete(int id);

     Category get(int id);

     void update(Category caotegry);
}