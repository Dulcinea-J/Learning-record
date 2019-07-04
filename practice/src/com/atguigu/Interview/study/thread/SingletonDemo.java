package com.atguigu.Interview.study.thread;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/1 22:33
 * @Version: 1.0
 */

/**
 *@ClassName SingletonDemo
 *@Description TODO
 *@Author SingletonDemo
 *@Date 2019/7/1 22:33
 *@Version 1.0
 **/
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法singletonDemo()");
    }

    // DCL(double check lock双端检锁机制)
    public static SingletonDemo getInstance(){
        if(instance == null){
//            synchronized (SingletonDemo.class){
//                if (instance == null){
                    instance = new SingletonDemo();
//                }
            }
//        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
