package com.how2java.tmall.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.how2java.tmall.mapper.OrderItemMapper;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.how2java.tmall.mapper.OrderMapper;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderExample;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.service.UserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserService userService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public List<Order> list(){
        OrderExample example =new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result =orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }
    public void setUser(List<Order> os){//一对多对象建立联系----Order与User
            for (Order o : os)
                setUser(o);
    }
    public void setUser(Order o){
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")//事务管理导入
    public float add(Order o, List<OrderItem> ois) {
        float total = 0;
        add(o);
         /*
        if(true)
            throw new RuntimeException();
        */
        for (OrderItem oi: ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
        }
        return total;
    }
    @Override
    public List list(int uid, String excludedStatus) {
        OrderExample example =new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }

    @Override
    public List<Order> list(String name, String createDate) throws ParseException {
        if((name==null||name=="")&&(createDate==null||createDate==""))
        {
            List<Order> os=list();
            return  os;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderExample example=new OrderExample();

        if(name==null||name=="")
       {
           Date date1= format.parse(createDate+" 00:00:00");
           example.createCriteria().andCreateDateGreaterThan(date1);
           example.setOrderByClause("id desc");
           List<Order> os=orderMapper.selectByExample(example);
           setUser(os);
           return os;
       }
       if (createDate==null||createDate=="")
       {
           User user=userService.get(name);
           if(user==null)
               return null;
           example.createCriteria().andUidEqualTo(user.getId());
           example.setOrderByClause("id desc");
           List<Order> os=orderMapper.selectByExample(example);
           setUser(os);
           return os;

       }

        User user=userService.get(name);
        if (user==null)//根据已给名字找，找不到自然表为空
            return null;
        Date date1= format.parse(createDate+" 00:00:00");
       example.createCriteria().andUidEqualTo(user.getId()).andCreateDateGreaterThan(date1);
        example.setOrderByClause("id desc");
        List<Order> os=orderMapper.selectByExample(example);
        setUser(os);
        return os;
    }
}