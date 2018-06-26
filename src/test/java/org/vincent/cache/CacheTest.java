package org.vincent.cache;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.vincent.custcache.Cache;
import org.vincent.custcache.CacheManager;

public class CacheTest {
	/**
	 * https://www.cnblogs.com/glacierh/p/5653512.html
	 * https://my.oschina.net/u/658658/blog/424161
	 * 一致性哈希算法 根据key 返回桶的序号 
	 * @param key
	 * @param num_buckets
	 * @return
	 */
	int ch(int key, int num_buckets) {
		Random random =new Random();  
		random.setSeed(key) ;
		int b = 0; 
		// This will track ch(key, j +1) .  
		for (int j = 1; j < num_buckets; j ++) {
			if (random.nextFloat() < 1.0/(j+1) ) 
				b = j ; 
		} 
		return b; 
	} 
	
	public static void main(String[] args) {
	  System.out.println(CacheManager.getSimpleFlag("alksd")); 
	  CacheManager.putCache("abc", new Cache("key-1", "value-1", 10, false)); 
	  Cache cache = CacheManager.getCache("abc"); 
	  CacheManager.putCache("def", new Cache("key-2", "value-2", 10, false)); 
	  CacheManager.putCache("ccc", new Cache("key-3", "value-3", 10, false)); 
	  System.out.println(CacheManager.getCacheSize());
	  CacheManager.clearOnly(""); 
	  System.out.println(CacheManager.getCacheSize());
	  Cache c = new Cache(); 
	  for (int i = 0; i < 10; i++) { 
	      CacheManager.putCache("" + i, c); 
	 } 
	  CacheManager.putCache("aaaaaaaa", c); 
	  CacheManager.putCache("abchcy;alskd", c); 
	  CacheManager.putCache("cccccccc", c); 
	  CacheManager.putCache("abcoqiwhcy", c); 
	  System.out.println("删除前的大小："+CacheManager.getCacheSize()); 
	  List<String> keys=CacheManager.getCacheAllkey(); 
	  System.out.println(keys.toString());
	  CacheManager.clearAll("aaaa"); 
	  System.out.println("删除后的大小："+CacheManager.getCacheSize()); 
	  keys=CacheManager.getCacheAllkey(); 
	  System.out.println(keys.toString());
	  
	  CacheManager.putCacheInfo("key-4", new Cache("key-4", "value-4", 10, false), 20);
	  cache =CacheManager.getCache("key-4");
	  System.out.println(cache.getValue());
	}
}
