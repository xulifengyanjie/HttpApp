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
    @Title: 央视新闻移动客户端抓取
 * @author xulifeng
   @date 2016-4-21下午05:08:38
   @version V1.0 
 *
 */
public class SpringQtzYangshi {
	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	private static Map<String, String> map = new HashMap<String, String>();
	private static SimpleDateFormat sf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static {
		map
				.put(
						"要闻",
						"http://hot.news.cntv.cn/index.php?controller=list&action=mainlist&type=shizheng&n1=10&n2=20");
		map
				.put(
						"社会",
						"http://hot.news.cntv.cn/index.php?controller=list&action=mainlist&handdata_id_1=TDAT1383126846736786&handdata_id_2=TDAT1383126850500788&n1=10&n2=20");
		map
				.put(
						"军事",
						"http://hot.news.cntv.cn/index.php?controller=list&action=mainlist&handdata_id_1=TDAT1383125299479181&handdata_id_2=TDAT1383125722766236&n1=10&n2=20");
		map
				.put(
						"财经",
						"http://hot.news.cntv.cn/index.php?controller=list&action=mainlist&handdata_id_1=TDAT1383126577072655&handdata_id_2=TDAT1383126577835657&n1=10&n2=20");
	}

	@Test
	public void jobYangshi() throws UnknownHostException, IOException {
		System.out.println("央视新闻移动客户端抓取任务开始");
		List<Map<String, String>> dataLists = new ArrayList<Map<String, String>>();
		String addTimeSortString = null;
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			String newsString = AppTools.getwebpushResponse(map.get(s));
			JSONObject jb = JSONObject.fromObject(newsString);
			JSONObject jb1 = JSONObject.fromObject(jb.get("data"));
			JSONArray jsonArray = JSONArray.fromObject(jb1.get("itemList"));
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Map<String, String> tempHashMap = new HashMap<String, String>();
				tempHashMap.put("tag", "app");
				tempHashMap.put("type", "10");
				tempHashMap.put("column_name", s);
				tempHashMap.put("app_name", "央视新闻");
				JSONObject jb2 = JSONObject.fromObject(jsonArray.getJSONObject(
						i).get("itemImage"));
				// 新闻的id，字符串
				tempHashMap.put("news_id", object.getString("itemID"));
				// 新闻标题
				tempHashMap.put("title", object.getString("itemTitle"));
				// 新闻简介
				tempHashMap.put("news_text", object.getString("brief"));
				// 新闻url
				tempHashMap.put("surl", object.getString("detailUrl"));
				// 图片的url地址
				String imgSrc = jb2.get("imgUrl1").toString();
				tempHashMap.put("imgsrc", imgSrc);
				tempHashMap.put("add_time", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
				dataLists.add(tempHashMap);
			}

		}
		SocketUtil socket = new SocketUtil(REMOTEIP,PORT);
		socket.sendData(dataLists);
	}
}
