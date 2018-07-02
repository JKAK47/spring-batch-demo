package org.vincent.cache.ehcache.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ehcache/spring/Application.xml" })
public class EhcacheServiceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private EhcacheService ehcacheService;

	// 有效时间是5秒，第一次和第二次获取的值是一样的，因第三次是5秒之后所以会获取新的值
	@Test
	public void testTimestamp() throws InterruptedException {
		System.out.println("第一次调用：" + ehcacheService.getTimestamp("param"));
		Thread.sleep(2000);
		System.out.println("2秒之后调用：" + ehcacheService.getTimestamp("param"));
		Thread.sleep(4000);// 在过四秒后entry 过期了
		System.out.println("再过4秒之后调用：" + ehcacheService.getTimestamp("param"));
	}

	@Test
	public void testCache() {
		String key = "zhangsan";
		String value = ehcacheService.getDataFromDB(key); // 从数据库中获取数据...
		System.out.println("1: " + value);// 1: zhangsan:663694
		value = ehcacheService.getDataFromDB(key); // 从缓存中获取数据，所以不执行该方法体
		System.out.println("2: " + value);// 2: zhangsan:663694 使用缓存
		ehcacheService.removeDataAtDB(key); // 从数据库中删除数据
		value = ehcacheService.getDataFromDB(key); // 从数据库中获取数据...（缓存数据删除了，所以要重新获取，执行方法体）
		System.out.println("3: " + value);// 3: zhangsan:585214 新产生了数据并缓存
		System.out.println("4: " + ehcacheService.getDataFromDB(key));// 4:
																		// zhangsan:585214
	}

	/**
	 * 测试缓存注解 @CachePut 是否工作正常。@CachePut注解注释的方法每次调用都会触发方法的执行，并缓存
	 */
	@Test
	public void testPut() {
		String key = "mengdee";
		System.out.println(ehcacheService.refreshData(key)); // 模拟从数据库中加载数据
		String data = ehcacheService.getDataFromDB(key);
		System.out.println("data:" + data); // data:mengdee::527236

		ehcacheService.refreshData(key); // 模拟从数据库中加载数据
		String data2 = ehcacheService.getDataFromDB(key);
		System.out.println("data2:" + data2); // data2:mengdee::377306
	}

	/**
	 * 测试缓存注解Cacheable 是否工作正常。
	 */
	@Test
	public void testFindById() {
		User user = ehcacheService.findById("100"); // 模拟从数据库中查询数据
		System.out.println(user);
		user = ehcacheService.findById("100");
		System.out.println(user);
	}

	/**
	 * @Cacheable 注解 condition 属性是否能正常工作
	 */
	@Test
	public void testIsReserved() {
		// 两次调用都会有输出,说明在cache里面并没有缓存
		ehcacheService.isReserved("123412341234");
		ehcacheService.isReserved("123412341234");
		// 两次调用只有一次输出，说明第二次直接返回了缓存的值作为方法的返回值
		ehcacheService.isReserved("1234");
		ehcacheService.isReserved("1234");
	}

	/**
	 *  
	 */
	@Test
	public void testRemoveUser() {
		// 添加到缓存
		User user = ehcacheService.findById("1");
		System.out.println(user);
		// 再删除
		ehcacheService.removeUser("1");

		// 上面调用去除了一个元素， 不存在会执行方法体
		user = ehcacheService.findById("1");
		System.out.println(user);
	}

	@Test
	public void testRemoveAllUser() {
		User user = ehcacheService.findById("1");
		System.out.println(user);
		user = ehcacheService.findById("2");
		System.out.println(user);

		ehcacheService.removeAllUser();
		System.out.println("----------------removeAll----------------");
		user = ehcacheService.findById("1");
		System.out.println(user);
		user = ehcacheService.findById("2");
		System.out.println(user);

		// 模拟从数据库中查询数据
		// 模拟从数据库中查询数据
		// UserCache delete all
		// 模拟从数据库中查询数据
		// 模拟从数据库中查询数据
	}

	/**
	 * 测试同一个entry 缓存到多个cache里面
	 * 通过getDataFromDB方法将entry缓存到 "HelloWorldCache","UserCache" cache里面
	 * 然后通过 getData 方法去获取 UserCache这个cache里面 
	 */
	@Test
	public void testMultiCache() {
		String key = "1111111111";
		String value = ehcacheService.getDataFromDB(key);
		System.out.println(value);
		System.out.println(ehcacheService.getDataFromDB(key));
		value = ehcacheService.getData(key);
		System.out.println(value);
	}
}
