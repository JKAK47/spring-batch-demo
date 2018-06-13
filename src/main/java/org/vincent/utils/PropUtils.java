package org.vincent.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 加载属性配置文件工具类
 * @author pengrong
 *
 */
public class PropUtils {

	/**
	 * 加载属性配置文件并返回  Properties
	 * @param filePath
	 * @return
	 */
	public static Properties  getProperties(String filePath) {
		Properties prop=new Properties();
		FileInputStream fileInputStream=null;
		try{
			fileInputStream =new FileInputStream(filePath);
			prop.load(fileInputStream);
			fileInputStream.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if (fileInputStream!=null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileInputStream=null;
			}
		}
		return prop;
		
	}
	
	public static void main(String[] args) {
		String relativelyPath=System.getProperty("user.dir");
		//获取当前项目根目录 D:\Dev\WorkStation\OneLife\spring-batch-demo
		System.out.println( System.getProperty("user.dir"));
		//获取到当前电脑用户目录
		System.out.println( System.getProperty("user.home"));
		//循环遍历所有的系统属性 
		Properties properties = System.getProperties();
		Enumeration pnames = properties.propertyNames();
		while (pnames.hasMoreElements()) {
		String pname = (String) pnames.nextElement();
		System.out.print(pname + "--------------");
		System.out.println(properties.getProperty(pname));
		}
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------");
		//第一种： 通过 user.dir获取到项目根目录，然后通过这个基目录组合相对目录路径 成绝对路径定位并读取配置文件。
		properties =PropUtils.getProperties(System.getProperty("user.dir")+"/src/main/resources/log4j.properties");
		pnames = properties.propertyNames();
		while (pnames.hasMoreElements()) {
		String pname = (String) pnames.nextElement();
		System.out.print(pname + "--------------");
		System.out.println(properties.getProperty(pname));
		}
		// 第二种ClassLoader, 从: 项目根目录/target/classes/ 目录加载文件, 从系统 class loader下加载文件
		//编译后 src/main/resources目录下的资源文件都会复制一份到 "项目根目录/target/classes/" 目录下
		@SuppressWarnings("unused")
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("log4j.properties");
		inputStream = ClassLoader.getSystemClassLoader().getSystemResourceAsStream("log4j.properties");
		//getSystemResource 方法在基于项目编译后 class 文件目录 (项目根目录/target/classes/)  下  查找资源文件,
		URL url=ClassLoader.getSystemResource("demo-1/spring/log4j.properties");
		System.out.println(url.getFile());
		properties = PropUtils.getProperties(url.getPath());
		// 通过当前类的加载器去查找资源
		url =PropUtils.class.getClassLoader().getResource("log4j.properties"); 
		// 通过执行当前类的线程对应的类加载器ContextClassLoader 加载资源;加载顺序是先加载父类加载器目录下的资源，如果没有找到则加载
		//内置到虚拟机下的类加载器目录下，最后如果还是没有找到则通过 findResource方法获取资源
		inputStream =Thread.currentThread().getContextClassLoader().getResourceAsStream("log4j.properties");
		
		// 关于File 类构建一个代表文件的实例 ,File 构建的文件file实例如果传递的是相对参数 默认在项目根目录下查找
		// File 如果File构建参数不是传的绝对地址，Java 默认是在项目根目录下查找。getAbsolutePath方法返回的是基于项目根目录的绝对地址，构建的绝对地址是基于项目根目录的绝对地址
		System.out.println(new File("log4j.properties").getAbsolutePath());
		System.out.println(new File("log4j.properties").exists());
		// 返回的是 构建File实例传入的参数。
		System.out.println(new File("log4j.properties").getPath());
		
		//构建File实例通过绝对路径参数构建，getAbsolutePath()方法返回的也就是路径参数，和Path()方法返回值一致。
		System.out.println(new File(System.getProperty("user.dir")+"/src/main/resources/log4j.properties"));
		System.out.println(new File(System.getProperty("user.dir")+"/src/main/resources/log4j.properties").getAbsolutePath());
		System.out.println(new File(System.getProperty("user.dir")+"/src/main/resources/log4j.properties").getPath());
		System.out.println(new File(System.getProperty("user.dir")+"/src/main/resources/log4j.properties").exists());
	}
	
	
}
