package com.zjf.demo.test;

import org.apache.ibatis.transaction.Transaction;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestStream {

    @Test
    public void test1() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream
                .flatMap((childList) -> childList.stream());

        List<Integer> list = outputStream.collect(toList());
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));
        System.out.println(list.get(5));
    }


    @Test
    public void test2() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(toList());
    }

    @Test
    public void test3() {
//        List<Person> persons = new ArrayList();
//        for (int i = 1; i <= 5; i++) {
////            Person person = new Person(i, "name" + i);
//            persons.add(person);
//        }
//        List<Person> personList2 = persons.stream()
//                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
//                .limit(2)
//                .collect(Collectors.toList());
//        System.out.println(personList2.get(0).getName());
//        System.out.println(personList2.get(1).getName());
    }

    @Test
    public void test4(){
        List<Person> javaProgrammers = new ArrayList<Person>() {{
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));  }};
        List<Person> sortedJavaProgrammers = javaProgrammers.stream()
                .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
                .limit(5)
                .collect(toList());
        sortedJavaProgrammers.forEach((p) -> System.out.printf(p.getFirstName()+"-"+ p.getLastName()+"--"));
    }

    public class Person {
        private String firstName, lastName, job, gender;
        private int salary, age;

        public Person(String firstName, String lastName, String job,
                      String gender, int age, int salary)       {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.job = job;
            this.salary = salary;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}
