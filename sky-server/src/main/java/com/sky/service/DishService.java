package com.sky.service;

import java.util.List;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

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

    /**
     * 根据 ID 查询菜品
     */
    DishVO getById(Long id);

    /**
     * 修改菜品基本信息和对应口味信息
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 菜品起售停售
     */
    void enableOrDisable(Integer status, Long id);
    
}
