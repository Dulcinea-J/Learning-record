package com.atguigu.Interview.study.singleton;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/2 12:14
 * @Version: 1.0
 */

/**
 *@ClassName SingletonDemo
 *@Description TODO
 *@Author SingletonDemo
 *@Date 2019/7/2 12:14
 *@Version 1.0
 **/
public class SingletonDemo {

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }

}

class Singleton{
    // 1.私有化构造器，使得在类的外部不能调用此构造器
    private Singleton(){

    }
    // 2.在类的内部创建一个类的实例
    private static Singleton instance = new Singleton();
    // 3.私有化此对象，通过公共的方法来调用
    // 4.此公共的方法，只能通过类来调用，因为设置为static的，同事类的实例也要设置用static声明
    public static Singleton getInstance(){
        return instance;
    }
}
