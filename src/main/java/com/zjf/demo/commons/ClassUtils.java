package com.zjf.demo.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaojiafeng
 * 反射
 */
public class ClassUtils<T> {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 根据属性名获取属性值
     *
     * @param attribute 属性名
     * @param object    属性对应值
     * @return 属性值
     */
    public Object getValue(String attribute, Object object) {
        try {
            String getter = "get" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
            Method method = object.getClass().getMethod(getter, new Class[]{});
            return method.invoke(object, new Object[]{});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error(String.valueOf(e));
            return null;
        }
    }


    /**
     * 获取对象的所有属性值
     *
     * @param attributes 属性名的list集合
     * @param t          对象
     * @return 属性值的list集合
     */
    public List getAllValues(List<String> attributes, T t) {
        List values = new ArrayList();
        for (String attr : attributes) {
            values.add(getValue(attr, t));
        }
        return values;
    }


    /**
     * 获取对象集合中的所有属性值
     *
     * @param attributes 属性名的list集合
     * @param ts         对象的list集合
     * @return 对象的属性值的list集合
     */
    public List getAllValues(List<String> attributes, List<T> ts) {
        List<List> values = new ArrayList<>();
        for (T t : ts) {
            values.add(getAllValues(attributes, t));
        }
        return values;
    }

    /**
     * 获取所有属性名
     *
     * @param t 泛型
     * @return 属性名的list集合
     */
    public List<String> getAttributes(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        List<String> attributes = new ArrayList<>();

        for (Field field : fields) {
            attributes.add(field.getName());
        }
        return attributes;
    }


    /**
     * 获取属性名和类型
     *
     * @param t 泛型
     * @return 属性名key和类型value的list集合
     */
    public List<Map<String, Object>> getAttributesAndType(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        List<Map<String, Object>> attributes = new ArrayList<>();
        for (Field field : fields) {
            Map<String, Object> map = new HashMap<>();
            map.put(field.getName(), field.getType());
            attributes.add(map);
        }
        return attributes;
    }

}
