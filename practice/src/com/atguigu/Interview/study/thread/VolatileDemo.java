package com.atguigu.Interview.study.thread;



import sun.font.FontRunIterator;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/1 20:46
 * @Version: 1.0
*/



class MyData{

   int number = 0;

    public void addTo60(){
        this.number = 60;
    }
}

/**
 * 1.验证volatile的可见性
 * 1.1假如int number = 0;number变量之前根本没有添加volatile关键字
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t update number value:"+ myData.number);
        },"AAA").start();






        // 第二个线程就使我们的main线程
        while(myData.number == 0){
            // main线程就一直在这里等待循环，直到number的值不在等于0
        }

        System.out.println(Thread.currentThread().getName()+"\t mission is over,main get numbervalue: " + myData.number);

    }



}
