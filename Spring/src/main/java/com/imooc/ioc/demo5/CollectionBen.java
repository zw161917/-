package com.imooc.ioc.demo5;

import java.util.*;

/**
 * Spring的属性注入--复杂类型的属性注入
 * 1.数组类型的属性注入
 * 2.List集合类型的属性注入
 * 3.Set集合类型的属性注入
 * 4.Map集合类型的属性注入
 * 5.Properties类型的属性注入
 */

public class CollectionBen {
    private  String[] arrs; //数组类型

    private List<String> list; //list集合类型

    private Set<String> set; //Set集合类型

    private Map<String,Integer> map; //Map集合类型

    private Properties properties; //属性类型

    public String[] getArrs() {
        return arrs;
    }

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "CollectionBen{" +
                "arrs=" + Arrays.toString(arrs) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }
}
