package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

public interface ShoppingCartService {

    /**
     * 添加菜品或套餐到购物车
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    ShoppingCart list();

    void clean();

    void sub(ShoppingCartDTO shoppingCartDTO);

}
