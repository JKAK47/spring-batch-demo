package org.vincent.eventbus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ABC {
public static void main(String[] args) throws FileNotFoundException {
	String file="../../app/config/iModule-cmdm/config.properties";
	File file2=new File(System.getProperty("user.home"), file);
	System.out.println(file2.getPath());
	System.out.println(new FileInputStream(file2).toString());
}
}
