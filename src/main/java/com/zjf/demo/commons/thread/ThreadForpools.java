package com.zjf.demo.commons.thread;

public class ThreadForpools implements Runnable {

    private Integer index;

    ThreadForpools(Integer index) {
        this.index = index;
    }

    @Override
    public void run() {
        try {
            System.out.println("start deal with thread ！！！");
            Thread.sleep(index * 1000);
            System.out.println("this thread index：" + this.index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.identityHashCode(Thread.currentThread()));
    }
}
