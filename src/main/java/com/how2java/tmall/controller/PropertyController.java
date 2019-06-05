package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;
import com.how2java.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping("admin_property_list")
    public String list(int cid,Model model, Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);
        Category category=categoryService.get(cid);
        int total = (int) new PageInfo<>(ps).getTotal();;
        page.setTotal(total);

        model.addAttribute("ps", ps);
        model.addAttribute("page", page);
        model.addAttribute("c",category);
        return "admin/listProperty";
    }
    //add与update为一类，传入一个对象
    @RequestMapping("admin_property_add")
    public String add(Property p) {

        propertyService.add(p);
        return "redirect:admin_property_list?cid="+p.getCid();
    }
    @RequestMapping("admin_property_update")
    public String update(Property p){
        propertyService.update(p);
        return "redirect:admin_property_list?cid="+p.getCid();
    }
    //edit需要model层支持，因为下一层是一个页面
    @RequestMapping("admin_property_edit")
    public  String edit(Model model,int id)
    {
        Property p=propertyService.get(id);
        Category c=categoryService.get(p.getCid());
        model.addAttribute("p",p);
        model.addAttribute("c",c);
        return "admin/editProperty";

    }

    @RequestMapping("admin_property_delete")
    public String delete(int id) {
        Property p = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?cid="+p.getCid();
    }


}
