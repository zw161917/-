package com.imooc.aop.demo6;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void find() {
        System.out.println("学生查找。。。");
    }

    @Override
    public void save() {
        System.out.println("学生保存。。。");
    }

    @Override
    public void update() {
        System.out.println("学生查找。。。");
    }

    @Override
    public void delete() {
        System.out.println("学生删除。。。");
    }
}
