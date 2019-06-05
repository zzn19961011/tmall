package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @RequestMapping("admin_order_list")
    public  String list(Model model, Page page)//需要一个订单列表
    {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Order> os = orderService.list();//一对一的关系可以写在在创建list时
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);
        orderItemService.fill(os);//一对多的关系需要调用多的一方的服务
        model.addAttribute("os",os);
        model.addAttribute("page",page);
        return  "admin/listOrder";
    }
    @RequestMapping("admin_order_delivery")
    public  String delivery(Order o)//需要发货操作
    {
        o.setDeliveryDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return "redirect:admin_order_list";
    }
    @RequestMapping("admin_order_search")
    public  String search(Model model, Page page, @RequestParam("name") String name,@RequestParam("createDate") String date) throws ParseException {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        if((name==null||name=="")&&(date==null||date==""))
            return "redirect:admin_order_list";
        List<Order> os = orderService.list(name,date);//一对一的关系可以写在在创建list时
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);

        if (os!=null)
            orderItemService.fill(os);
        model.addAttribute("os",os);
        model.addAttribute("page",page);
        return  "admin/listOrder";
    }
}
