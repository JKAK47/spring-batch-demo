package org.vincent.mutlistep;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Package: org.vincent.mutlistep <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 *
 */
@XmlRootElement(name = "student")
public class Student {
		private int id;
		private String name;
		private int age;
		private String address;
		@XmlAttribute(name = "id")
		public int getId() {
				return id;
		}
		public void setId(int id) {
				this.id = id;
		}
		@XmlElement(name = "name")
		public String getName() {
				return name;
		}
		public void setName(String name) {
				this.name = name;
		}
		@XmlElement(name = "age")
		public int getAge() {
				return age;
		}
		public void setAge(int age) {
				this.age = age;
		}

		@XmlElement(name = "address")
		public String getAddress() {
				return address;
		}

		public void setAddress(String address) {
				this.address = address;
		}

		@Override
		public String toString() {
				return "Student{" +
								"id=" + id +
								", name='" + name + '\'' +
								", age=" + age +
								", address='" + address + '\'' +
								'}';
		}
}

