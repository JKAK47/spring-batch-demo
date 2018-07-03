package org.vincent.enums.enum03.service;

public class PrintTest {

	public static void main(String[] args) {
		System.out.println("--------------begin------------");
		Print print=Print.HUAWEI;
		System.out.println(print.getInfo());
		print.print();
		System.out.println("index : "+print.getIndex()+" name: "+print.getName());
		System.out.println(print.ordinal()+print.name());
	}
}
