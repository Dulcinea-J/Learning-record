package com.atguigu.Interview.study.synchronousQueue;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/3 16:42
 * @Version: 1.0
 */

/**
 *@ClassName SynchronousQueueDemo
 *@Description TODO
 *@Author SynchronousQueueDemo
 *@Date 2019/7/3 16:42
 *@Version 1.0
 **/

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列SynchronousQueue演示
 * SynchronousQueue不存储元素，生产一个消费一个
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();


        new Thread(() -> {

            try {

                TimeUnit.SECONDS.sleep(2 );
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

                TimeUnit.SECONDS.sleep(2 );
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

                TimeUnit.SECONDS.sleep(2 );
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();



    }
}
