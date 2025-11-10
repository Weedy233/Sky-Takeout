package com.sky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    ShoppingCartMapper shoppiCartMapper;

    /**
     * 添加菜品或套餐到购物车
     * @param shoppingCartDTO
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        // 判断当前加入的商品再购物车中是否已存在

        // 如果已存在，只更新对应商品的数量

        // 如果不存在，插入新的商品数据
    }
}
