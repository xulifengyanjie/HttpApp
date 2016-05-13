package com.twsm.quartobj;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import com.twsm.app.common.AppNewsTools;
import com.twsm.app.common.AppTools;
import com.twsm.app.common.SocketUtil;

/**
 * 
 @Title: 搜狐移动客户端抓取
 * @author xulifeng
 * @date 2016-4-21下午04:02:39
 * @version V1.0
 * 
 */
public class SpringQtzSohu {
	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	public static Map<String, String> map = new HashMap<String, String>();
	static {
		map
				.put(
						"要闻",
						"http://api.k.sohu.com/api/channel/news.go?channelId=1&num=20&page=1&supportTV=1&supportLive=1&supportPaper=1&supportSpecial=1&showPic=1&picScale=2&rt=json&pull=0&isSecond=1&more=0&net=3g&p1=NTgxMjExNTk2NjU3Nzg3MjkzMg%3D%3D&gid=02ffff110611014b2013b02d6005c8c3eb75d26cb1960e&pid=-1");
		map
				.put(
						"军事",
						"http://api.k.sohu.com/api/channel/news.go?channelId=5&num=20&page=1&supportTV=1&supportLive=1&supportPaper=1&supportSpecial=1&showPic=1&picScale=2&rt=json&pull=0&isSecond=1&more=0&listUpdateTime=1385710436000&net=3g&gid=02ffff110611014b2013b02d6005c8c3eb75d26cb1960e&pid=-1");
		map
				.put(
						"社会",
						"http://api.k.sohu.com/api/channel/news.go?channelId=23&num=20&page=1&supportTV=1&supportLive=1&supportPaper=1&supportSpecial=1&showPic=1&picScale=2&rt=json&pull=0&more=0&net=3g&p1=NTgxMjExNTk2NjU3Nzg3MjkzMg%3D%3D&gid=02ffff110611014b2013b02d6005c8c3eb75d26cb1960e&pid=-1");
		map
				.put(
						"国际",
						"http://api.k.sohu.com/api/channel/news.go?channelId=45&num=20&page=1&supportTV=1&supportLive=1&supportPaper=1&supportSpecial=1&showPic=1&picScale=2&rt=json&pull=0&isSecond=1&more=0&net=3g&p1=NTgxMjExNTk2NjU3Nzg3MjkzMg%3D%3D&gid=02ffff110611014b2013b02d6005c8c3eb75d26cb1960e&pid=-1");
		map
				.put(
						"财经",
						"http://api.k.sohu.com/api/channel/news.go?channelId=4&num=20&page=1&supportTV=1&supportLive=1&supportPaper=1&supportSpecial=1&showPic=1&picScale=2&rt=json&pull=0&more=0&net=3g&gid=02ffff110611014b2013b02d6005c8c3eb75d26cb1960e&pid=-1");
	}

	@Test
	public void jobSohu() throws UnknownHostException, IOException {
		System.out.println("搜狐新闻移动客户端抓取任务开始");
		List<Map<String,String>> dataLists = new ArrayList<Map<String,String>>();
		String addTimeSortString = null;
		Map<String, String> tempHashMap =null;
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			// System.out.println(map.get(s));
			String newsString = AppTools.getwebpushResponse(map.get(s));

			JSONObject jsb = new JSONObject(newsString);
			JSONArray newListsArray = jsb.getJSONArray("articles");
			for (int i = 0; i < newListsArray.length(); i++) {
				JSONObject object = newListsArray.getJSONObject(i);
				tempHashMap = new HashMap<String, String>();
				tempHashMap.put("tag", "app");
				tempHashMap.put("type", "10");
				tempHashMap.put("column_name", s);
				tempHashMap.put("app_name", "搜狐新闻");
				tempHashMap.put("news_id", object.getString("newsId"));
				// mobappNews.setNewsId(object.getString("newsId"));
				if (object.toString().contains("\"media\"")) {
					tempHashMap.put("source", object.getString("media"));
					// mobappNews.setSource(object.getString("media"));
				}
				tempHashMap.put("title", object.getString("title"));
				// mobappNews.setTitle(object.getString("title"));
				//tempHashMap.put("news_text", object.getString("description"));
				// mobappNews.setNewsText(object.getString("description"));
				tempHashMap.put("pub_time", object.getString("time"));
				// mobappNews.setPubTime(object.getString("time"));
				tempHashMap.put("surl", "http://3g.k.sohu.com/t/n"
						+ object.getString("newsId"));
				// mobappNews.setSurl("http://3g.k.sohu.com/t/n"+object.getString("newsId"));
				if (object.toString().contains("\"listPic\"")) {
					tempHashMap.put("imgSrc", object.getString("listPic"));
					// mobappNews.setImgsrc(imgSrc);
				}
				tempHashMap.put("add_time", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
				dataLists.add(tempHashMap);
			}
		}
		SocketUtil socket = new SocketUtil(REMOTEIP,PORT);
		//SocketUtil socket = new SocketUtil("192.168.118.52",8821);
		socket.sendData(dataLists);
	}
}
