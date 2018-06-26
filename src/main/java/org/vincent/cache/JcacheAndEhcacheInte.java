package org.vincent.cache;

import java.io.File;
import java.net.URISyntaxException;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import org.ehcache.config.CacheRuntimeConfiguration;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.impl.config.persistence.DefaultPersistenceConfiguration;
import org.ehcache.jsr107.Eh107Configuration;
import org.ehcache.jsr107.EhcacheCachingProvider;

/**
 * JCache 和 Ehcache 集成
 * 
 * @author pengrong
 *
 */
public class JcacheAndEhcacheInte {

	public static void main(String[] args) {

		//第一种方案
		JCacheIntegrateEhcacheOne();
		JCacheIntegrateEhcacheTwo();
	}

	private static void JCacheIntegrateEhcacheTwo() {
		CachingProvider cachingProvider = Caching.getCachingProvider();//获取到provider
		EhcacheCachingProvider ehcacheProvider = (EhcacheCachingProvider) cachingProvider; //强转 为ehcache 类型的provider 
		//创建 DefaultConfiguration  ehcache配置类，并配置持久化目录参数
		DefaultConfiguration configuration = new DefaultConfiguration(ehcacheProvider.getDefaultClassLoader(),
		  new DefaultPersistenceConfiguration(getPersistenceDirectory())); 
		//构建cacheManager 将ehcache 的配置类作为参数穿进去
		CacheManager cacheManager = ehcacheProvider.getCacheManager(ehcacheProvider.getDefaultURI(), configuration);
	}
	//设置ehcache 持久化目录
	private static File getPersistenceDirectory() {
		// TODO Auto-generated method stub
		File file=null;
		try {
			file = new File(JcacheAndEhcacheInte.class.getClassLoader().getResource("").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return file;
	}

	private static void JCacheIntegrateEhcacheOne() {

		// JCache 和 Ehcache 集成第一种方案
		CachingProvider provider = Caching.getCachingProvider();
		CacheManager cacheManager = provider.getCacheManager();
		MutableConfiguration<Long, String> configuration = new MutableConfiguration<>();
		configuration.setTypes(Long.class, String.class).setStoreByValue(false);
		Cache<Long, String> cache = cacheManager.createCache("someCache", configuration);

		// 获取到只读的 JCache配置类的只读视图  CompleteConfiguration. 
		@SuppressWarnings("unchecked")
		CompleteConfiguration<Long, String> completeConfiguration = cache.getConfiguration(CompleteConfiguration.class);
		//获取到桥接  Ehcache 和 JCache 的配置类
		@SuppressWarnings("unchecked")
		Eh107Configuration<Long, String> eh107Configuration = cache.getConfiguration(Eh107Configuration.class);
		// 获取到Ehcache配置类 
		@SuppressWarnings("unchecked")
		CacheRuntimeConfiguration<Long, String> runtimeConfiguration = eh107Configuration
				.unwrap(CacheRuntimeConfiguration.class);
	}
}