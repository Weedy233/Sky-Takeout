package com.sky.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;

@Service
@Transactional
public class SetmealServiceImpl implements SetmealService{

    @Autowired
    SetmealMapper setmealMapper;

    @Autowired
    SetmealDishMapper setmealDishMapper;

    /**
     * 新增套餐 
     * @param setmealDTO
     */
    @Override
    public void saveWithDish(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.insert(setmeal);

        Long setmealId = setmeal.getId();

        List<SetmealDish> dishes = setmealDTO.getSetmealDishes();
        if (dishes != null && dishes.size() > 0) {
            dishes.forEach(dish -> {
                dish.setSetmealId(setmealId);
            });
            setmealDishMapper.insertBatch(dishes);
        }
    }

    /**
     * 根据 id 查询套餐
     * @param id
     * @return
     */
    @Override
    public Setmeal getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    /**
     * 更新套餐
     * @param setmealDTO
     */
    @Override
    public void updateWithDish(SetmealDTO setmealDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pageQuery'");
    }

    /**
     * 启用禁用套餐
     * @param status
     * @param id
     */
    @Override
    public void enableOrDisable(Integer status, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enableOrDisable'");
    }
    
    /**
     * 批量删除套餐
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBatch'");
    }

}
