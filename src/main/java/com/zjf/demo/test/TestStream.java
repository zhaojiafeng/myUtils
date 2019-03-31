package com.zjf.demo.test;

import com.zjf.demo.admin.Admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStream {

    private static List<Admin> admins = Arrays.asList(
            new Admin(1,"azj", 25),
            new Admin(2,"hangsan", 26),
            new Admin(3,"zhaowu", 29)
    );

    public static void main(String[] args) {
        String str = "旅游#跑步#游泳#打乒乓#";
        List list = new ArrayList();
        Stream.of(str.split("#"))
                .peek(System.out::println)
                .forEach(list::add);
        System.out.println(list);
    }
}
