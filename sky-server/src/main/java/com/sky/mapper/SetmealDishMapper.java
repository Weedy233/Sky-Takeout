package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.SetmealDish;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品 id 查询对应套餐 id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     * 批量插入菜品数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
