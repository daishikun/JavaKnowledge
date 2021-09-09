package com.dsk.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalNormal00 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormal00().date(10);
                System.out.println(date);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormal00().date(1007);
                System.out.println(date);
            }
        }).start();
    }

    public String date(int seconds) {
        // 参数是毫秒，是1970年开始
        Date date = new Date(seconds * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
