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

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.twsm.app.common.AppNewsTools;
import com.twsm.app.common.AppTools;
import com.twsm.app.common.SocketUtil;
/**
 * 
    @Title: 网易移动客户端抓取
 * @author xulifeng
   @date 2016-4-21下午05:46:59
   @version V1.0 
 *
 */
public class SpringQtzNetease {
	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	public static Map<String, String> map = new HashMap<String, String>();
	static {
		map
				.put("头条",
						"http://c.m.163.com/nc/article/headline/T1347415223240/0-20.html");
		map.put("军事",
				"http://c.m.163.com/nc/article/list/T1348648141035/0-20.html");
		map.put("科技",
				"http://c.m.163.com/nc/article/list/T1348649580692/0-20.html");
		map.put("财经",
				"http://c.m.163.com/nc/article/list/T1348648756099/0-20.html");
	}

	@Test
	public void jobNetease() throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("网易新闻移动客户端抓取任务开始");
		List<Map<String, String>> dataLists = new ArrayList<Map<String, String>>();
		String addTimeSortString = null;
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			// System.out.println(map.get(s));
			String newsString = AppTools.getwebpushResponse(map.get(s));

			JSONObject jsb = new JSONObject(newsString);
			JSONArray newListsArray = jsb.getJSONArray(jsb.keys().next()
					.toString());
			for (int i = 0; i < newListsArray.length(); i++) {
				JSONObject object = newListsArray.getJSONObject(i);
				Map<String, String> tempHashMap = new HashMap<String, String>();
				tempHashMap.put("tag", "app");
				tempHashMap.put("column_name", s);
				tempHashMap.put("app_name", "网易新闻");
				// if (object.has("replyCount")) {
				// mobappNews.setCommentNum(Integer.parseInt(object
				// .getString("replyCount")));
				// }
				if (object.has("docid")) {
					tempHashMap.put("news_id", object.getString("docid"));
				}
				if (object.has("source")) {
					tempHashMap.put("source", object.getString("source"));
				}
				if (object.has("title")) {
					tempHashMap.put("title", object.getString("title"));
				}
				if (object.has("digest")) {
					tempHashMap.put("news_text", object.getString("digest"));
				}
				if (object.has("ptime")) {
					tempHashMap.put("pub_time", object.getString("ptime"));
				}
				if (object.has("url_3w")) {
					tempHashMap.put("surl", object.getString("url_3w"));
				}
				if (object.has("imgsrc")) {
					tempHashMap.put("imgsrc", object.getString("imgsrc"));
				}
				tempHashMap.put("add_time", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
				dataLists.add(tempHashMap);
			}
		}
		SocketUtil socket = new SocketUtil(REMOTEIP,PORT);
		socket.sendData(dataLists);
	}
}
