package org.vincent.cache;

import java.util.concurrent.TimeUnit;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

/**
 * Jcache 作为接口API, Ehcache 作为cache软件实现
 * 代码基于 JCache API 写的
 * @author pengrong
 *http://www.ehcache.org/documentation/3.5/107.html
 */
public class EhCacheHelloWorldJSR107 {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 使用javax.cache 定义的Jcache 接口API 必须有一个 Provider 。这里使用的提供者是 Ehcache
		 */
		// 第一步：获取到Provider 实例，定义了创建、配置、获取、管理和控制多个CacheManager。一个应用可以在运行期访问多个CachingProvider。
		//如果classpath 中有Jcache provider 实现类了，这个方法将返回 CachingProvider 实现类。这里使用的是ehcache，所以返回的是 org.ehcache.jsr107.EhcacheCachingProvider
		//如果classpath中有多个provider实现，则需要指明全路径。org.ehcache.jsr107.EhcacheCachingProvider
		//provider=Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
		CachingProvider provider = Caching.getCachingProvider();
		//第二步 CacheManager 定义了创建、配置、获取、管理和控制多个唯一命名的Cache，这些Cache存在于CacheManager的上下文中。一个CacheManager仅被一个CachingProvider所拥有。
		CacheManager manager = provider.getCacheManager();
		//第三步：cache 配置
		MutableConfiguration<Long, String> configuration =
			    new MutableConfiguration<Long, String>()  
			        .setTypes(Long.class, String.class)   //设置key 和value 类型分别为 Long ，String
			        .setStoreByValue(false)   // 存储cache entry 是引用存储
			        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 2)));// 定义cache entry 过期策略， 定义创建后2秒中
		// 第四步 创建一个命名 jCache 的cache，使用 configuration 配置
		Cache<Long, String> cache = manager.createCache("jCache", configuration); 
		// 第五步，投递数据
		cache.put(1L, "one"); 
		//获取到cache数值
		String value = cache.get(1L);  
		System.out.println(value);
		//线程睡眠 5秒
		Thread.sleep(5000);
		// 第六步 再次获取cache 的值发现获取为null 
		System.out.println(cache.get(1L));
		
		//如果key 不存在则插入
		boolean isture=cache.putIfAbsent(2L, "two");//true
		System.out.println(isture);
		isture=cache.putIfAbsent(2L, "SDFAS");// key =2L 的值已经有了，就不会再次插入 返回false
		System.out.println(isture);
		cache=manager.getCache("jCache");//获取到Cache 
		cache=manager.getCache("jCache", Long.class, String.class);  //获取到Cache 
		
		cache.close();//关闭cache
		manager.close();//关闭 cachemanager,释放所有cache 实例
	}
}
