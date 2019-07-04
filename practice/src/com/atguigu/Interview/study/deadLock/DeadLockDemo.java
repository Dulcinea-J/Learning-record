package com.atguigu.Interview.study.deadLock;

import java.util.concurrent.TimeUnit;

/**
 *@ClassName DeadLockDemo
 *@Description TODO
 *@Author DeadLockDemo
 *@Date 2019/7/4 17:39
 *@Version 1.0
 **/
class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockA + " 尝试获取" + lockB);
            try {TimeUnit.SECONDS.sleep( 2); } catch (InterruptedException e) {e.printStackTrace(); }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockA + " 尝试获取" + lockB);
            }
        }

    }
}

/**
 * 死锁是指两个或者两个以上的进程在执行过程中，
 * 因争夺资源而造成的一中互相等待的现象
 * 若无外力干涉它们都无法推进下去
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"AAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"BBB").start();
    }

    /**
     * LINUX   ps -ef|grep xxxx      ls -l
     *
     * Windows下的java运行程序 也有类似的查看进程的命令，但是目前我们需要查看的只是java
     *
     *         jps  =  java ps        jps -l ->jps命令定位进程号
     *                                jstack +进程号 ->jstack找到死锁查看
     *
     * Java stack information for the threads listed above:
     * ===================================================
     * "Thread-1":
     *         at com.atguigu.Interview.study.deadLock.HoldLockThrea
     * d.run(DeadLockDemo.java:28)
     *         - waiting to lock <0x04dacc18> (a java.lang.String)
     *         - locked <0x04dacc40> (a java.lang.String)
     *         at java.lang.Thread.run(Thread.java:748)
     * "Thread-0":
     *         at com.atguigu.Interview.study.deadLock.HoldLockThrea
     * d.run(DeadLockDemo.java:28)
     *         - waiting to lock <0x04dacc40> (a java.lang.String)
     *         - locked <0x04dacc18> (a java.lang.String)
     *         at java.lang.Thread.run(Thread.java:748)
     *
     * Found 1 deadlock.
     *
     */
}
