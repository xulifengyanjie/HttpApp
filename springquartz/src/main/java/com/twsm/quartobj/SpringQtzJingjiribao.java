package com.twsm.quartobj;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import com.twsm.app.common.AppTools;
import com.twsm.app.common.SocketUtil;

/**
 * 
 @Title: 经济日报客户端抓取
 * @author xulifeng
 * @date 2016-4-19下午04:09:39
 * @version V1.0
 * 
 */
public class SpringQtzJingjiribao {
	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	public static Map<String, String> map = new HashMap<String, String>();
	static {
		map
				.put(
						"政务",
						"http://9.twsm.com.cn/service/findChannelNewsAction.do?channelId=2&cpage=1&pageSize=20");
		map
				.put(
						"头条",
						"http://9.twsm.com.cn/service/findChannelNewsAction.do?channelId=1&cpage=1&pageSize=20");
		map
				.put(
						"科技",
						"http://9.twsm.com.cn/service/findChannelNewsAction.do?channelId=121&cpage=1&pageSize=20");
		map
				.put(
						"产业",
						"http://9.twsm.com.cn/service/findChannelNewsAction.do?channelId=119&cpage=1&pageSize=20");
		map
				.put(
						"国际",
						"http://9.twsm.com.cn/service/findChannelNewsAction.do?channelId=222&cpage=1&pageSize=20");
	}

	@Test
	public void jobJingjiribao() throws JsonGenerationException,
			JsonMappingException, IOException {
		System.out.println("经济日报移动客户端抓取任务开始");
		List<Map<String, String>> dataLists = new ArrayList<Map<String, String>>();
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			// System.out.println(map.get(s));
			String newsString = AppTools.getwebpushResponse(map.get(s));

			JSONObject jsb = JSONObject.fromObject(newsString);
			JSONArray jsonArrayNewsList = JSONArray.fromObject(jsb
					.get("newsList"));

			for (int i = 0; i < jsonArrayNewsList.size(); i++) {
				JSONObject object = jsonArrayNewsList.getJSONObject(i);
				Map<String, String> tempHashMap = new HashMap<String, String>();
				tempHashMap.put("tag", "app");
				tempHashMap.put("type", "10");
				tempHashMap.put("column_name", s);
				tempHashMap.put("app_name", "经济日报");
				tempHashMap.put("source", object.getString("source"));
				tempHashMap.put("title", object.getString("title"));
				tempHashMap.put("surl", object.getString("shareUrl"));
				tempHashMap.put("imgsrc", object.getString("thumb"));
				tempHashMap.put("add_time", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
				dataLists.add(tempHashMap);
			}
		}
		SocketUtil socket = new SocketUtil(REMOTEIP, PORT);
		socket.sendData(dataLists);
	}
}
