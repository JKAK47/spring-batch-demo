package org.vincent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class AppTest {

	public static void main(String[] args) {
		/**
		 * Collection 类别接口
		 */
		/** ArrayList 可以容纳 null 值，而且还是多个 */
		List<Object> arrayList = new ArrayList<>();
		arrayList.add(null);
		arrayList.add(null);
		arrayList.add(null);
		arrayList.forEach(System.out::println);
		System.out.println("----------ArrayList stop -----------");
		
		/** LinkedList 可以容纳 null 值，而且还是多个 */
		List<Object> linkedList = new LinkedList<>();
		linkedList.add(null);
		linkedList.add(null);
		linkedList.add(null);
		linkedList.add(null);
		linkedList.forEach(System.out::println);
		System.out.println("-----------LinkedList stop----------");
		
		/** HashSet 可以容纳 null 值，但是只能一个  */
		HashSet<Object> hashSet = new HashSet<>();
		hashSet.add(null);
		hashSet.add(null);// 最终只能添加一个
		hashSet.forEach(System.out::println);
		hashSet.remove(null); //可以将set里面的null 元素 删除
		System.out.print("//");
		//再次输出为空
		hashSet.forEach(System.out::println);
		System.out.println("----------HashSet stop -----------");
		
		/**
		 * Map 类别接口
		 */
		
		/** HashMap 可以容纳 null 值，key, value 都可以为 null  */
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put(null, null);
		hashMap.put(null, null);
		/** 从输出结果看，添加两次 key=null,value=null, 将只有第一次添加是有意义的，第二次将覆盖第一次添加 */
		hashMap.forEach((key, value) -> System.out.println(key + ":" + value));
		/** 根据key=null ,将key=null,value=null 的value 覆盖。 */
		hashMap.put(null, new Object());
		hashMap.forEach((key, value) -> System.out.println(key + ":" + value));
		System.out.println("-----------HashMap stop----------");
		
		
		/** Hashtable key和value 都不能为 null */
		Hashtable<Object, Object> hashtable = new Hashtable<>();
		// key,value 都为null 不能添加
		//hashtable.put(null, null);
		hashtable.put(new Object(), new Object());
		hashtable.forEach((key, value) -> System.out.println(key + ":" + value));
		System.out.println("-----------Hashtable stop----------");
	}
}
