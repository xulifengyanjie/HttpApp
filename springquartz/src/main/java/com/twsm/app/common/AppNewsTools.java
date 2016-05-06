package com.twsm.app.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppNewsTools {

	public static String getNewsSortAddTime(String sort) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:");
		   
//		System.out.println(sdf.format(new Date()));
		return sdf.format(new Date())+sort;
	}

	public static void main(String[] args) {
		String addTimeSortString=null;
		int j=20;
		for(int i = 19;i>=0;i--){
			
			if(j<11){
				System.out.println(addTimeSortString="0"+(--j));
			}else {
				System.out.println(addTimeSortString=""+(--j));
			}
		}
	}
}
