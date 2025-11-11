package com.sky.service;

import java.util.List;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

public interface ShoppingCartService {

    /**
     * 添加菜品或套餐到购物车
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查看购物车
     * @return 购物车列表
     */
    List<ShoppingCart> showShoppingCart();

    void cleanShoppingCart();

    void sub(ShoppingCartDTO shoppingCartDTO);

}
