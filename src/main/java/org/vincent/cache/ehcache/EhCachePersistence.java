package org.vincent.cache.ehcache;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.function.Supplier;

import org.ehcache.PersistentUserManagedCache;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.spi.service.LocalPersistenceService;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.impl.config.persistence.DefaultPersistenceConfiguration;
import org.ehcache.impl.config.persistence.UserManagedPersistenceContext;
import org.ehcache.impl.persistence.DefaultLocalPersistenceService;

/**
 * ehcache 持久化配置，既是将缓存信息存储在文件里面
 * https://blog.csdn.net/zhengholien/article/details/77149835
 * @author pengrong
 *
 */
public class EhCachePersistence {

	public static void main(String[] args) throws URISyntaxException {
		// 构建数据存放目录
		//獲取到target/classes目录
		URL targetURL = EhCachePersistence.class.getClassLoader().getResource("");
		//创建 target/classes/ehcache/data目录用于保存缓存数据文件
		File targetFile = new File(targetURL.getPath(), "ehcache/data");
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 持久化服务
		LocalPersistenceService persistenceService = new DefaultLocalPersistenceService(
				new DefaultPersistenceConfiguration(targetFile));
		//PersistentUserManagedCache 是一个包含数据生命周期比JVM长的
		PersistentUserManagedCache<Long, String> cache = UserManagedCacheBuilder
				.newUserManagedCacheBuilder(Long.class, String.class)
				.with(new UserManagedPersistenceContext<Long, String>("persistentCache-1", persistenceService))//持久化文件夹名称前缀： persistentCache-1
				.withResourcePools(ResourcePoolsBuilder.newResourcePoolsBuilder().disk(10L, MemoryUnit.MB, true)) //获取资源
				.withExpiry(new ExpiryPolicy<Long,String>() {//设置entry 过期策略
					@Override
					public  Duration getExpiryForCreation(Long key, String value) {
						return Duration.ofSeconds(3);
					}

					@Override
					public  Duration getExpiryForAccess(Long key, Supplier<? extends String> value) {
						return null;
					}

					@Override
					public  Duration getExpiryForUpdate(Long key, Supplier<? extends String> oldValue,
							String newValue) {
						return null;
					}
				})  
				.build(true);
		// 把缓存只存进硬盘里，只要 persistenceService 相同，即使关闭，再次启动还是可以读取数据
		cache.put(42L, "The Answer! 40");
		System.out.println(cache.get(42L));
		// cache.remove(44L);

		// 手动关闭和销毁
		cache.close();
		// 删除硬盘上的缓存
		// cache.destroy();

		// 停止服务
		persistenceService.stop();
	}
}
