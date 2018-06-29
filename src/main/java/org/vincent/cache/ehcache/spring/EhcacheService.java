package org.vincent.cache.ehcache.spring;

public interface EhcacheService {
	// 测试失效情况，有效期为5秒
	public String getTimestamp(String param);

	public String getDataFromDB(String key);

	public void removeDataAtDB(String key);

	public String refreshData(String key);

	public User findById(String userId);

	public boolean isReserved(String userId);

	public void removeUser(String userId);


	String getData(String key);

	public void removeAllUser();

}
