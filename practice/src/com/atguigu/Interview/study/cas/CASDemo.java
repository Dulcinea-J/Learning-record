package com.atguigu.Interview.study.cas;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/2 14:55
 * @Version: 1.0
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName CASDemo
 *@Description TODO
 *@Author CASDemo
 *@Date 2019/7/2 14:55
 *@Version 1.0
 **/

/**
 * 1 CAS是什么？ ===> compareAndSet
 * 比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        //
        System.out.println(atomicInteger.compareAndSet(5,2019) + "\t currentdata: "+ atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5,2019) + "\t currentdata: "+ atomicInteger.get());
    }
}
