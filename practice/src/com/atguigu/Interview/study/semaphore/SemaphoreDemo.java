package com.atguigu.Interview.study.semaphore;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/3 14:37
 * @Version: 1.0
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName SemaPhoreDemo
 *@Description TODO
 *@Author SemaPhoreDemo
 *@Date 2019/7/3 14:37
 *@Version 1.0
 **/

/**
 * Semaphore（信号量）主要用于两个目的，一个是用于多个共享资源的互斥使用，
 * 另一个用于并发线程数的控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 模拟3个停车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    try {
                                TimeUnit.SECONDS.sleep(3 );
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    System.out.println(Thread.currentThread().getName()+"\t 停车三秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
