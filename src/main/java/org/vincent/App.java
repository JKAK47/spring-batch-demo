package org.vincent;

public class App {

public static void main(String[] args) {
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
