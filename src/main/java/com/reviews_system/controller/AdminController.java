package com.reviews_system.controller;

import com.reviews_system.domain.Admin;
import com.reviews_system.domain.User;
import com.reviews_system.service.AdminService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public String login(String admin_name, String admin_password, HttpSession session){
        Admin admin=adminService.login(admin_name,admin_password);
        if(admin!=null){
            session.setAttribute("admin",admin);
            return "redirect:/pages/main.jsp";
        }
        return "redirect:/login.jsp";
    }
    static int count=0;
    @RequestMapping("/list")
    public ModelAndView list(String methods){
        if(methods==null)
        {
            methods="one";
        }
        int size=5;
        int total=adminService.selectAdminCount();
        int page=0;
        if(total%size!=0)
        {
            page=total/size+1;
        }
        else
        {
            page=total/size;
        }
        if(methods.equals("next")&&count<page-1)
        {
            count++;
        }else if(methods.equals("next")&&count==page-1){
            count=page-1;
        }
        else if(methods.equals("up")&&count!=0)
        {
            count--;
        }
        else
        {
            count=0;
        }
        int start=size*count;
        ModelAndView modelAndView=new ModelAndView();
        List<Admin>adminList=adminService.listByPage(start,size);
        modelAndView.addObject("adminList",adminList);
        modelAndView.addObject("pagenum",count+1);
        modelAndView.addObject("pagetotal",page);
        modelAndView.setViewName("admin-list");
        return modelAndView;
    }
    //    ??????name??????
    @RequestMapping("/selectByName")
    public ModelAndView selectByName(String admin_name){
        System.out.println(admin_name);
        List<Admin>adminList=adminService.selectByName(admin_name);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("adminList",adminList);
        modelAndView.setViewName("admin-list");
        return modelAndView;
    }
    //    ??????
    @RequestMapping("/save")
    public String save(Admin admin){
        int i=adminService.save(admin);
        return "redirect:/admin/list";
    }
    //    ??????
    @RequestMapping("/refresh")
    public String refresh(){
        return "redirect:/admin/list";
    }
    //  ??????id??????
    @RequestMapping("/delById/{id}")
    public String delById(@PathVariable("id") int id){
        adminService.delById(id);
        return "redirect:/admin/list";
    }
    //    ??????id??????
    @ResponseBody
    @RequestMapping("/selectById")
    public Result<Admin> selectById(int id){
        try{
            Admin admin=adminService.selectById(id);
            System.out.println(admin);
            if(admin==null){
                return new Result(false,"???????????????");
            }
            return new Result(true,"????????????",admin);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"???????????????");
        }
    }
    //    ??????id??????
    @RequestMapping("/updateAdmin")
    public String updateAdmin(Admin admin){
        int i=adminService.updateAdmin(admin);
        return "redirect:/admin/list";
    }
    //    ????????????
    @RequestMapping("/delByIds/{admin_ids}")
    public String delByIds(@PathVariable("admin_ids")int[]ids){
        for (int i:ids) {
            System.out.println(i);
        }
        int i=adminService.delByIds(ids);
        if(i==ids.length)
        {
            return "redirect:/admin/list";
        }
        else
            return null;
    }
}
