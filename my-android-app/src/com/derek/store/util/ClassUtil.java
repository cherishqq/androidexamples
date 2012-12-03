package com.derek.store.util;

public class ClassUtil {
	
	public  static  String getFullName(Class c){
		
		String cc = c.getName();
		System.out.print(cc);
		return "";
	}
	
	public static void main(String [] args){
		getFullName(ClassUtil.class);
	}

}
