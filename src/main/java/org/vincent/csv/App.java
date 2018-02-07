package org.vincent.csv;

import org.springframework.util.Assert;

/**
 * @Package: org.vincent.csv <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 */

public class App {
		public static void main(String[] args) {
				//表达式为false 抛出异常
				Assert.state("".equals(""), "字符串不能为空");
				Assert.isTrue(""==null,"字符串不能为空");
		}
}
