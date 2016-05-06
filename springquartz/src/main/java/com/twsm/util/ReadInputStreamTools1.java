package com.twsm.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadInputStreamTools1 {

	public static String readInputStream(InputStream is){
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len =0;
			while((len = is.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			String result = new String(bos.toByteArray(),"GB2312");
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
