package org.vincent.cache;
//Description: 管理缓存 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

//可扩展的功能：当chche到内存溢出时必须清除掉最早期的一些缓存对象，这就要求对每个缓存对象保存创建时间 
//http://developer.51cto.com/art/201411/456219.htm#topx

public class CacheManager { 
   private static HashMap<String,Object> cacheMap = new HashMap<String, Object>(); 

   //单实例构造方法 
   private CacheManager() { 
       super(); 
   } 
   
   /****************************************
    * 获取到基础类型数据缓存 begin
    */
   
   
   /**
    * 获取布尔值的缓存 
    * @param key
    * @return
    */
   public static boolean getSimpleFlag(String key){ 
       try{ 
           return (Boolean) cacheMap.get(key); 
       }catch(NullPointerException e){ 
           return false; 
       } 
   } 
   public static long getServerStartdt(String key){ 
       try { 
           return (Long)cacheMap.get(key); 
       } catch (Exception ex) { 
           return 0; 
       } 
   } 
   /**
    * 设置布尔值的缓存 
    * //假如为flag = true ,并缓存了该 key 对应的布尔值且为 true 则不允许被覆盖 
    * @param key
    * @param flag
    * @return
    */
   public synchronized static boolean setSimpleFlag(String key,boolean flag){ 
       if (flag && getSimpleFlag(key)) {//假如为真,并缓存了改key对应的布尔值为true 则不允许被覆盖 
           return false; 
       }else{ 
           cacheMap.put(key, flag); 
           return true; 
       } 
   } 
   /**
    * 设置Long 实例变量
    * @param key
    * @param serverbegrundt
    * @return
    */
   public synchronized static boolean setSimpleFlag(String key,long serverbegrundt){ 
       if (cacheMap.get(key) == null) { 
           cacheMap.put(key,serverbegrundt); 
           return true; 
       }else{ 
           return false; 
       } 
   } 
   
   
   /****************************************
    * 获取到基础类型数据缓存 end
    * 
    */

   
   
   /******************************************
    * 关于缓存实例 操作 begin 
    */
   
    /**
     *  获取到key 对应的缓存实例Cache
     * @param key
     * @return
     */
   //得到缓存。同步静态方法 
   private synchronized static Cache getCacheInfo(String key) { 
       return (Cache) cacheMap.get(key); 
   } 

   //判断是否存在一个缓存 
   private synchronized static boolean hasCache(String key) { 
       return cacheMap.containsKey(key); 
   } 

   /**
    * 设置载入缓存 
    * @param key
    * @param obj
    */
   public synchronized static void putCache(String key, Cache obj) { 
	   cacheMap.put(key, obj); 
   } 
   
   //获取缓存信息 
   public static Cache getCache(String key) { 

       if (hasCache(key)) { 
           Cache cache = getCacheInfo(key); 
           if (cacheExpired(cache)) { //调用判断是否终止方法 
               cache.setExpired(true); 
           } 
           return cache; 
       }else 
           return null; 
   } 

   /**
    * 载入缓存信息 
    * @param key
    * @param obj 传入的  obj 是 Cache 实例，函数方法里面又包装了一个Cache 实例
    * @param TimeOut
    * @param expired
    */
   public static void putCacheInfo(String key, Cache obj, long TimeOut,boolean expired) { 
	   if (null ==obj) {
		return;
	   }
       Cache cache = new Cache(); 
       cache.setKey(key); 
       cache.setTimeOut(TimeOut + System.currentTimeMillis()); //设置多久后更新缓存 
       cache.setValue(obj); 
       cache.setExpired(expired); //缓存默认载入时，终止状态为FALSE 
       cacheMap.put(key, cache); 
   } 
   //重写载入缓存信息方法 
   public static void putCacheInfo(String key,Cache obj,long TimeOut){ 
	   putCacheInfo(key, obj,TimeOut,false);
   } 

   //判断缓存是否终止 
   public static boolean cacheExpired(Cache cache) { 
       if (null == cache) { //传入的缓存不存在 
           return false; 
       } 
       long nowDt = System.currentTimeMillis(); //系统当前的毫秒数 
       long cacheDt = cache.getTimeOut(); //缓存内的过期毫秒数 
       if (cacheDt <= 0||cacheDt > nowDt) { //过期时间小于等于零时,或者过期时间大于当前时间时，则为FALSE 
           return false; 
       } else { //大于过期时间 即过期 
           return true; 
       } 
   } 

   //获取缓存中的大小 
   public static int getCacheSize() { 
       return cacheMap.size(); 
   } 

   //获取指定的类型的大小 
   public static int getCacheSize(String type) { 
       int k = 0; 
       Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator(); 
       String key; 
       try { 
           while (i.hasNext()) { 
               Entry<String, Object> entry = i.next(); 
               key = entry.getKey(); 
               if (key.indexOf(type) != -1) { //如果匹配则删除掉 
                   k++; 
               } 
           } 
       } catch (Exception ex) { 
           ex.printStackTrace(); 
       } 

       return k; 
   } 

   //获取缓存对象中的所有键值名称 
   public static ArrayList<String> getCacheAllkey() { 
       ArrayList<String> a = new ArrayList<String>(); 
       try { 
           Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator(); 
           while (i.hasNext()) { 
               Entry<String, Object> entry = i.next(); 
               a.add(entry.getKey()); 
           }
       } catch (Exception ex) {
    	   ex.printStackTrace();
       } 
       finally { 
             // finally 不应该有return 返回语句
       }
       return a; 
   } 

   //获取缓存对象中指定类型 的键值名称 的列表
   public static ArrayList<String> getCachekeys(String type) { 
       ArrayList<String> a = new ArrayList<String>(); 
       String key; 
       try { 
           Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator(); 
           while (i.hasNext()) { 
               Entry<String, Object> entry = i.next(); 
               key = (String) entry.getKey(); 
               if (key.indexOf(type) != -1) { 
                   a.add(key); 
               } 
           } 
       } catch (Exception ex) {} finally { 
         /*  return a; */
       } 
       return a;
   }
   
   
   /**
    * 清除所有缓存 
    */
   public synchronized static void clearAll() { 
       cacheMap.clear(); 
   } 

   /**
    * 清除某一类特定缓存,通过遍历HASHMAP下的所有对象，来判断它的KEY与传入的TYPE是否匹配 .
    * 
    * @param type
    */
   public synchronized static void clearAll(String type) { 
       Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator(); 
       String key; 
       ArrayList<String> arr = new ArrayList<String>(); 
       try { 
           while (i.hasNext()) { 
              Entry<String,Object> entry = i.next(); 
               key = entry.getKey(); 
               if (key.startsWith(type)) { //如果匹配则删除掉 
                   arr.add(key); 
               } 
           } 
           for (int k = 0; k < arr.size(); k++) { 
               clearOnly(arr.get(k)); 
           } 
       } catch (Exception ex) { 
           ex.printStackTrace(); 
       } 
   } 

   /**
    * 清除指定的key缓存 
    * @param key
    */
   public synchronized static void clearOnly(String key) { 
       cacheMap.remove(key); 
   } 

} 
