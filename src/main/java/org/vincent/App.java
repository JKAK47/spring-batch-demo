package org.vincent;

import java.util.Arrays;

public class App {

public static void main(String[] args) {
	String[] result="(0:100)(1:100)".trim().split("\\)");
	System.out.println(Arrays.toString(result));
	result="0000-00-15,0070-00-00".trim().split(",");
	System.out.println(Arrays.toString(result));
	Integer integer=null;
	try {
		integer=getInteger();
	} catch (Exception e) {
		// TODO: handle exception
		if (integer ==null) {
			System.out.println("integer is null");
		}
	}
	System.out.println("sdfsdf");
}

private static Integer getInteger() {
	// TODO Auto-generated method stub
	try {
		Integer integer=10/0;
		return integer;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return null;
}
}
