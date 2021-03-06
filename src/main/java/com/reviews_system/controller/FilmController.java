package com.reviews_system.controller;

import com.reviews_system.domain.*;
import com.reviews_system.service.CategoryService;
import com.reviews_system.service.CommentService;
import com.reviews_system.service.FilmService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/film")
@Controller
public class FilmController {
    public static int num=0;
    private static final String BASEDIR = "C:\\Users\\Administrator\\Desktop\\下学期\\J2EE\\githubprojects\\Reviews_system\\src\\main\\webapp\\images\\";
    @Autowired
    private FilmService filmService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;

    //    查询所有
    //    分页查询
    static int count=0;
    @RequestMapping("/list")
    public ModelAndView list(String methods){
        if(methods==null)
        {
            methods="one";
        }
        int size=5;
        int total=filmService.selectFilmCount();
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
        List<Film>filmList=filmService.listByPage(start,size);
        modelAndView.addObject("filmList",filmList);
        modelAndView.addObject("pagenum",count+1);
        modelAndView.addObject("pagetotal",page);
        modelAndView.setViewName("film-list");
        return modelAndView;
    }

//    根据电影名称进行模糊查询
    @RequestMapping("/selectByName")
    public ModelAndView selectByName(String film_name){
        System.out.println(film_name);
        List<Film>filmList=filmService.selectByName(film_name);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("filmList",filmList);
        modelAndView.setViewName("film-list");
        return modelAndView;

    }

//    根据ID进行查询
    @RequestMapping("/selectById")
    public ModelAndView selectById(int film_id){
        ModelAndView modelAndView=new ModelAndView();
        Film film=filmService.selectById(film_id);
        modelAndView.addObject("film",film);
        List<Category>categoryList=categoryService.list();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("film-edit");
        return modelAndView;
    }

//    更新
    @RequestMapping("/updateFilm")
    public String updateFilm(Film film, MultipartFile pictureFile, int [] catrgoryIds,String oldicture) throws IOException{

        String newFileName = saveFile(pictureFile);
        System.out.println(newFileName);
        if(newFileName!=null)
        {
            film.setPicture(newFileName);
        }
        else
        {
            film.setPicture(oldicture);
        }
        System.out.println(oldicture);
        int i=filmService.updateFilm(film,catrgoryIds);
        System.out.println(i);
        return "redirect:/film/list";
    }

    @RequestMapping("/delById")
    public String delById(int film_id){
        int i=filmService.deleteById(film_id);
        return "redirect:/film/list";
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        ModelAndView modelAndView=new ModelAndView();
        List<Category>categoryList=categoryService.list();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("film-add");
        return modelAndView;
    }

    @RequestMapping("save")
    public String save(Film film, MultipartFile pictureFile, int [] catrgoryIds) throws IOException{
        String newFileName = saveFile(pictureFile);
        film.setPicture(newFileName);
        filmService.save(film,catrgoryIds);
        return  "redirect:/film/list";
    }

    public String saveFile(MultipartFile pictureFile) throws IOException {
        String newFileName = null;
        if (pictureFile != null) {
            // 获取文件名称
            String originalFilename = pictureFile.getOriginalFilename();
            if (originalFilename != null && !"".contentEquals(originalFilename)) {
                // 获取扩展名
                String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
                // 重新生成一个新的文件名
                newFileName = UUID.randomUUID().toString() + extName;
                // 指定存储文件的根目录
                File dirFile = new File(BASEDIR);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                // 将上传的文件复制到新的文件（完整路径）中
                pictureFile.transferTo(new File(BASEDIR + newFileName));
            }
        }
        return newFileName;
    }
    static int film_count=0;
    @RequestMapping("/weblist")
    public ModelAndView weblist(String methods){
        if(methods==null)
        {
            methods="one";
        }
        int size=8;
        int total=filmService.selectFilmCount();
        int page=0;
        if(total%size!=0)
        {
            page=total/size;
            page++;
        }
        else
        {
            page=total/size;
        }
        if(methods.equals("next")&&film_count<page)
        {
            film_count++;
        }
        else if(methods.equals("up")&&film_count!=0)
        {
            film_count--;
        }
        else
        {
            film_count=0;
        }
        int start=size*film_count;
        ModelAndView modelAndView=new ModelAndView();
//        List<Film>filmList=filmService.findAll();
        List<Film>filmList=filmService.listByPage(start,size);
        modelAndView.addObject("filmList",filmList);
        modelAndView.addObject("pagenum",film_count+1);
        modelAndView.addObject("pagetotal",page);
        List<Category>categoryList=categoryService.list();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("home");
        return modelAndView;
    }

//    点击电影跳转详情页
    @RequestMapping("/filmDetails")
    public ModelAndView filmDetails(int film_id){
        ModelAndView modelAndView=new ModelAndView();
        Film film=filmService.selectById(film_id);
        List<Comment>comments=commentService.selectByFilmId(film_id);
        modelAndView.addObject("commentlist",comments);
        modelAndView.addObject("film",film);
        modelAndView.setViewName("film-info");
        return modelAndView;
    }

    //    刷新
    @RequestMapping("/refresh")
    public String refresh(){
        return "redirect:/film/list";
    }
}
