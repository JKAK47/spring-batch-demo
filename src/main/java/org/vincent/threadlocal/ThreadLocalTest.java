package org.vincent.threadlocal;

public class ThreadLocalTest {
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();
 
     
    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }
     
    public Long getLong() {
    	Long lon=longLocal.get();
        return lon;
    }
     
    public String getString() {
        return stringLocal.get();
    }
     
    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();
         
         
        //test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());
     
         
        Thread thread1 = new Thread(){
            public void run() {
                test.set();
                //获取thread-01线程的线程私有变量
                System.out.println(test.getLong());
                System.out.println(test.getString());
            };
        };
        thread1.start();
        thread1.join();
        //获取main线程 
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
