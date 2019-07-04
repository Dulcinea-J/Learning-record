package com.atguigu.Interview.study.aba;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/2 16:14
 * @Version: 1.0
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *@ClassName ABADemo
 *@Description TODO
 *@Author ABADemo
 *@Date 2019/7/2 16:14
 *@Version 1.0
 **/
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,1);

    public static void main(String[] args) {

        System.out.println("====以下是ABA问题的产生=====");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);

        },"t1").start();

        new Thread(() -> {
            try {
                        TimeUnit.SECONDS.sleep(1 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            System.out.println(atomicReference.compareAndSet(100,2019) + "\t"+atomicReference.get());

        }, "t2").start();


        // 暂停一会儿线程
        try {
                    TimeUnit.SECONDS.sleep(1 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        System.out.println("=====以下是ABA问题的解决=====");

        new Thread(() -> {

            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号: " + stamp);
            // 暂停1秒钟t3线程
            try {
                        TimeUnit.SECONDS.sleep( 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t第2次版本号: " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t第3次版本号: " + atomicStampedReference.getStamp());

        }, "t3").start();


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号: " + stamp);
            // 暂停3秒钟t4线程，保证上面的t3线程完成率一次ABA操作
            try {
                        TimeUnit.SECONDS.sleep(3 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            boolean result = atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName() + "\t修改成功否: " + result + "\t当前实际最新版本号为："+atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName()+"\t当前实际最新值：" + atomicStampedReference.getReference());
        }, "t4").start();


    }
}
