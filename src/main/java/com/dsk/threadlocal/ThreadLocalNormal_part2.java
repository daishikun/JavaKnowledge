package com.dsk.threadlocal;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 的第二种场景  避免传递参数的麻烦
 */
public class ThreadLocalNormal_part2 {

    public static ExecutorService threadPool =
            Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
       new Service1().process();
    }
}
class Service1{
    public void process(){
        User user = new User("AA");
        UserContextHolder.holder.set(user);
        new Service2().process();;
    }
}

class Service2{
    public void process(){
        User user = UserContextHolder.holder.get();
        UserContextHolder.holder.remove();
        System.out.println(user);
        new Service3().process();
    }
}

class Service3{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println(user);
    }
}

class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();

}

@Data
class User{
    private String name;
    public User(){
    }
    public User(String name){
        this.name = name;
    }
}


