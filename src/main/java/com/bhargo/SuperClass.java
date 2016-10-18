package com.piyush;

public class SuperClass {
	public int add(int a, int b){
		return (1);
	}
	
	public Object show(String str1, String str2){
		return str1;
	}
}	
class SubClass extends SuperClass{
	public short add(int a, int b){
		return 2;
	}
	
	public String show(String str1, String str2){
		return str1;
	}
}

