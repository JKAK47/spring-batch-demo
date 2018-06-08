package org.vincent.translate;

import java.net.URL;

/**
 * 有道翻译测试案例
 *
 */
public class YoudaoTranslateTest {

	  public static void main(String[] args) throws Exception {
	       String fromText="你好";
	        //System.out.println(TranslateUtils.translateFromEng(fromText));
	       System.out.println("SDFSD");
	       URL url = Thread.currentThread().getContextClassLoader().getResource("file/印尼项目国际化清单.txt");
	       System.out.println(url.toString());
	       url = YoudaoTranslateTest.class.getClassLoader().getResource("file/印尼项目国际化清单.txt");
	       System.out.println(url);
	       url = YoudaoTranslateTest.class.getClassLoader().getResource("QRCode.JPG");
	       System.out.println(url);
	       url = ClassLoader.getSystemClassLoader().getResource("file/印尼项目国际化清单.txt");
	       System.out.println(url);
	       System.out.println(url.getFile());
	       /**
	       * class.getResource 形式加载资源，如果资源在当前java文件package目录下，那么形式就是:YoudaoTranslateTest.class.getResource("file/印尼项目国际化清单.txt")
	       * 如果资源文件在classes目录下，也就是在class文件的跟目录下，那么调用形式就是: YoudaoTranslateTest.class.getResource("/file/印尼项目国际化清单.txt")
	       */

	       url = YoudaoTranslateTest.class.getResource("/file/印尼项目国际化清单.txt");
	       System.out.println(url);
	       System.out.println(url.getFile());
	       System.out.println(YoudaoTranslateTest.class.getResource("").getFile()); // 获取到当前类绝对路径
	    }

}
