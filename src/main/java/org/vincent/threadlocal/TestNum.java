package org.vincent.threadlocal;

import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;

@ThreadSafe
public class TestNum {
	 // ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值  
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
       //覆盖ThreadLocal Value 默认初始值
       @Override
       public Integer initialValue() {
           return 0;
       }
   };

    /**
     * 这样value就是线程安全的了
     */
    @GuardedBy("this")
    private  int value;
    public synchronized int getValue() {
        return value;
    }

    // ②获取下一个序列值
    public Integer getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) throws InterruptedException {
        TestNum sn = new TestNum();  
        // ③ 3个线程共享sn，各自产生序列号  
        TestClient t1 = new TestClient(sn);  
        TestClient t2 = new TestClient(sn);  
        TestClient t3 = new TestClient(sn);  
        t1.start();  
        t2.start();  
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    /**
     * 操作 TestNum 类的 seqNum ThreadLocal 类型变量 子线程
     */
    private static class TestClient extends Thread {  
        private TestNum sn;  
  
        public TestClient(TestNum sn) {  
            this.sn = sn;  
        }  

        @Override
        public void run() {  
            for (int i = 0; i < 3; i++) {  
                // ④每个线程打出3个序列值  
            	Integer integer=sn.getNextNum();
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["  
                         + integer + "]\t");  
            }  
        }  
    }  
}
