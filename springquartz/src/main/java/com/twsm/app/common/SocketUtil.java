package com.twsm.app.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 @Title: Socket通信工具类
 * @author xulifeng
 * @date 2016-4-21上午09:44:01
 * @version V1.0
 * 
 */
public class SocketUtil {

	private String REMOTEIP ;
	private  Integer PORT ;

	public SocketUtil(String REMOTEIP, Integer PORT) {
		this.REMOTEIP=REMOTEIP;
		this.PORT=PORT;
	}

	public  void sendData(List<Map<String, String>> datas)
			throws UnknownHostException, IOException {
		System.out.println("数据开始传递.........");
		Socket socket = new Socket(REMOTEIP, PORT);
		// 发送关闭命令
		OutputStream socketOut = socket.getOutputStream();
		ObjectMapper objectMapper = new ObjectMapper();
		String value = objectMapper.writeValueAsString(datas);
		socketOut.write((value + "\r\n").getBytes("utf-8"));
		socketOut.flush();
		socketOut.write("quit".getBytes("utf-8"));
		socketOut.flush();
		// 接收服务器的反馈
		BufferedReader br = new BufferedReader(new InputStreamReader(socket
				.getInputStream()));
		String msg = null;
		while ((msg = br.readLine()) != null) {
			if (msg.equals("sucess")){
				System.out.println("数据传递成功.......");
				break;
			}
			socketOut.write("\r\n".getBytes());
		}
		socket.close();
	}
}
