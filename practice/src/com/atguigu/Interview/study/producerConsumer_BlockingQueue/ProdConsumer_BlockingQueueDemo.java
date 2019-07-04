package com.atguigu.Interview.study.producerConsumer_BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName ProdConsumer_BlockingQueueDemo
 *@Description TODO
 *@Author ProdConsumer_BlockingQueueDemo
 *@Date 2019/7/3 21:31
 *@Version 1.0
 **/
class MyResource{
    // 默认开启，进行生产消费
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue blockingQueue = null;

    public MyResource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L,TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列" +data +"成功");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列" +data +"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停了，表示FLAG=false,生产结束");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while(FLAG){
            result = (String) blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟，未取到，消费退出");
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列" + result +"成功");

        }
    }
    public void stop() throws Exception{
        this.FLAG = false;
    }
}
public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        // 暂停一会儿线程
        try {
                    TimeUnit.SECONDS.sleep(5 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("5秒时间到，大老板main线程叫停，活动结束");

        myResource.stop();

    }
}
