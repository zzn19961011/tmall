package com.how2java.tmall.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.how2java.tmall.pojo.*;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.OrderService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;
import com.how2java.tmall.util.UploadedImageFile;

@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderService orderService;

    @RequestMapping("admin_productImage_add")
    public String add(ProductImage pi, HttpSession session, UploadedImageFile uploadedImageFile) {
        //先加入数据库中
        productImageService.add(pi);
        //文件操作
        //提供路径与文件名
        String fileName = pi.getId() + ".jpg";//文件名
        String imageFolder;//对于单个图片而言设置三个大小
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.type_single.equals(pi.getType())) {
            //部署到服务器上，重新部署临时文件消失
            /*imageFolder= session.getServletContext().getRealPath("img/productSingle");//从session中获取真实路径
            imageFolder_small= session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle= session.getServletContext().getRealPath("img/productSingle_middle");*/
            imageFolder = "D:\\severimage\\productSingle";
            imageFolder_small = "D:\\severimage\\productSingle_small";
            imageFolder_middle = "D:\\severimage\\productSingle_middle";
        } else {
            //imageFolder= session.getServletContext().getRealPath("img/productDetail");
            imageFolder = "D:\\severimage\\productDetail";
        }

        File f = new File(imageFolder, fileName);
        f.getParentFile().mkdirs();//根据文件流创建目录
        try {
            uploadedImageFile.getImage().transferTo(f);//从upload文件流中获取img并传输到文件夹中（二进制文件)
            BufferedImage img = ImageUtil.change2jpg(f);//通过ImageUtil工具转换成jpg
            ImageIO.write(img, "jpg", f);

            if (ProductImageService.type_single.equals(pi.getType())) {//对于单张图片的处理
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);

                ImageUtil.resizeImage(f, 56, 56, f_small);
                ImageUtil.resizeImage(f, 217, 190, f_middle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?pid=" + pi.getPid();
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(int id, HttpSession session) {
        ProductImage pi = productImageService.get(id);
        //从数据库删除的同时需要从文件删除
        String fileName = pi.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;

        if (ProductImageService.type_single.equals(pi.getType())) {
            //imageFolder= session.getServletContext().getRealPath("img/productSingle");
            imageFolder = "D:\\severimage\\productSingle";
            System.out.println("---" + imageFolder + "-----");
            //imageFolder_small= session.getServletContext().getRealPath("img/productSingle_small");
            //imageFolder_middle= session.getServletContext().getRealPath("img/productSingle_middle");
            imageFolder_small = "D:\\severimage\\productSingle_small";
            imageFolder_middle = "D:\\severimage\\productSingle_middle";
            File imageFile = new File(imageFolder, fileName);
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            imageFile.delete();
            f_small.delete();
            f_middle.delete();

        } else {
            //imageFolder= session.getServletContext().getRealPath("img/productDetail");
            imageFolder = "D:\\severimage\\productDetail";
            File imageFile = new File(imageFolder, fileName);
            imageFile.delete();
        }

        productImageService.delete(id);

        return "redirect:admin_productImage_list?pid=" + pi.getPid();
    }

    @RequestMapping("admin_productImage_list")
    public String list(int pid, Model model) {
        Product p = productService.get(pid);
        List<ProductImage> pisSingle = productImageService.list(pid, ProductImageService.type_single);
        List<ProductImage> pisDetail = productImageService.list(pid, ProductImageService.type_detail);
        Category c = categoryService.get(p.getCid());
        model.addAttribute("p", p);
        model.addAttribute("c", c);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);

        return "admin/listProductImage";
    }

    @RequestMapping("forecreateOrder")
    public String createOrder(Model model, Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUid(user.getId());
        order.setStatus(OrderService.waitPay);
        List<OrderItem> ois= (List<OrderItem>)  session.getAttribute("ois");

        float total =orderService.add(order,ois);
        return "redirect:forealipay?oid="+order.getId() +"&total="+total;
    }
}
