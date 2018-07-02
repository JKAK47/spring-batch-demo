package org.vincent.cache;


/**
 * java中volatile关键字的含义
 * <a> http://www.cnblogs.com/aigongsi/archive/2012/04/01/2429166.html#3834881 </a>
 * @author pengrong
 *
 */
public class AppTest {

	//volatile 关键字只是代表了每次读取都是当前最新的值。并不能对于JVM中多线程同步有任何帮助
	 public volatile static int count = 0;
	 public static synchronized void inc() {
	        //这里延迟1毫秒，使得结果明显
	        try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	        }
	        count++;
	    }
	 
	    public static void main(String[] args) throws InterruptedException {
	 
	        //同时启动1000个线程，去进行i++计算，看看实际结果
	 
	        for (int i = 0; i < 1000; i++) {
	            Thread thread=new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	AppTest.inc();
	                }
	            });
	            thread.start();
	            //join方法必须在thread线程启动后调用才有效，功能是只有thread线程执行完后，main线程才能执行。
	            thread.join(2);
	        }
	        //Thread.sleep(5000);
	        //这里每次运行的值都有可能不同,可能为1000
	        System.out.println("运行结果:Counter.count=" + AppTest.count);
	    }
}
