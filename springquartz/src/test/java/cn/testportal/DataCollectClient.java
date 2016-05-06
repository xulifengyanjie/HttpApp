package cn.testportal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataCollectClient {

	public static final Logger LOG = LoggerFactory.getLogger(DataCollectClient.class);
    private static final String REMOTEIP="192.168.118.52";
    private static final int PORT=8821;
    
    public static void testSendData(List<Map<String,String>> datas) throws UnknownHostException, IOException{
    	Socket socket = new Socket(REMOTEIP, PORT);
        // 发送关闭命令
        OutputStream socketOut = socket.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        String value=objectMapper.writeValueAsString(datas);
        socketOut.write((value+"\r\n").getBytes());
        socketOut.flush();
        socketOut.write("quit".getBytes());
        socketOut.flush();
        // 接收服务器的反馈
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        String msg = null;
        while ((msg = br.readLine()) != null){
        	if (msg.equals("sucess")){
				System.out.println("数据传递成功.......");
				break;
			}
      	  socketOut.write("\r\n".getBytes());
        }
        socket.close();
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException{
    	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("title", "长沙今天有大暴雨");
    	map.put("author", "三万英尺的鹰");
    	list.add(map);
    	testSendData(list);
    }
}
