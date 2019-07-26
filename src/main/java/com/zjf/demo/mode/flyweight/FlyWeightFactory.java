package com.zjf.demo.mode.flyweight;import java.util.HashMap;/** * @author zhaojiafeng */class FlyWeightFactory {    /**     * 定义一个池容器     */    private static HashMap<String, AbstractFlyWeight> pool = new HashMap<>();    /**     * 享元工厂     *     * @param extrinsic  外部状态     * @return AbstractFlyWeight     */    static AbstractFlyWeight getFlyweight(String extrinsic) {        AbstractFlyWeight flyweight;        if (pool.containsKey(extrinsic)) {            flyweight = pool.get(extrinsic);            System.out.print("已有 " + extrinsic + " 直接从池中取---->");        } else {            //根据外部状态创建享元对象            flyweight = new ConcreteFlyweight(extrinsic);            //放入池中            pool.put(extrinsic, flyweight);            System.out.print("创建 " + extrinsic + " 并从池中取出---->");        }        return flyweight;    }}