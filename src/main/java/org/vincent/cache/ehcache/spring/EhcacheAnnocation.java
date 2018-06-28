package org.vincent.cache.ehcache.spring;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * ehcache 2.10.x 集成spring 
 * @author pengrong
 *
 */
@Service("ehcacheAnnocation")  
@CacheConfig(cacheNames = "myCache")  
public class EhcacheAnnocation {  
  
    /** 
     * key:该缓存entry中的key 
     * value： 
     */  
    @Cacheable(key = "11")  
    public String getName() {  
        System.out.println("get Name By executing code");  
        return "pens";  
    }  
  
    @Cacheable(key = "11", condition = "11 > 10")  
    @Caching(put = {@CachePut, @CachePut}, evict = {@CacheEvict})  
    public String getName2() {  
        System.out.println("get Name By executing code");  
        return "haha";  
    }  
  
    // 特地用了名为test的cache，覆盖了类注解中的myCache  
    @Cacheable(cacheNames = "test", key = "11")  
    public int getAge() {  
        System.out.println("get Age By executing code");  
        return 24;  
    }  
  
    @CachePut()  
    public String updateName() {  
        System.out.println("update Name By executing code");  
        return "holien";  
    }  
  
    @CacheEvict  
    public void removeName() {  
        System.out.println("remove Name By executing code");  
    }  
  
}  
