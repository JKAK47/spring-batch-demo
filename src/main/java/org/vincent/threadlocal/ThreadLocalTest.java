package org.vincent.threadlocal;

/**
 * 新建子线程和main线程，对比两个线程往threadLocal 变量里面插入数据后是否能分别获取到数据
 */
public class ThreadLocalTest {
		ThreadLocal<Long> longLocal = new ThreadLocal<>();
		ThreadLocal<String> stringLocal = new ThreadLocal<>();

		public ThreadLocal<Long> getLongLocal() {
				return longLocal;
		}

		public void set() {
				longLocal.set(Thread.currentThread().getId());
				stringLocal.set(Thread.currentThread().getName());
		}

		public Long getLong() {
				Long lon = longLocal.get();
				return lon;
		}

		public String getString() {
				return stringLocal.get();
		}

		public static void main(String[] args) throws InterruptedException {
				final ThreadLocalTest test = new ThreadLocalTest();

				//main 线程去set 值到threadLocal
				test.set();
				//main 线程去获取 long 和 String
				System.out.println(test.getLong());
				System.out.println(test.getString());


				Thread thread1 = new Thread(() -> {
						test.set();
						//获取thread-01线程的线程私有变量
						System.out.println("long = " + test.getLong());
						System.out.println("string = " + test.getString());
				});
				thread1.start();
				thread1.join();
				//获取main线程set 到threadLocal的值
				//先删除 main线程往LongLocal 这个threadlocal 插入的数据
				test.getLongLocal().remove();
				System.out.println(test.getLong());
				System.out.println(test.getString());
		}
}
