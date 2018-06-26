package org.vincent.cache.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * Ehcache 作为cache软件实现helloworld 代码实现
 * 程序代码配置 形式 
 * http://www.ehcache.org/documentation/3.5/getting-started.html
 * @author pengrong
 *
 */
public class EhCacheHelloWorld {

	public static void main(String[] args) throws InterruptedException {
		// 构建 cacheManager 并构建一个cache实例
		CacheManager cacheManager = CacheManagerBuilder
				.newCacheManagerBuilder().withCache("ehcache-1", CacheConfigurationBuilder
						.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
				.build(true);// 最后build 构建时候参数true标识构建manager
		// 获取到构建的 preConfigured cache
		Cache<Long, String> preConfigured = cacheManager.getCache("ehcache-1", Long.class, String.class);
		// 往 preConfigured 里面插入数据
		preConfigured.put(21L, "value-1");
		preConfigured.put(22L, "value-2");
		preConfigured.putIfAbsent(21L, "value-1");
		System.out.println(preConfigured.get(21L));
		System.out.println(preConfigured.get(22L));
		// 手动创建 cache 实例
		Cache<Long, String> myCache = cacheManager.createCache("myCache", CacheConfigurationBuilder
				.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)).build());

		myCache.put(1L, "da one!");
		System.out.println(myCache.get(1L));
		cacheManager.removeCache("preConfigured");

		cacheManager.close();
		// try -- catch 模式
		try (CacheManager Manager = CacheManagerBuilder
				.newCacheManagerBuilder().withCache("preConfigured", CacheConfigurationBuilder
						.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
				.build(true)) {
			Cache<Long, String> cache = Manager.getCache("preConfigured", Long.class, String.class);
			cache.put(1L, "alsdfjas");
			System.out.println(cache.get(1L));
			Manager.close();
		}
	}
}
