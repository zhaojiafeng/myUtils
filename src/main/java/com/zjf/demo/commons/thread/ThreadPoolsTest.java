package com.zjf.demo.commons.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolsTest {


    public static void main(String[] args) {

//        newCachedThreadPool 创建一个可缓存线程池，应用中存在的线程数可以无限大
//        输出结果是：可以有无限大的线程数进来（线程地址不一样）
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("******************newCachedThreadPool*****************");
        for (int i = 0; i < 5; i++) {
            newCachedThreadPool.execute(new ThreadForPools(i));
        }
        newCachedThreadPool.shutdown();


//        newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//        输出结果：每次只有两个线程在处理，当第一个线程执行完毕后，新的线程进来开始处理（线程地址不一样）
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
        System.out.println("*****************newFixedThreadPool****************");
        for (int i = 0; i < 4; i++) {
            newFixedThreadPool.execute(new ThreadForPools(i));
        }
        newFixedThreadPool.shutdown();

//        newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
//        执行结果：延迟三秒之后执行，除了延迟执行之外和newFixedThreadPool基本相同，可以用来执行定时任务
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("****************************newFixedThreadPool*******************************");
        for (int i = 0; i < 4; i++) {
            //延迟三秒执行
            newScheduledThreadPool.schedule(new ThreadForPools(i), 3, TimeUnit.SECONDS);
        }
        newScheduledThreadPool.shutdown();


//        newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
//        执行结果：只存在一个线程，顺序执行
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        System.out.println("****************************newFixedThreadPool*******************************");
        for (int i = 0; i < 4; i++) {
            newSingleThreadExecutor.execute(new ThreadForPools(i));
        }
        newSingleThreadExecutor.shutdown();


//        jdk8增加了newWorkStealingPool(int parall)，增加并行处理任务的线程池，不能保证处理的顺序。  有点问题
//        ExecutorService newWorkStealingPool = Executors.newWorkStealingPool(2);
//        for (int i = 0; i < 4; i++) {
//            newWorkStealingPool.execute(new ThreadForPools(i));
//        }
//        newWorkStealingPool.shutdown();
    }
}
