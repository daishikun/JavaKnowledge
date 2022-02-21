package com.dsk.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 的第一种场景  共享一个对象数据出错，
 * 让每个线程的对象独立
 * 每个线程需要独享的对象
 * 作用：1.线程间的对象隔离      initialValue  初始化时机由我们控制
 *      2.用户轻松的获取到该该对象   set        不由我们随意的控制
 *
 */
public class ThreadLocalNormal_part1 {

    public static ExecutorService threadPool =
            Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
           threadPool.submit(() -> {
               String date = new ThreadLocalNormal_part1().date(finalI);
               System.out.println(date);
           });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {
        // 参数是毫秒，是1970年开始
        Date date = new Date(seconds * 1000);
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }
}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    public static ThreadLocal<SimpleDateFormat>
    dateFormatThreadLocal2 = ThreadLocal.withInitial(
            ()->new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
}
