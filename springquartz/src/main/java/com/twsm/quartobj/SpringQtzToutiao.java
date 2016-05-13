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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import com.twsm.app.common.AppNewsTools;
import com.twsm.app.common.AppTools;
import com.twsm.app.common.SocketUtil;

/**
 * 
    @Title: 今日头条移动客户端抓取
 * @author xulifeng
   @date 2016-4-21下午04:56:45
   @version V1.0 
 *
 */
public class SpringQtzToutiao {
	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	public static Map<String, String> map = new HashMap<String, String>();
	private static SimpleDateFormat sf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static {
		map
				.put(
						"社会",
						"http://ic.snssdk.com/2/article/v13/stream/?detail=1&image=1&category=news_society&count=20&lac=0&cid=0&iid=156356741&device_id=2346708182&ac=3g&channel=baidu&aid=13&app_name=news_article&version_code=322&device_platform=android&device_type=Android%20SDK%20built%20for%20x86&os_api=18&os_version=4.3&openudid=19cfbd4d7edcebb8");
		map
				.put(
						"财经",
						"http://ic.snssdk.com/2/article/v13/stream/?detail=1&image=1&category=news_finance&count=20&lac=0&cid=0&iid=156356741&device_id=2346708182&ac=3g&channel=baidu&aid=13&app_name=news_article&version_code=322&device_platform=android&device_type=Android%20SDK%20built%20for%20x86&os_api=18&os_version=4.3&openudid=19cfbd4d7edcebb8");
		map
				.put(
						"军事",
						"http://ic.snssdk.com/2/article/v13/stream/?detail=1&image=1&category=news_military&count=20&lac=0&cid=0&iid=156356741&device_id=2346708182&ac=3g&channel=baidu&aid=13&app_name=news_article&version_code=322&device_platform=android&device_type=Android%20SDK%20built%20for%20x86&os_api=18&os_version=4.3&openudid=19cfbd4d7edcebb8");
		map
				.put(
						"国际",
						"http://ic.snssdk.com/2/article/v13/stream/?detail=1&image=1&category=news_world&count=20&lac=0&cid=0&iid=156356741&device_id=2346708182&ac=3g&channel=baidu&aid=13&app_name=news_article&version_code=322&device_platform=android&device_type=Android%20SDK%20built%20for%20x86&os_api=18&os_version=4.3&openudid=19cfbd4d7edcebb8");
		map
				.put(
						"科技",
						"http://ic.snssdk.com/2/article/v13/stream/?detail=1&image=1&category=news_tech&count=20&min_behot_time=1392600622&lac=0&cid=0&iid=156356741&device_id=2346708182&ac=3g&channel=baidu&aid=13&app_name=news_article&version_code=322&device_platform=android&device_type=Android%20SDK%20built%20for%20x86&os_api=18&os_version=4.3&openudid=19cfbd4d7edcebb8");
	}

	@Test
	public void jobToutiao() throws UnknownHostException, IOException {
		System.out.println("今日头条新闻移动客户端抓取任务开始");
		List<Map<String, String>> dataLists = new ArrayList<Map<String, String>>();
		String addTimeSortString = null;
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			// System.out.println(map.get(s));
			String newsString = AppTools.getwebpushResponse(map.get(s));
			JSONObject jb = JSONObject.fromObject(newsString);
			JSONArray jsonArray = JSONArray.fromObject(jb.get("data"));
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Map<String, String> tempHashMap = new HashMap<String, String>();
				tempHashMap.put("tag", "app");
				tempHashMap.put("type", "10");
				tempHashMap.put("column_name", s);
				tempHashMap.put("app_name", "今日头条");
				tempHashMap.put("title", object.getString("title"));
				tempHashMap.put("news_text", object.getString("abstract"));
				tempHashMap.put("source", object.getString("source"));
				tempHashMap.put("publish_time", sf.format(new Date(Long
						.parseLong((jsonArray.getJSONObject(i).get(
								"publish_time").toString())
								+ "000"))));
				tempHashMap.put("surl", object.getString("share_url"));
				tempHashMap.put("add_time", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
				dataLists.add(tempHashMap);
			}
		}
		SocketUtil socket = new SocketUtil(REMOTEIP,PORT);
		socket.sendData(dataLists);
	}
}
