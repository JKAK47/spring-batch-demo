package org.vincent.cache.ehcache.spring;

public class User {

	private String userId;
	private String userName;
	private String timestmp;
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "], timestmp = "+timestmp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User(String userId, String userName) {
		this.userId=userId;
		this.userName=userName;
		this.timestmp=Long.toString(System.currentTimeMillis());
	}
	public User(){
		
	}
	
	/*public static void main(String[] args) {
		System.out.println(System.getProperty("java.io.tmpdir"));
	}*/

}
