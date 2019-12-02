package com.imooc.sm.dao;

import com.imooc.sm.entity.Department;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("departmentDao")
public interface DepartmentDao {

    void insert(Department department);//增
    void delete(Integer id);//删
    void update(Department department);//改
    Department selectById(Integer id);//查
    List<Department> selectAll();//查询所有
}
