package com.reviews_system.service;


import com.reviews_system.domain.Orders;

import java.util.List;

public interface OrderService {
    Integer save(Orders orders,int[]site_ids);
    List<Orders> selectOrder();

    List<Orders> findAll();

    void delById(int orders_id);

    int delByIds(int[]ids);

    List<Orders> findByFilmId(int film_id);

    String findUserNameById(int user_id);


    List<Orders> findOrderByName(String film_name);
}
