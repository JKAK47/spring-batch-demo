package org.vincent.eventbus;

import java.util.ArrayList;
import java.util.Iterator;

public class App {
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args)  {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		/**
		 * 只遍历List 线程
		 */
		Thread thread1 = new Thread(){
			public void run() {
				Iterator<Integer> iterator = list.iterator();
				while(iterator.hasNext()){
					Integer integer = iterator.next();
					System.out.println(integer);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		};
		/**
		 * 遍历List 通过 遍历器的Itr 的remove方法修改List 。
		 * 在单线程下这个是不会报错的。
		 */
		Thread thread2 = new Thread(){
			public void run() {
				Iterator<Integer> iterator = list.iterator();
				while(iterator.hasNext()){
					Integer integer = iterator.next();
					if(integer==2)
						iterator.remove();  
				}
			};
		};
		thread1.start();
		thread2.start();
	}

}
