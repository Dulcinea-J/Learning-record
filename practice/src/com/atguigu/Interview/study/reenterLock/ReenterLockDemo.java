package com.atguigu.Interview.study.reenterLock;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/2 22:14
 * @Version: 1.0
 */

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@ClassName ReenterLockDemo
 *@Description TODO
 *@Author ReenterLockDemo
 *@Date 2019/7/2 22:14
 *@Version 1.0
 **/

class Phone {
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoke sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t######invoked sendEmail");
    }
    //=================================================
}

class Phone2 implements Runnable{

    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoke sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t######invoked sendEmail");
    }

    //=====================

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoke get");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t######invoked set");
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍能获取该锁的代码
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *
 * 也即是说，线程可以进入任何一个它拥有的锁所同步着的代码块
 *
 * case one synchronized就是一个典型的可重入锁
 * t1	 invoke sendSMS
 * t1	######invoked sendEmail
 * t2	 invoke sendSMS
 * t2	######invoked sendEmail
 *
 *
 * case two
 * ReentrantLock也是一个典型的可重入锁
 *
 */

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone2 phone2 = new Phone2();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();


        try {
                    TimeUnit.SECONDS.sleep(1 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        System.out.println("===============");
        System.out.println("===============");

        Thread t3 = new Thread(phone2,"t3");
        Thread t4 = new Thread(phone2,"t4");
        t3.start();
        t4.start();


    }


}
