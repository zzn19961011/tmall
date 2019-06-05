package com.how2java.tmall.service;

        import com.how2java.tmall.pojo.Category;
        import com.how2java.tmall.util.Page;
        import java.util.List;

public interface CategoryService{//service中的条目应该由数据库及项目要求、前端综合确定

    List<Category> list();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}