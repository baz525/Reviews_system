package com.reviews_system.dao.impl;

import com.github.pagehelper.Page;
import com.reviews_system.dao.CategoryDao;
import com.reviews_system.domain.Category;
import com.reviews_system.domain.Film;
import com.reviews_system.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Category> findAll() {
        List<Category>categoryList=jdbcTemplate.query("select * from category",new BeanPropertyRowMapper<Category>(Category.class));
        return categoryList;
    }
    @Override
    public List<Film> findFilm(int category_id) {
        List<Film>FilmList=jdbcTemplate.query("SELECT film.film_id,film.film_name,film.picture,film.price,film.score FROM film_category,film,category WHERE film.film_id=film_category.film_id and category.category_id=film_category.category_id and film_category.category_id=?",new BeanPropertyRowMapper<Film>(Film.class),category_id);
        return FilmList;
    }

    @Override
    public Integer save(Category category) {
        Integer i = jdbcTemplate.update("insert into category values (?,?)",null,category.getCategory_name());
        return i;
    }

    @Override
    public void delById(int category_id) {
        int i=jdbcTemplate.update("delete from category where category_id=?",category_id);
        System.out.println(i);
    }

    @Override
    public Category selectById(int category_id) {
        Category category=jdbcTemplate.queryForObject("select * from category where category_id=?",new BeanPropertyRowMapper<Category>(Category.class),category_id);
        category.toString();
        return category;
    }

    @Override
    public int updateCategory(Category category) {
        int i=jdbcTemplate.update("update category set category_name=?where category_id=?",category.getCategory_name(),category.getCategory_id());
        return i;
    }

    @Override
    public Page<Category> search(Category category) {
        Page<Category>page= (Page<Category>) jdbcTemplate.query("select * from category",new BeanPropertyRowMapper<Category>(Category.class));
        return page;
    }

    @Override
    public List<Category> selectByName(String category_name) {
        String sql="select * from category where category_name like '%"+category_name+"%'";
        List<Category>categoryList=jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
        return categoryList;
    }

    @Override
    public int delByIds(int[] ids) {
        int i=0;
        for (int id:ids) {
            int j=jdbcTemplate.update("delete from category where category_id=?",id);
            i+=j;
        }
        return i;
    }

    @Override
    public List<Category> findRoleById(int id) {
        String sql="SELECT * FROM category ca,film_category fc WHERE ca.category_id=fc.category_id AND fc.film_id=?";
        List<Category>categoryList=jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class),id);
        return categoryList;
    }
    @Override
    public int selectFilmCount(int category_id) {
        int i= jdbcTemplate.queryForObject("select count(*) from film,film_category where film.film_id=film_category.film_id and film_category.category_id=?",(Integer.class),category_id);
        return i;
    }
    @Override
    public List<Film> listByPage(Integer start, Integer end,int category_id) {
        String sql="select * from film,film_category where film.film_id=film_category.film_id and film_category.category_id="+category_id+" limit "+start+","+end;
        List<Film> filmList =jdbcTemplate.query(sql,new BeanPropertyRowMapper<Film>(Film.class));
        return filmList;
    }

    @Override
    public Integer selectUserCount() {
        String sql="select count(*) from category";
        int i= jdbcTemplate.queryForObject(sql,Integer.class);
        return i;
    }

    @Override
    public List<Category> listByPage(Integer start, Integer end) {
        String sql = "select * from category limit "+start+","+end;
        List<Category> categoryList =jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
        return categoryList;
    }
}
