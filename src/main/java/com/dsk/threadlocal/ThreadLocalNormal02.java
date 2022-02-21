package com.dsk.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalNormal02 {

    public static ExecutorService threadPool =
            Executors.newFixedThreadPool(10);

    static SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
           threadPool.submit(() -> {
               String date = new ThreadLocalNormal02().date(finalI);
               System.out.println(date);
           });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {
        // 参数是毫秒，是1970年开始
        Date date = new Date(seconds * 1000);
        return simpleDateFormat.format(date);
    }
}
