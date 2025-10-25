package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 校验员工登录信息，并返回合法的员工实体。
     *
     * @param employeeLoginDTO 登录所需的用户名、密码
     * @return 通过认证的员工实体
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增后台员工并为其设置默认状态与初始密码。
     *
     * @param employeeDTO 待保存的员工资料
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 根据查询条件分页检索员工。
     *
     * @param employeePageQueryDTO 查询条件与分页参数
     * @return 含总数与当前页数据的分页结果
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 切换指定员工的启用/禁用状态。
     *
     * @param status 目标状态，取 {@link com.sky.constant.StatusConstant}
     * @param id     员工主键
     */
    void enableOrDisable(Integer status, Long id);

    /**
     * 按主键查询员工详情。
     *
     * @param id 员工主键
     * @return 员工实体，不会包含真实密码
     */
    Employee getById(Long id);

    /**
     * 更新员工的基本信息（不含密码）。
     *
     * @param employeeDTO 最新的员工资料
     */
    void update(EmployeeDTO employeeDTO);

}
