package com.atguigu.Interview.study.futureTaskCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/4 9:05
 * @Version: 1.0
 */

class MyThread implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        System.out.println("come in callable****");
        // 暂停一会儿线程
        try {
                    TimeUnit.SECONDS.sleep( 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 两个线程，一个main主线程，一个是AAFutureTask

        // FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask,"AA").start();
//        int result02 = futureTask.get();

        // 只有一个FutureTask但是有多个线程时，futuretask的结果会被复用，mythread中的方法值执行一次
//      // 要想执行两次，就需要再new一个FutureTask对象
        new Thread(futureTask,"BB").start();

        System.out.println(Thread.currentThread().getName()+"*********");
        int result01 = 100;

        while(!futureTask.isDone()){

        }

        // 要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，值要计算完才可以取到
        int result02 = futureTask.get();
        System.out.println("*****result: "+(result01 + result02));
    }
}
