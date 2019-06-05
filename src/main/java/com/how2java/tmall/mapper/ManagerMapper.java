package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Manager;
import com.how2java.tmall.pojo.ManagerExample;
import java.util.List;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}