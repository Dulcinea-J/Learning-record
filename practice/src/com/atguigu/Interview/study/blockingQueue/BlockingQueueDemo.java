package com.atguigu.Interview.study.blockingQueue;

/**
 *@ClassName BlockingQueueDemo
 *@Description TODO
 *@Author BlockingQueueDemo
 *@Date 2019/7/3 15:04
 *@Version 1.0
 **/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列，此队列按FIFO(先进先出)原则读元素排序
 * LinkedBlockingQueue: 一个基于链表结构的阻塞队列，此队列按FIFO(先进先出)元素排序，吞吐量要高于ArrayBlockingQueue
 * synchronousQueue: 一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态
 *
 *
 * 1 队列
 *
 * 2.阻塞队列
 *   2.1 阻塞队列有没有好的一面
 *   2.2 不得不阻塞，你如何管理
 *
 * 方法类型
 * 抛出异常：当阻塞队列满时，再往队列里add元素会抛出IllegalStateException:Queue full
 *          当阻塞队列空时，再往队列里remove元素会抛出NoSuchElementException
 *          插入->add(e)
 *          移除->remove(),remove(object)为定点清除
 *          检查->element()
 * 特殊值：插入方法，成功true失败false
 *        移除方法，成功返回出队列的元素，队列里面没有就返回null
 *        插入->offer(e)
 *        移除->poll()
 *        检查->peek()
 * 一直阻塞：当阻塞队列满时，生产者线程继续往队列里面put元素，队列会一直阻塞生产者线程直到put数据or响应中断退出
 *          当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用
 *         插入->put(e)
 *         移除->take()
 *         检查->不可用
 * 超时：当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出
 *          插入->offer(e,time,unit)
 *          移除->poll(time,unit)
 *          检查->不可用
 *
 *
 */

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));;
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));;
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));;
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));;
    }
}
