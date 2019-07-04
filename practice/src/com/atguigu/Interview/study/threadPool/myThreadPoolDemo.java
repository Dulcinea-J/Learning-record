package com.atguigu.Interview.study.threadPool;

/**
 *@ClassName myThreadPoolDemo
 *@Description TODO
 *@Author myThreadPoolDemo
 *@Date 2019/7/4 13:48
 *@Version 1.0
 **/

import java.util.concurrent.*;

/**
 *
 * 第4种获得/使用java多线程的方式，线程池
 *
 * 以下重要：*线程池底层工作原理*
 *
 * 1.在创建了线程池后，等待提交过来的任务请求
 * 2.当调用excute()方法添加一个请求任务时，线程池会做如下判断：
 *  2.1 如果正在运行的线程数量小于corePoolSize,那么马上创建线程运行这个任务
 *  2.2 如果正在运行的线程数量大于或等于corePoolSize,那么将这个任务放入队列
 *  2.3 如果这时队列满了且正在运行的线程数量还小于maximunPoolSize,那么还是要创建非核心线程理科运行这个任务
 *  2.4 如果队列满了且正在运行的线程数量大于或等于maximumPoolSize,那么线程池会启动饱和拒绝策略来执行
 * 3.当一个线程完成任务时，他会从队列中取下一个任务来执行
 * 4.当一个线程无事可做超过一定时间（keepAliveTime）时，线程会判断
 *      如果当前运行的线程数大于corePoolSize,那么这个线程就会被停掉
 *      所以线程池的所有任务完成后它最终会收缩到corePoolSize的大小
 *
 *  四种拒绝策略：
 *          1.AbortPolicy（默认）：直接抛出RejectedExecutionException异常阻止系统正常运行
 *
 *          D:\develop\Java\jdk1.8.0_131\bin\java.exe -Dvisualvm.id=247116125974600 "-javaagent:E:\IntelliJ IDEA 2018.3.6\lib\idea_rt.jar=52080:E:\IntelliJ IDEA 2018.3.6\bin" -Dfile.encoding=UTF-8 -classpath D:\develop\Java\jdk1.8.0_131\jre\lib\charsets.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\deploy.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-32.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\javaws.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jce.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfr.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jsse.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\management-agent.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\plugin.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\resources.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\rt.jar;E:\IdeaWorkSpace\practice\out\production\practice com.atguigu.Interview.study.threadPool.myThreadPoolDemo
 *              pool-1-thread-1	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-3	 办理业务
 *              pool-1-thread-4	 办理业务
 *              java.util.concurrent.RejectedExecutionException: Task com.atguigu.Interview.study.threadPool.myThreadPoolDemo$$Lambda$1/4952965@19a45b3 rejected from java.util.concurrent.ThreadPoolExecutor@99a589[Running, pool size = 5, active threads = 4, queued tasks = 0, completed tasks = 4]
 *              	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2047)
 *              	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:823)
 *              	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1369)
 *              	at com.atguigu.Interview.study.threadPool.myThreadPoolDemo.main(myThreadPoolDemo.java:64)
 *              pool-1-thread-5	 办理业务
 *
 *          2.CallerRunsPolicy :  "调用者运行"一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退给调用者
 *
 *          //D:\develop\Java\jdk1.8.0_131\bin\java.exe -Dvisualvm.id=247004985321500 "-javaagent:E:\IntelliJ IDEA 2018.3.6\lib\idea_rt.jar=52067:E:\IntelliJ IDEA 2018.3.6\bin" -Dfile.encoding=UTF-8 -classpath D:\develop\Java\jdk1.8.0_131\jre\lib\charsets.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\deploy.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-32.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\javaws.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jce.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfr.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jsse.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\management-agent.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\plugin.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\resources.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\rt.jar;E:\IdeaWorkSpace\practice\out\production\practice com.atguigu.Interview.study.threadPool.myThreadPoolDemo
 *              pool-1-thread-1	 办理业务
 *              main	 办理业务
 *              pool-1-thread-1	 办理业务
 *              pool-1-thread-1	 办理业务
 *              pool-1-thread-1	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-4	 办理业务
 *              pool-1-thread-3	 办理业务
 *              pool-1-thread-5	 办理业务
 *
 *
 *           3.DiscardOldestPolicy: 抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
 *
 *           D:\develop\Java\jdk1.8.0_131\bin\java.exe -Dvisualvm.id=247531194301700 "-javaagent:E:\IntelliJ IDEA 2018.3.6\lib\idea_rt.jar=52205:E:\IntelliJ IDEA 2018.3.6\bin" -Dfile.encoding=UTF-8 -classpath D:\develop\Java\jdk1.8.0_131\jre\lib\charsets.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\deploy.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-32.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\javaws.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jce.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfr.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jsse.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\management-agent.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\plugin.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\resources.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\rt.jar;E:\IdeaWorkSpace\practice\out\production\practice com.atguigu.Interview.study.threadPool.myThreadPoolDemo
 *              pool-1-thread-1	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-1	 办理业务
 *              pool-1-thread-3	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-5	 办理业务
 *              pool-1-thread-4	 办理业务
 *
 *              Process finished with exit code 0
 *
 *             4. DiscardPolicy: 直接丢弃任务，不予任何处理也不抛出异常。如果允许任务丢失，这是一种最好的方案
 *
 *              D:\develop\Java\jdk1.8.0_131\bin\java.exe -Dvisualvm.id=247733190169800 "-javaagent:E:\IntelliJ IDEA 2018.3.6\lib\idea_rt.jar=52229:E:\IntelliJ IDEA 2018.3.6\bin" -Dfile.encoding=UTF-8 -classpath D:\develop\Java\jdk1.8.0_131\jre\lib\charsets.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\deploy.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-32.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\javaws.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jce.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfr.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\jsse.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\management-agent.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\plugin.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\resources.jar;D:\develop\Java\jdk1.8.0_131\jre\lib\rt.jar;E:\IdeaWorkSpace\practice\out\production\practice com.atguigu.Interview.study.threadPool.myThreadPoolDemo
 *              pool-1-thread-1	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-2	 办理业务
 *              pool-1-thread-3	 办理业务
 *              pool-1-thread-4	 办理业务
 *              pool-1-thread-5	 办理业务
 *
 *              Process finished with exit code 0
 *
 *   线程池配置合理线程数：
 *   CPU密集型(类似while(true)这种)
 *      CPU密集的意思是该任务需要大量的运算，而没有阻塞，CPU一直全速运行
 *      CPU密集任务只有在真正的多核CPU上才可能得到加速（通过多线程）
 *
 *      而在单核CPU上，无论开几个模拟的多线程任务都不可能得到加速，因为CPU总的运算能力就那些
 *
 *      CPU密集型任务配置尽可能少的线程数量：
 *      一般公式：CPU核数+1个线程的线程池
 *   IO密集型
 *   方案一：
 *      IO密集型，即该任务需要大量的IO，即大量的阻塞
 *      在单线程上运行IO密集型的任务会导致浪费大量的CPU运算能力浪费在等待
 *      所以在IO密集型任务中使用多线程可以大大的加速程序的运行，即使在单核CPU上，这种加速主要就是利用了被浪费掉的阻塞时间
 *
 *      IO密集型是，大部分线程都在阻塞，故需要多配置线程数：
 *
 *
 *      参考公式： CPU核数/(1-阻塞系数)  阻塞系数在0.8~0.9之间
 *      比如8核CPU： 8/(1-0.9) = 80 个线程数
 *
 *   方案二：
 *     由于IO密集型任务线程并不是一直在执行任务，则应配置竟可能多的线程，如CPU核数*2
 */
public class myThreadPoolDemo {
    public static void main(String[] args) {

        // 获取计算机cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 实际工作中用自定义的
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );


        try {

            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


    }

    public static void threadPoolInit() {
        // 一池5个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//      // 一池1个线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//      // 一池N个线程，随机的，看处理能力
//        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {


            for (int i = 1; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });

                // 暂停一会儿线程,使用newCachedThreadPool时效果更明显
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
