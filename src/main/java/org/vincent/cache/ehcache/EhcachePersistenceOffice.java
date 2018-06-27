package org.vincent.cache.ehcache;

import java.io.File;
import java.net.URL;
import java.time.Duration;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

/**
 * 官方提供额持久化到磁盘的案例
 * 
 * @author liuhy
 *
 */
public class EhcachePersistenceOffice {
	public static void main(String[] args) {

		PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.with(CacheManagerBuilder.persistence(new File(getStoragePath(), "myData")))
				.withCache("threeTieredCache",// Expiry  定义数据entry 有效期的策略，这个是Cache级别。
						CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
								ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES)
										.offheap(1, MemoryUnit.MB).disk(20, MemoryUnit.MB, true))
										.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(5))).build())//构建Time-To-Live ExpiryPolicy
				.build(true);
		Cache<Long, String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", Long.class,
				String.class);
		threeTieredCache.put(1L, "stillAvailableAfterRestart");

		persistentCacheManager.close();
	}

	/**
	 * 获取到存储的基目录路径
	 * @return
	 */
	private static String getStoragePath() {
		// TODO Auto-generated method stub
		// 构建数据存放目录
		// 獲取到target/classes目录
		URL targetURL = EhCachePersistence.class.getClassLoader().getResource("");
		// 创建 target/classes/ehcache/data目录用于保存缓存数据文件
		File targetFile = new File(targetURL.getPath(), "ehcache/data");
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		return targetFile.getPath();
	}

}
