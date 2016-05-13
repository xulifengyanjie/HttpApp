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
 @Title: 腾讯移动客户端抓取
 * @author xulifeng
 * @date 2016-4-21下午03:46:17
 * @version V1.0
 * 
 */
public class SpringQtzQq {
	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	public static Map<String, String> map = new HashMap<String, String>();
	static {
		map
				.put(
						"要闻",
						"http://r.inews.qq.com/getQQNewsIndexAndItems?qqnetwork=gsm&store=63&hw=unknow_sdk&devid=000000000000000&screen_width=480&mac=mac%2Bunknown&imsi=310260000000000&apptype=android&chlid=news_news_top&appver=18_android_3.2.1");
		map
				.put(
						"军事",
						"http://r.inews.qq.com/getQQNewsIndexAndItems?qqnetwork=gsm&store=63&hw=unknown_sdk&devid=000000000000000&screen_width=480&mac=mac%2Bunknown&imsi=310260000000000&apptype=android&chlid=news_news_mil&appver=18_android_3.2.1");
		map
				.put(
						"社会",
						"http://r.inews.qq.com/getQQNewsIndexAndItems?qqnetwork=gsm&store=63&hw=unknown_sdk&devid=000000000000000&screen_width=480&mac=mac%2Bunknown&imsi=310260000000000&apptype=android&chlid=news_news_ssh&appver=18_android_3.2.1");
		map
				.put(
						"财经",
						"http://r.inews.qq.com/getQQNewsIndexAndItems?qqnetwork=gsm&store=63&hw=unknown_sdk&devid=000000000000000&screen_width=480&mac=mac%2Bunknown&imsi=310260000000000&apptype=android&chlid=news_news_finance&appver=18_android_3.2.1");
		map
				.put(
						"科技",
						"http://r.inews.qq.com/getQQNewsIndexAndItems?qqnetwork=gsm&store=63&hw=unknown_sdk&devid=000000000000000&screen_width=480&mac=mac%2Bunknown&imsi=310260000000000&apptype=android&chlid=news_news_tech&appver=18_android_3.2.1");
	}

	@Test
	public void jobQq() throws JsonGenerationException, JsonMappingException,
			IOException {
		System.out.println("腾讯新闻移动客户端抓取任务开始");
		List<Map<String, String>> dataLists = new ArrayList<Map<String, String>>();
		String addTimeSortString = null;
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			String newsString = AppTools.getwebpushResponse(map.get(s));
			JSONObject jsb = new JSONObject(newsString);
			JSONArray jsonArray = jsb.getJSONArray("idlist");
			JSONObject jsb2 = jsonArray.getJSONObject(0);
			JSONArray newListsArray = jsb2.getJSONArray("newslist");
			Integer j = 0;
			for (int i = 0; i < newListsArray.length(); i++) {
				JSONObject object = newListsArray.getJSONObject(i);
				Map<String, String> tempHashMap = new HashMap<String, String>();
				tempHashMap.put("tag", "app");
				tempHashMap.put("type", "10");
				tempHashMap.put("column_name", s);
				tempHashMap.put("app_name", "腾讯新闻");
				tempHashMap.put("id", object.getString("id"));
				// mobappNews.setNewsId(object.getString("id"));
				tempHashMap.put("source", object.getString("source"));
				// mobappNews.setSource(object.getString("source"));
				tempHashMap.put("title", object.getString("title"));
				// mobappNews.setTitle(object.getString("title"));
				tempHashMap.put("news_text", object.getString("abstract"));
				// mobappNews.setNewsText(object.getString("abstract"));
				tempHashMap.put("pub_time", object.getString("time"));
				// mobappNews.setPubTime(object.getString("time"));
				tempHashMap.put("surl", object.getString("surl"));
				// mobappNews.setSurl(object.getString("surl"));
				if (((JSONArray) object.get("thumbnails_qqnews_photo"))
						.length() != 0) {
					String imgSrc = ((JSONArray) object
							.get("thumbnails_qqnews_photo")).getString(0);
					tempHashMap.put("imgsrc", imgSrc);
					// mobappNews.setImgsrc(imgSrc);
				}
				tempHashMap.put("add_time", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
				// mobappNews.setAddTime(AppNewsTools.getNewsSortAddTime(addTimeSortString));
				// sqlMap.startTransaction();
				// mobappNewsDao.insert(mobappNews);
				// sqlMap.commitTransaction();
				// sqlMap.endTransaction();
				dataLists.add(tempHashMap);
			}

		}
		SocketUtil socket = new SocketUtil(REMOTEIP,PORT);
		socket.sendData(dataLists);
	}
}
