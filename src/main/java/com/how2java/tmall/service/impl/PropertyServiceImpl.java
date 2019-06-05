package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.CategoryMapper;
import com.how2java.tmall.mapper.PropertyMapper;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyExample;
import com.how2java.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public List<Property> list(int cid) {
        PropertyExample propertyExample=new PropertyExample();
        PropertyExample.Criteria criteria=propertyExample.createCriteria();
        criteria.andCidEqualTo(cid);
        propertyExample.setOrderByClause("id asc");
        return  propertyMapper.selectByExample(propertyExample);
    }

    @Override
    public void add(Property property) {
        propertyMapper.insertSelective(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Property get(int id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKeySelective(property);
    }
}
