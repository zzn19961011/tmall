package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Manager;
import com.how2java.tmall.pojo.User;

import java.util.List;

public interface ManagerService {
    void add(Manager m);
    void delete(int id);
    void update(Manager m);
    Manager get(int id);
    List list();
    boolean isExist(String name);
    Manager get(String name, String password);
}
