package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductionInfoService;
import com.bjpowernode.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 作者：梁栋~
 * 时间：2022/8/15 12:47
 * 描述：
 */
@Controller
@RequestMapping("/prod")
public class ProductionInfoAction {
    public static final int PAGE_SIZE = 5;
    String saveFileName = "";
    @Autowired
    ProductionInfoService productionInfoService;
    @RequestMapping("getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productionInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }

    //显示第一页5条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        //得到第一页的数据
        PageInfo info = productionInfoService.splitPage(1,PAGE_SIZE);
        request.setAttribute("info",info);
        return "product";
    }
    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public  void  ajaxSplit(int page, HttpSession session){
        PageInfo info = productionInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",info);
    }


    //异步ajax文件上传
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //提取生成文件名
        saveFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/image_big");
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回给客户端json对象,封装图片的路径,在页面回显
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        return object.toString();
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int num = -1 ;
        try {

            num = productionInfoService.save(info);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg","增加成功");

        }else{
            request.setAttribute("msg","增加失败");
        }
        //增加成功跳转重新访问数据库,到分页显示页面上
        //清空img
        saveFileName = null;
        return "forward:/prod/split.action";
    }

    @RequestMapping("/one")
    public String one(Integer pid, Model model){
        ProductInfo info = productionInfoService.getByID(pid);
        model.addAttribute("prod",info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        if (!saveFileName.equals("")) {
            info.setpImage(saveFileName);
        }
        int num = -1;
        try {

            num = productionInfoService.update(info);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (num>0) {
            request.setAttribute("msg","更新陈工");
        }else{
            request.setAttribute("msg","更新失败");
        }
        saveFileName = null;
        return "forward:/prod/split.action";
    }

    @RequestMapping("/deleate")
    public String delete(int pid,HttpServletRequest request){
        int num = -1;
        try {

            num = productionInfoService.delete(pid);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (num >0 ){
            request.setAttribute("msg","删除成功");
        }else{
            request.setAttribute("msg","删除失败");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }
    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=UTF-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
        PageInfo info = productionInfoService.splitPage(1,PAGE_SIZE);
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }
}
