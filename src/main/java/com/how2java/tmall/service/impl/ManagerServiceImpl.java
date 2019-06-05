package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.ManagerMapper;
import com.how2java.tmall.pojo.Manager;
import com.how2java.tmall.pojo.ManagerExample;
import com.how2java.tmall.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
     @Autowired
    ManagerMapper managerMapper;

    @Override
    public void add(Manager m) {
        managerMapper.insert(m);
    }

    @Override
    public void delete(int id) {
    managerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Manager m) {
    managerMapper.updateByPrimaryKeySelective(m);
    }

    @Override
    public Manager get(int id) {

        return managerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list() {
        ManagerExample managerExample=new ManagerExample();
        managerExample.setOrderByClause("id desc");
        return managerMapper.selectByExample(managerExample);
    }

    @Override
    public boolean isExist(String name) {
          ManagerExample managerExample=new ManagerExample();
          managerExample.createCriteria().andNameEqualTo(name);
          List<Manager> ms=managerMapper.selectByExample(managerExample);
          if(ms.isEmpty())
              return false;
          return true;
    }

    @Override
    public Manager get(String name, String password) {
        ManagerExample managerExample=new ManagerExample();
        managerExample.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<Manager> ms=managerMapper.selectByExample(managerExample);
        if(ms.isEmpty())
            return null;
        return ms.get(0);
    }
}
