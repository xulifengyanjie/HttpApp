package com.twsm.quartobj;

import java.io.IOException;
import java.text.ParseException;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import com.twsm.app.common.AppNewsTools;
import com.twsm.app.common.AppTools;
import com.twsm.app.common.SocketUtil;
/**
 * 
    @Title: 凤凰移动客户端抓取
 * @author xulifeng
   @date 2016-4-21下午05:31:37
   @version V1.0 
 *
 */
public class SpringQtzFenghuang {

	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	public static Map<String, String> map = new HashMap<String, String>();
	static {
		map
				.put(
						"头条",
						"http://api.3g.ifeng.com/iosNews?id=aid=SYLB10,SYDT10&imgwidth=480,100&type=list,list&pagesize=20,20&page=1&gv=4.0.8&av=4.0.8&uid=e6824390-3a06-45cc-b076-e09f4cc42dcc&proid=ifengnews&os=android_18&df=androidphone&vt=5&screen=480x800&publishid=1156");
		map
				.put(
						"军事",
						"http://api.3g.ifeng.com/iosNews?id=aid=JS83&imgwidth=300&type=list&pagesize=20&page=1&gv=4.0.8&av=4.0.8&uid=e6824390-3a06-45cc-b076-e09f4cc42dcc&proid=ifengnews&os=android_18&df=androidphone&vt=5&screen=480x800&publishid=1156");
		map
				.put(
						"财经",
						"http://api.3g.ifeng.com/iosNews?id=aid=CJ33&imgwidth=300&type=list&pagesize=20&page=1&gv=4.0.8&av=4.0.8&uid=e6824390-3a06-45cc-b076-e09f4cc42dcc&proid=ifengnews&os=android_18&df=androidphone&vt=5&screen=480x800&publishid=1156");
		map
				.put(
						"科技",
						"http://api.3g.ifeng.com/iosNews?id=aid=KJ123,FOCUSKJ123&imgwidth=300&type=list&pagesize=20&page=1&gv=4.0.8&av=4.0.8&uid=e6824390-3a06-45cc-b076-e09f4cc42dcc&proid=ifengnews&os=android_18&df=androidphone&vt=5&screen=480x800&publishid=1156");
		map
				.put(
						"时政",
						"http://api.3g.ifeng.com/iosNews?id=aid=XW23&imgwidth=300&type=list&pagesize=20&page=1&gv=4.0.8&av=4.0.8&uid=e6824390-3a06-45cc-b076-e09f4cc42dcc&proid=ifengnews&os=android_18&df=androidphone&vt=5&screen=480x800&publishid=1156");
	}

	@Test
	public void jobFenghuang() throws JSONException, ParseException, JsonGenerationException, JsonMappingException, IOException {
		System.out.println("凤凰新闻移动客户端抓取任务开始");
		List<Map<String,String>> dataLists = new ArrayList<Map<String,String>>();
		String addTimeSortString = null;
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			String newsString = AppTools.getwebpushResponse(map.get(s));
			JSONArray jsb = new JSONArray(newsString);
			JSONObject jb1 = jsb.getJSONObject(0);
			JSONObject jsb2 = jb1.getJSONObject("body");
			JSONArray newListsArray = jsb2.getJSONArray("item");
			Integer j = 0;
			for (int i = 0; i < newListsArray.length(); i++) {
				JSONObject object = newListsArray.getJSONObject(i);
				Map<String, String> tempHashMap = new HashMap<String, String>();
				tempHashMap.put("tag", "app");
				tempHashMap.put("column_name", s);
				tempHashMap.put("app_name", "凤凰新闻");
				tempHashMap.put("news_id", object.getString("documentId"));
				tempHashMap.put("source", object.getString("source"));
				tempHashMap.put("title", object.getString("title"));
				tempHashMap.put("news_text", object.getString("introduction"));
				tempHashMap.put("surl", object.getString("commentsUrl"));
				tempHashMap.put("imgsrc", object.getString("thumbnail"));
				tempHashMap.put("add_time",  new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
				dataLists.add(tempHashMap);
			}
		}

		SocketUtil socket = new SocketUtil(REMOTEIP,PORT);
		socket.sendData(dataLists);
	}
}
