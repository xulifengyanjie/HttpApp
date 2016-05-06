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
 @Title: 人民日报移动客户端抓取
 * @author xulifeng
 * @date 2016-4-18下午03:53:46
 * @version V1.0
 * 
 */
public class SpringQtzPeople {
	@Value("${REMOTEIP}")
	private String REMOTEIP ;
	@Value("${PORT}")
	private  Integer PORT ;
	public static Map<String, String> map = new HashMap<String, String>();
	static {
		map
				.put(
						"财经",
						"http://rmrbapi.people.cn/content/getcontentlist?categoryid=10&categorytype=classify&systype=cms&timestamp=b20f869f6df90f24e6744f359e0478458db0c7d34561b95d2c7317df323eeeb5&maxid=&sinceid=&userid=5628906&client_ver=5.2.0&visit_start_time=1460964035599&city=??&citycode=&device_os=4.4&device_model=unknown-sdk&channel_num=baidu&province=??&longitude=&district=&platform=android&device_product=unknown&securitykey=eed8d9cfe5e41205d086cc96423f4b8d&sp=&device_size=240.0x320.0&MNC=26&app_key=2_2015_03_52&udid=S_c9d9e852f3954d45ba35dc5c262a2475&ctime=1460964035574&client_code=70&sessionId=a3e6372a3d1c4875b7d8e8832a663e292AvhBIxX&visit_id=1460964035599&adcode=&isoCC=us&MCC=310&latitude=&network_state=3G");
		map
				.put(
						"社会",
						"http://rmrbapi.people.cn/content/getcontentlist?categoryid=11&categorytype=classify&systype=cms&timestamp=09d38c1c4450ce0f083eaef28dc51b7918db3e8191d932078cee40b21d97a35d&maxid=&sinceid=&userid=5628906&client_ver=5.2.0&visit_start_time=1461032515028&city=??&citycode=&device_os=4.4&device_model=unknown-sdk&channel_num=baidu&province=??&longitude=&district=&platform=android&device_product=unknown&securitykey=21b13dc8a9995f52b58ea0214b7b0efb&sp=&device_size=240.0x320.0&MNC=26&app_key=2_2015_03_52&udid=S_c9d9e852f3954d45ba35dc5c262a2475&ctime=1461032515024&client_code=70&sessionId=a3e6372a3d1c4875b7d8e8832a663e292AvhBIxX&visit_id=1461032515028&adcode=&isoCC=us&MCC=310&latitude=&network_state=3G");
		map
				.put(
						"健康",
						"http://rmrbapi.people.cn/content/getcontentlist?categoryid=59&categorytype=classify&systype=cms&timestamp=24a09505eed8cef84f94699abcd2dfd7cb5aaae74bedcf926826879ddf3ed4eb&maxid=&sinceid=&userid=5628906&client_ver=5.2.0&visit_start_time=1461034336092&city=??&citycode=&device_os=4.4&device_model=unknown-sdk&channel_num=baidu&province=??&longitude=&district=&platform=android&device_product=unknown&securitykey=dd45b72efeab9edd7baae3a5f7074c61&sp=&device_size=240.0x320.0&MNC=26&app_key=2_2015_03_52&udid=S_c9d9e852f3954d45ba35dc5c262a2475&ctime=1461034336087&client_code=70&sessionId=a3e6372a3d1c4875b7d8e8832a663e292AvhBIxX&visit_id=1461034336092&adcode=&isoCC=us&MCC=310&latitude=&network_state=3G");
		map
				.put(
						"教育",
						"http://rmrbapi.people.cn/content/getcontentlist?categoryid=57&categorytype=classify&systype=cms&timestamp=6ac9e53ce82addbd9383486d9544538719fc491a8a9b643a012c14bb61713a44&maxid=&sinceid=&userid=5628906&client_ver=5.2.0&visit_start_time=1461034497418&city=??&citycode=&device_os=4.4&device_model=unknown-sdk&channel_num=baidu&province=??&longitude=&district=&platform=android&device_product=unknown&securitykey=f9062d12de23f1a020df7b28638597bc&sp=&device_size=240.0x320.0&MNC=26&app_key=2_2015_03_52&udid=S_c9d9e852f3954d45ba35dc5c262a2475&ctime=1461034497408&client_code=70&sessionId=a3e6372a3d1c4875b7d8e8832a663e292AvhBIxX&visit_id=1461034497418&adcode=&isoCC=us&MCC=310&latitude=&network_state=3G");
		map
				.put(
						"公益",
						"http://rmrbapi.people.cn/content/getcontentlist?categoryid=8&categorytype=help&systype=help&timestamp=1460963581&maxid=&sinceid=&userid=5628906&client_ver=5.2.0&visit_start_time=1461034574094&city=??&citycode=&device_os=4.4&device_model=unknown-sdk&channel_num=baidu&province=??&longitude=&district=&platform=android&device_product=unknown&securitykey=a9a06f55c5e4d0973e73477ac0c26d21&sp=&device_size=240.0x320.0&MNC=26&app_key=2_2015_03_52&udid=S_c9d9e852f3954d45ba35dc5c262a2475&ctime=1461034574087&client_code=70&sessionId=a3e6372a3d1c4875b7d8e8832a663e292AvhBIxX&visit_id=1461034574094&adcode=&isoCC=us&MCC=310&latitude=&network_state=3G");
	}

	@Test
	public void jobPeople() throws UnknownHostException, IOException {
		System.out.println("人民日报移动客户端抓取任务开始");
		List<Map<String,String>> dataLists = new ArrayList<Map<String,String>>();
			String addTimeSortString = null;
			Set<String> key = map.keySet();
			for (Iterator it = key.iterator(); it.hasNext();) {
				String s = (String) it.next();
				String newsString = AppTools.getwebpushResponse(map.get(s));
				JSONObject jb = JSONObject.fromObject(newsString);
				JSONArray jsonArray = JSONArray.fromObject(jb.get("data"));
				// System.out.println(jsonArray.size());
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsb = jsonArray.getJSONObject(i);
					// System.out.println(jsb.getString("group_data"));
					JSONArray jsonArrayGroupData = JSONArray.fromObject(jsb
							.getString("group_data"));
					for (int j = 0; j < jsonArrayGroupData.size(); j++) {
						JSONObject jsonObjectTemp = jsonArrayGroupData
								.getJSONObject(j);
						// System.out.println(jsonObjectTemp.getString("title"));
//						whereMap.put("appName", "人民日报");
//						whereMap
//								.put("title", jsonObjectTemp.getString("title"));
//						List<MobappNews> list = mobappNewsDao
//								.getByWhere(whereMap);
				
							Map<String,String> tempHashMap = new HashMap<String, String>();
							tempHashMap.put("tag", "app");
							tempHashMap.put("title", jsonObjectTemp
									.getString("title"));
							tempHashMap.put("column_name", s);
							tempHashMap.put("app_name", "人民日报");
//							mobappNews.setTitle(jsonObjectTemp
//									.getString("title"));
							tempHashMap.put("source", jsonObjectTemp
									.getString("copyfrom"));
//							mobappNews.setSource(jsonObjectTemp
//									.getString("copyfrom"));
//							tempHashMap.put("imgsrc", JSONArray.fromObject(
//									jsonObjectTemp.getString("image")).get(0)
//									.toString());
//							mobappNews.setImgsrc(JSONArray.fromObject(
//									jsonObjectTemp.getString("image")).get(0)
//									.toString());
							tempHashMap.put("pub_time", jsonObjectTemp
									.getString("news_datetime"));
//							mobappNews.setPubTime(jsonObjectTemp
//									.getString("news_datetime"));
							tempHashMap.put("surl", jsonObjectTemp
									.getString("share_url"));
//							mobappNews.setSurl(jsonObjectTemp
//									.getString("share_url"));
							tempHashMap.put("add_time",new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(new Date()));
//							mobappNews.setAddTime(AppNewsTools
//									.getNewsSortAddTime(addTimeSortString));
							dataLists.add(tempHashMap);
					
					}

				}
			}
			SocketUtil socket = new SocketUtil(REMOTEIP,PORT);
			socket.sendData(dataLists);

	}
}
