package org.vincent.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * <p>基于CAS操作实现的实现线程安全计数器，但是因为CAS操作的问题存在ABA 的问题 </p>
 * 
 * @author pengrong
 *
 */
public class Counter {
	private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        //创建 100 个线程
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        //启动 100个线程
        for (Thread t : ts) {
            t.start();
        }
        // 等待所有 100个 线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);

    }

    /**
     * 使用CAS实现线程安全计数器
     * 自旋CAS实现的基本思路就是：每个线程 循环进行CAS操作，如果失败就重试直到成功为止
     */
    private void safeCount() {
        for (;;) {
            int i = atomicI.get();
            //每次获取的值后，通过CAS操作对比发现是否修改了期望值。
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }
}
