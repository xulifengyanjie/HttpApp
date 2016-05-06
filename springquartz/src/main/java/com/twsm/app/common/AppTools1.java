package com.twsm.app.common;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.twsm.util.ReadInputStreamTools1;

public class AppTools1 {

	public static String getwebpushResponse(String urlString) {
	    try {
			URL url = new URL(urlString);
			HttpURLConnection conn =(HttpURLConnection) url.openConnection();
			conn.setReadTimeout(50000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode()==200)
			{
				InputStream is = conn.getInputStream();
				String result = ReadInputStreamTools1.readInputStream(is);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
