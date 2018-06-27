package org.vincent.cache.ehcache;

import java.time.Duration;
import java.util.function.Supplier;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.ExpiryPolicy;

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
						.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)).withExpiry(new ExpiryPolicy<Long,String>() {

							//當entry 添加到cache 時候回調返回設置的過期時間
							@Override
							public Duration getExpiryForCreation(Long key, String value) {
								// TODO Auto-generated method stub
								System.out.println("invoke getExpiryForCreation key ="+key +" value = "+value);
								return Duration.ofSeconds(2);
							}
							//当从cache get entry 时候 回调(entry必须存在才会回调这个方法)，返回null表示过期时间未变。返回一个Duration表示相对当前时间再过Duration 时间过期
							@Override
							public Duration getExpiryForAccess(Long key, Supplier<? extends String> value) {
								System.out.println("invoke getExpiryForAccess key ="+key +" value = "+value);
								return Duration.ofSeconds(3);
							}
							//当cache update key 对应的value =newValue 时候会回调这个方法
							@Override
							public Duration getExpiryForUpdate(Long key, Supplier<? extends String> oldValue,
									String newValue) {
								System.out.println("invoke getExpiryForUpdate key ="+key +" newValue = "+ newValue +" oldValue = "+oldValue.get());
								return null;
							}
						}))
				.build(true);// 最后build 构建时候参数true标识构建manager,这个步骤是必要的
		// 获取到构建的 preConfigured cache
		Cache<Long, String> preConfigured = cacheManager.getCache("ehcache-1", Long.class, String.class);
		// 往 preConfigured 里面插入数据
		preConfigured.put(21L, "value-1");
		preConfigured.put(22L, "value-2");
		//preConfigured.putIfAbsent(21L, "value-21");// 如果key不存在才会进行插入操作。
		System.out.println("output key = 21 "+preConfigured.get(21L));
		System.out.println("output key = 22 "+preConfigured.get(22L));
		Thread.sleep(2000);//检查Expiry 接口是否被调用
		System.out.println("output key = 21 "+preConfigured.get(21L));
		System.out.println("output key = 22 "+preConfigured.get(22L));
		preConfigured.put(21L, "sadfljs");
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
			/* Manager.close(); try - with - resources 会自动关闭 CacheManager */
		}
	}
}
