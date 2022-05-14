package com.reviews_system.controller;

import com.reviews_system.domain.Film;
import com.reviews_system.domain.User;
import com.reviews_system.service.FilmService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/film")
@Controller
public class FilmController {
    @Autowired
    private FilmService filmService;
    //    查询所有
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView=new ModelAndView();
        List<Film>filmList=filmService.findAll();
        modelAndView.addObject("filmList",filmList);
        modelAndView.setViewName("film-list");
        for (Film f:filmList) {
            f.getCategories().toString();
            f.toString();
        }
        return modelAndView;
    }

    //    根据id查询
    @ResponseBody
    @RequestMapping("/selectById")
    public Result<Film> selectById(int film_id){
        try{
            Film film=filmService.selectById(film_id);
            System.out.println(film);
            if(film==null){
                return new Result(false,"查询失败！");
            }
            return new Result(true,"查询成功",film);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"查询失败！");
        }
    }
}
