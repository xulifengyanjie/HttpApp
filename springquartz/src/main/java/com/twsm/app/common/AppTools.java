package com.twsm.app.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.twsm.util.ReadInputStreamTools;


public class AppTools {

	public static String getwebpushResponse(String urlString) {
		InputStream is=null;
		HttpURLConnection conn=null; 
	    try {
			URL url = new URL(urlString);
		   conn =(HttpURLConnection) url.openConnection();
			conn.setReadTimeout(3000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode()==200)
			{
				is = conn.getInputStream();
				String result = ReadInputStreamTools.readInputStream(is);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				conn.disconnect();
			}
		}
		return null;
	}
}
