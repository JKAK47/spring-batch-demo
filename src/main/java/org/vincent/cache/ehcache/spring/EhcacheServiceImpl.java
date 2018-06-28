package org.vincent.cache.ehcache.spring;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
@Component("ehcacheService")
public class EhcacheServiceImpl implements EhcacheService {

	 /**
	  *  value的值是cache的名称 和 ehcache.xml 中的cache name配置保持一致，key 引用参数值，使用 井号加参数名
	  *  @Cacheable 注解标识这个方法是返回值可以 缓存的， 如果以指定的入参计算的key在cache中没找到将执行函数体，若找到则返回cache里面的值。
	  *  在缓存的有效时间内，以后访问这个方法都直接返回缓存结果，不再执行方法中的代码段。 
	  *  这个注解可以用condition属性来设置条件，如果不满足条件，就不使用缓存数据，直接执行方法。 可以使用key属性来指定key的生成规则。
	  *  验证时间到期 
	  */
    @Cacheable(value="HelloWorldCache", key="'timestamp: ' +#param")
    @Override
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
    /**
     *  ---------------------------getDataFromDB begin  缓存的key 和删除方法的key 必须一致 ---------------------------------------------
     */
    @Cacheable(value="HelloWorldCache", key="'DataFromDB:' + #key")
    @Override
    public String getDataFromDB(String key) {
        System.out.println("从数据库中获取数据，执行方法体...");
        return key + ":" + String.valueOf(Math.round(Math.random()*1000000));
    }

    /**
     * @CacheEvict 注解 表明所修饰的方法是用来删除失效或无用的缓存数据。
     * @CacheEvict属性  value：缓存名称，不能为空
     * key：缓存的key，默认为空 ，必须和插入的数据key一致
     * condition：触发条件，只有满足条件的情况才会触发清除缓存，默认为空
     * allEntries：true表示清除value中的全部缓存 ，默认为false
     */
    @CacheEvict(value="HelloWorldCache", key="'DataFromDB:' + #key")
    @Override
    public void removeDataAtDB(String key) {
        System.out.println("从数据库中删除数据");
    }
    /**
     * @CachePut 注解的方法体总是会执行方法体并触发cache put操作缓存数据
     */
    @CachePut(value="HelloWorldCache", key="'DataFromDB:' + #key")
    @Override
    public String refreshData(String key) {
    	System.out.println("模拟从数据库中加载数据");
    	return key + "::" + String.valueOf(Math.round(Math.random()*1000000));
    }
    
    
    /**
     *  --------------------------getDataFromDB end----------------------------------------------
     */
    /**
     * --------------------------user begin------------------------------------------------------------
     */
    /**
     * 默认所有 都缓存
     */
    @Cacheable(value="UserCache", key="'user:' + #userId")    
    public User findById(String userId) {  
    	System.out.println("模拟从数据库中查询数据");
    	return (User) new User(userId, "mengdee");           
    }  
    
    //清除掉UserCache中某个指定key的缓存    
    @CacheEvict(value="UserCache",key="'user:' + #userId")    
    public void removeUser(String userId) {    
    	System.out.println("UserCache remove:"+ userId);    
    }    
    /**
     * 通过condition 表示 只要userId 字符串长度小于 12 才缓存
     */
    @Cacheable(value="UserCache", condition="#userId.length()<12")    
    public boolean isReserved(String userId) {    
    	System.out.println("UserCache:"+userId);    
    	return false;    
    }
    /**
     * --------------------------user end------------------------------------------------------------
     */


    //清除掉UserCache中全部的缓存    
    @CacheEvict(value="UserCache", allEntries=true)    
    public void removeAllUser() {    
       System.out.println("UserCache delete all");    
    }

   

}
