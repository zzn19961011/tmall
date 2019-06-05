package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Manager;
import com.how2java.tmall.service.ManagerService;
import com.how2java.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class ManagerController {
  @Autowired
    ManagerService managerService;

  @RequestMapping("managercheck")
    public  String managercheck(Model model, @RequestParam("name") String name, @RequestParam("password") String password, HttpSession session){
      name = HtmlUtils.htmlEscape(name);
      Manager manager=managerService.get(name, password);
      if(null==manager){
          model.addAttribute("msg", "账号密码错误");
          return "admin/managerlogin";
      }
      session.setAttribute("manager", manager);
      return "redirect:admin_category_list";
  }
  @RequestMapping("managerlogout")
  public String logout( HttpSession session) {
    Manager manager=(Manager)session.getAttribute("manager");
    manager.setPower(0);
    managerService.update(manager);
    session.removeAttribute("manager");
    return "redirect:managerlogin";

  }
  @RequestMapping("admin_manager_checkpower")
  public  String managercheckpower(HttpSession session){
    Manager manager=(Manager)session.getAttribute("manager");
    if (manager.getPower()==0)
      return "admin/managercheckpower";
    else
      return "redirect:admin_manager_list"+"?powerpass=666";
  }
  @RequestMapping("admin_manager_list")
  public  String managerlist(Page page, Model model,String powerpass,HttpSession session){
    if (!powerpass.equals("666"))
      return "redirect:admin_category_list";
    Manager manager=(Manager)session.getAttribute("manager");
    manager.setPower(1);
    session.setAttribute("manager",manager);
    PageHelper.offsetPage(page.getStart(), page.getCount());
    List<Manager> ms = managerService.list();//一对一的关系可以写在在创建list时
    int total = (int) new PageInfo<>(ms).getTotal();
    page.setTotal(total);
    model.addAttribute("ms",ms);
    model.addAttribute("page",page);
    return  "admin/listManager";
  }
  @RequestMapping("admin_manager_add")
  public String manageradd(Manager manager)
  {
    managerService.add(manager);
    return "redirect:admin_category_list";

  }

}
