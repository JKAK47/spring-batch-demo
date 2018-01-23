package org.vincent.csv;

import org.springframework.stereotype.Component;

/**
 * @Package: org.vincent.helloworld01 <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/24 0:52 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/24. <br/>
 */
@Component("student")
public class Student {
		private  String id;
		private  String name;
		private  String age;
		private  String score;

		public String getId() {
				return id;
		}

		public void setId(String id) {
				this.id = id;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getAge() {
				return age;
		}

		public void setAge(String age) {
				this.age = age;
		}

		public String getScore() {
				return score;
		}

		public void setScore(String score) {
				this.score = score;
		}
}
