package org.vincent.cache.ehcache.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.sf.ehcache.Element;


/**
 *  需要 ehcache = 2.10.x 版本  
 *  [Spring 集成 Ehcache ](https://blog.csdn.net/zhengholien/article/details/77282137) 
 * @author 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ehcache/spring/Application.xml"})
public class EhcacheAnnocationTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	@Qualifier("ehcacheAnnocation")
	private EhcacheAnnocation ehcacheAnnocation;
	@Test
	public void testEhcacheAnnocation() {
		// ehcache 2.10.x部分用于表示一个cache entry 实例的对象，包含(Key, Value)
		Element element=null;

		System.out.println(ehcacheAnnocation.getName2());
		// 第一次会在控制台打印方法体语句，后面2次直接返回结果，不执行打印语句  

        System.out.println(ehcacheAnnocation.getName());  
        System.out.println(ehcacheAnnocation.getName());  
        System.out.println(ehcacheAnnocation.getName());  
        // 刷新缓存，会执行打印语句，后面一次直接返回更新后的结果  
        System.out.println(ehcacheAnnocation.updateName());  
        System.out.println(ehcacheAnnocation.getName());  
        // 删除缓存，会执行语句  
        ehcacheAnnocation.removeName();  
        // 删除后第一次又会在控制台打印语句  
        System.out.println(ehcacheAnnocation.getName());  
//        System.out.println(ehcacheAnnocation.getName());  
//        System.out.println(ehcacheAnnocation.getName());  
//        System.out.println(ehcacheAnnocation.getAge());  
//        System.out.println(ehcacheAnnocation.getAge());  
//        System.out.println(ehcacheAnnocation.getName());  
	}
}
