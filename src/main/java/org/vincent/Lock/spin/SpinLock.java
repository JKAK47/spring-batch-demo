package org.vincent.Lock.spin;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Package: org.vincent.Lock <br/>
 * @Description： java 自旋锁 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/7/9 8:03 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/7/9. <br/>
 *  <p>http://ifeve.com/java_lock_see4/</p>
 *  <p>http://ifeve.com/java_lock_see/</p>
 *  <p>第一个线程调用lock函数，因为owner一开始是null，可以设置操作owner为第一个线程的thread，获取锁</p>
 *  <p>当有第二个线程调用lock操作时由于owner值不为空，导致循环一直被执行，直至第一个线程调用unlock函数将owner设置为null，第二个线程才能进入获取锁临界区。</p>
 *  <p>由于自旋锁只是将当前线程不停地执行循环体，不进行线程状态的改变，所以响应速度更快。但当线程数不停增加时，性能下降明显，因为每个线程都需要执行，占用CPU时间。</p>
 *  <p>如果线程竞争不激烈，并且保持锁的时间段。适合使用自旋锁。</p>
 *  <p>注：该例子为非公平锁，获得锁的先后顺序，不会按照进入lock的先后顺序进行。</p>
 */

public class SpinLock {
		private AtomicReference<Thread> owner=new AtomicReference<>();
		/**
		 * 使用了CAS原子操作，如果原来的值为空，lock函数将owner设置为当前线程，并返回true
		 如果实际值并不是期望值，CAS函数将返回false
		 */
		public void lock(){
				Thread thread=Thread.currentThread();
				while (!owner.compareAndSet(null,thread)){}
		}

		/**
		 * unlock函数将owner设置为null，并且预测值为当前线程。
		 */
		public void unlock(){
				Thread current = Thread.currentThread();
				owner.compareAndSet(current, null);
		}

}
