package org.vincent.cache.ehcache;

import java.net.URL;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

/**
 * Ehcache 作为cache软件实现helloworld XML实现
 * 程序代码配置 形式 
 * http://www.ehcache.org/documentation/3.5/getting-started.html
 * @author pengrong
 *
 */
public class EhCacheHelloWorldXML {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		URL myUrl = EhCacheHelloWorldXML.class.getClassLoader().getResource("ehcache/ehcache-config.xml") ;
		Configuration xmlConfig = new XmlConfiguration(myUrl); 
		CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig); 
		Cache<String, String> cache=myCacheManager.getCache("foo", String.class, String.class);
		Cache<Long, String> simpleCache=myCacheManager.getCache("simpleCache", Long.class, String.class);
		Cache<Number, String> bar=myCacheManager.getCache("bar", Number.class, String.class);
		
	}
}
