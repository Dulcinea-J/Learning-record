package com.atguigu.Interview.study.notsafe;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/2 20:18
 * @Version: 1.0
 */

/**
 *@ClassName ContainerNotSafeDemo
 *@Description TODO
 *@Author ContainerNotSafeDemo
 *@Date 2019/7/2 20:18
 *@Version 1.0
 **/

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 * ArrayList
 */
// java.util.ConcurrentModificationException
/**
 *  1.故障现象
 *        java.util.ConcurrentModificationException
 *
 *  2.导致原因
 *      并发修改异常
 *
 *  3.解决方案
 *   3.1 new Vector<>();
 *   3.2 Collections.synchronizedList(new ArrayList<>());
 *   3.3 new CopyOnWriteArrayList<>();
 *
 *  4.优化建议
 *
 *
 *  new HashSet<>().add("a");
 *  HashSet的底层实现是什么？如果是HashMap,为什么HashSet的add方法只有一个参数
 *  HashSet的底层实现是HashMap,HashSet的add方法调用了hashMap的put方法，添加的是一个key,值为PRESENT的Object类型的常量
 *  return map.put(e, PRESENT)==null;
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i <= 30 ; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }

    public static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i <= 30 ; i++) {
             new Thread(() -> {
             set.add(UUID.randomUUID().toString().substring(0,8));
                 System.out.println(set);
                         }, String.valueOf(i)).start();
        }
        new HashSet<>().add("a");
    }

    public static void listNotSafe() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i <= 30 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
         }
    }
}
