package com.atguigu.Interview.study.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *@ClassName CyclicBarrierDemo
 *@Description TODO
 *@Author CyclicBarrierDemo
 *@Date 2019/7/3 14:08
 *@Version 1.0
 **/

/**
 * CyclicBarrier的字面意思是可循环（Cyclic）使用的屏障（Barrier）.
 * 他要做的事情就是让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时才会开门，所有被屏障拦截的线程才会继续干活，
 * 线程进入屏障通过CyclicBarrier的await（）方法
 *
 *
 * 与CountDownLatch的不同点是：
 * countDownLatch是线程做减法，
 * cyclicBarrier是线程做加法
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七颗龙珠，召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            final int temptInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 收集到第：" + temptInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, String.valueOf(i)).start();

        }
    }
}
