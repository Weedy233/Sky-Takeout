package com.sky.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;

@Service
public interface SetmealService {

    /**
     * 新增套餐 
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 根据 id 查询套餐
     * @param id
     * @return
     */
    Setmeal getById(Long id);

    /**
     * 更新套餐
     * @param setmealDTO
     */
    void updateWithDish(SetmealDTO setmealDTO);

    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 启用禁用套餐
     * @param status
     * @param id
     */
    void enableOrDisable(Integer status, Long id);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteBatch(List<Long> ids);

}
