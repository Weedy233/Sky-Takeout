package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sky.entity.DishFlavor;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入口味数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品 id 删除口味
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteByDishId(long dishId);

    /**
     * 根据菜品id查询对应的口味数据
     * @param id
     * @return 口味列表
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getFlavorsByDishId(Long id);
}