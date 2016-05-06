package com.twsm.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadInputStreamTools {

	public static String readInputStream(InputStream is){
		ByteArrayOutputStream bos=null;
		try {
			bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len =0;
			while((len = is.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			String result = new String(bos.toByteArray(),"UTF-8");
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
