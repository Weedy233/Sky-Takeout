package com.sky.service;

import java.util.List;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;

public interface DishService {
    
    /**
     * 新增菜品和对应的口味
     */
    Dish saveWithFlavor(DishDTO dishDTO);

    /**
     * 新增菜品和对应的口味
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     */
    void deleteBatch(List<Long> ids);
    
}
