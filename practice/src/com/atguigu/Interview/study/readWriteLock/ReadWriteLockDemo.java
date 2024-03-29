package com.atguigu.Interview.study.readWriteLock;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/3 10:15
 * @Version: 1.0
 */

/**
 *@ClassName ReadWriteLockDemo
 *@Description TODO
 *@Author ReadWriteLockDemo
 *@Date 2019/7/3 10:15
 *@Version 1.0
 **/

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源，就不应该再有其他线程可以对改资源进行读或写
 * 小总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 */
class MyCache //资源类
{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    public void put(String key,Object value){
        rwLock.writeLock().lock();
        try{

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

        System.out.println(Thread.currentThread().getName()+"\t 正在写入：" +key);
        // 暂停一会儿线程
        try {
                    TimeUnit.MILLISECONDS.sleep(300 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"\t 写入完成：");
    }


    public void get(String key){
        rwLock.readLock().lock();
        try{

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

        System.out.println(Thread.currentThread().getName()+"\t 正在读取：" +key);
        // 暂停一会儿线程
        try {
            TimeUnit.MILLISECONDS.sleep(300 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object result =map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t 读取完成："+result);
    }


}
public class ReadWriteLockDemo {
    public static void main(String[] args) {

    }
}
