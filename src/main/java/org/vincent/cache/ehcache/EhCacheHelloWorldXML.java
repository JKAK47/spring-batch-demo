package org.vincent.cache.ehcache;

import java.net.URL;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.Status;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

/**
 * Ehcache 作为cache软件实现helloworld XML实现 Cache 配置 
 * 程序代码配置 形式 
 * http://www.ehcache.org/documentation/3.5/getting-started.html
 * @author pengrong
 *
 */
public class EhCacheHelloWorldXML {

	public static void main(String[] args) throws InterruptedException {
		//寻找资源，获取资源绝对 URL,配置文件放置在src/main/resources
		URL myUrl = EhCacheHelloWorldXML.class.getClassLoader().getResource("ehcache/ehcache-config.xml") ;
		// 通过xml 文件转化 为对应的Configuration
		Configuration xmlConfig = new XmlConfiguration(myUrl);
		//实例化  CacheManager ，通过 xmlConfig 配置文件
		CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
		// Ehcache 务必需要初始化 ，让CacheManager 可用
		myCacheManager.init();
		Status status = myCacheManager.getStatus();
		if (status ==  Status.AVAILABLE) {
			System.out.println("myCacheManager status is avaliable." );
		}
		Cache<String, String> cache=myCacheManager.getCache("foo", String.class, String.class);
		cache.put("hello", "world");
		System.out.println("cache "+cache.get("hello"));
		Cache<Long, String> simpleCache=myCacheManager.getCache("simpleCache", Long.class, String.class);
		simpleCache.put(1L, "laskdjsdal");
		System.out.println(simpleCache.get(1L));
		Cache<Number, String> bar=myCacheManager.getCache("bar", Number.class, String.class);
		bar.put(10, "BAT");
		System.out.println(bar.get(10));
		myCacheManager.close();//用完后關閉
		status = myCacheManager.getStatus();
		if (status ==  Status.UNINITIALIZED) {
			System.out.println("myCacheManager status is UNINITIALIZED." );
		}
	}
}
