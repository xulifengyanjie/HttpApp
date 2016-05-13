package com.twsm.quartobj;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import com.twsm.app.common.SocketUtil;
import com.twsm.dto.MobappNews;
import com.twsm.util.ReadInputStreamTools;
/**
 * 
    @Title: 新华炫闻移动客户端抓取
 * @author xulifeng
   @date 2016-4-22上午11:43:33
   @version V1.0 
 *
 */
public class SpringQtzXinhua {

	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	private static List<Map<String, String>> dataLists = new ArrayList<Map<String, String>>();

	@Test
	public void jobXinhua() throws Exception {
		System.out.println("新华炫闻移动客户端抓取任务开始");
		caijing();
		shehui();
		shizheng();
		junshi();
		SocketUtil socket = new SocketUtil(REMOTEIP,PORT);
		socket.sendData(dataLists);
	}

	public static void caijing() throws Exception {
		MobappNews mobappNews = new MobappNews();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mobappNews.setAppName("新华炫闻");
		mobappNews.setColumnName("财经");
		String path = "http://api.xw.feedss.com/news/list";
		StringBuffer params = new StringBuffer();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");// 提交模式
		conn.setRequestProperty("UserAgent", "android");
		conn.setRequestProperty("userid", "960a014d9c6611e4b5810be55b1866b7");
		conn.setRequestProperty("language", "zh_CN");
		conn.setRequestProperty("phoneResolution", "1080x1920");
		conn.setRequestProperty("channelid", "yingyonghui_xinwen");
		conn.setRequestProperty("deviceid",
				"ffffffff-f5b5-e83d-f002-adf200000000");
		conn.setRequestProperty("sdkVersion", "4.4.4");
		conn
				.setRequestProperty("User-Agent",
						"android-async-http/1.4.3 (http://loopj.com/android-async-http)");
		conn.setRequestProperty("Accept-Encoding", "gzip");
		conn.setRequestProperty("productid", "4.2.2");
		conn.setRequestProperty("phoneType", "Xiaomi MI 3");
		conn.setRequestProperty("uniqueid", "960a014d9c6611e4b5810be55b1866b7");
		conn.setRequestProperty("Host", "api.xw.feedss.com");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// conn.setConnectTimeout(10000);//连接超时 单位毫秒
		// conn.setReadTimeout(2000);//读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		params.append("type=1").append("&").append("typeId=0").append("&")
				.append("published=0").append("&").append("channelid=29");
		byte[] bypes = params.toString().getBytes();
		conn.getOutputStream().write(bypes);// 输入参数
		InputStream inStream = conn.getInputStream();
		String result = ReadInputStreamTools.readInputStream(inStream);
		JSONObject jb = new JSONObject(result);
		JSONObject jb1 = new JSONObject(jb.get("data").toString());
		JSONArray jsonArray = new JSONArray(jb1.get("list").toString());
		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jb2 = new JSONObject(jsonArray.get(i).toString());
			Map<String, String> tempHashMap = new HashMap<String, String>();
			tempHashMap.put("tag", "app");
			tempHashMap.put("type", "10");
			tempHashMap.put("column_name", "财经");
			tempHashMap.put("app_name", "新华炫闻");
			tempHashMap.put("title", jb2.getString("title"));
			tempHashMap.put("news_id", jb2.getString("newsId"));
			tempHashMap.put("news_text", jb2.getString("summary"));
			tempHashMap.put("surl", jb2.getString("shorturl"));
			tempHashMap.put("imgsrc", jb2.getString("thumbnail"));
			tempHashMap.put("pub_time", sdf.format(new Date(Long.parseLong(jb2
					.getString("published")) * 1000)));
			tempHashMap.put("add_time", new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			dataLists.add(tempHashMap);

		}
	}

	public static void shehui() throws Exception {
		MobappNews mobappNews = new MobappNews();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mobappNews.setAppName("新华炫闻");
		mobappNews.setColumnName("社会");
		String path = "http://api.xw.feedss.com/news/list";
		StringBuffer params = new StringBuffer();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");// 提交模式
		conn.setRequestProperty("UserAgent", "android");
		conn.setRequestProperty("userid", "960a014d9c6611e4b5810be55b1866b7");
		conn.setRequestProperty("language", "zh_CN");
		conn.setRequestProperty("phoneResolution", "1080x1920");
		conn.setRequestProperty("channelid", "yingyonghui_xinwen");
		conn.setRequestProperty("deviceid",
				"ffffffff-f5b5-e83d-f002-adf200000000");
		conn.setRequestProperty("sdkVersion", "4.4.4");
		conn
				.setRequestProperty("User-Agent",
						"android-async-http/1.4.3 (http://loopj.com/android-async-http)");
		conn.setRequestProperty("Accept-Encoding", "gzip");
		conn.setRequestProperty("productid", "4.2.2");
		conn.setRequestProperty("phoneType", "Xiaomi MI 3");
		conn.setRequestProperty("uniqueid", "960a014d9c6611e4b5810be55b1866b7");
		conn.setRequestProperty("Host", "api.xw.feedss.com");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// conn.setConnectTimeout(10000);//连接超时 单位毫秒
		// conn.setReadTimeout(2000);//读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		params.append("type=1").append("&").append("typeId=0").append("&")
				.append("published=0").append("&").append("channelid=32");
		byte[] bypes = params.toString().getBytes();
		conn.getOutputStream().write(bypes);// 输入参数
		InputStream inStream = conn.getInputStream();
		String result = ReadInputStreamTools.readInputStream(inStream);
		JSONObject jb = new JSONObject(result);
		JSONObject jb1 = new JSONObject(jb.get("data").toString());
		JSONArray jsonArray = new JSONArray(jb1.get("list").toString());
		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jb2 = new JSONObject(jsonArray.get(i).toString());
			Map<String, String> tempHashMap = new HashMap<String, String>();
			tempHashMap.put("tag", "app");
			tempHashMap.put("type", "10");
			tempHashMap.put("column_name", "社会");
			tempHashMap.put("app_name", "新华炫闻");
			tempHashMap.put("title", jb2.getString("title"));
			tempHashMap.put("news_id", jb2.getString("newsId"));
			tempHashMap.put("news_text", jb2.getString("summary"));
			tempHashMap.put("surl", jb2.getString("shorturl"));
			tempHashMap.put("imgsrc", jb2.getString("thumbnail"));
			tempHashMap.put("pub_time", sdf.format(new Date(Long.parseLong(jb2
					.getString("published")) * 1000)));
			tempHashMap.put("add_time", new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			dataLists.add(tempHashMap);
		}
	}

	public static void shizheng() throws Exception {
		MobappNews mobappNews = new MobappNews();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mobappNews.setAppName("新华炫闻");
		mobappNews.setColumnName("时政");
		String path = "http://api.xw.feedss.com/news/list";
		StringBuffer params = new StringBuffer();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");// 提交模式
		conn.setRequestProperty("UserAgent", "android");
		conn.setRequestProperty("userid", "960a014d9c6611e4b5810be55b1866b7");
		conn.setRequestProperty("language", "zh_CN");
		conn.setRequestProperty("phoneResolution", "1080x1920");
		conn.setRequestProperty("channelid", "yingyonghui_xinwen");
		conn.setRequestProperty("deviceid",
				"ffffffff-f5b5-e83d-f002-adf200000000");
		conn.setRequestProperty("sdkVersion", "4.4.4");
		conn
				.setRequestProperty("User-Agent",
						"android-async-http/1.4.3 (http://loopj.com/android-async-http)");
		conn.setRequestProperty("Accept-Encoding", "gzip");
		conn.setRequestProperty("productid", "4.2.2");
		conn.setRequestProperty("phoneType", "Xiaomi MI 3");
		conn.setRequestProperty("uniqueid", "960a014d9c6611e4b5810be55b1866b7");
		conn.setRequestProperty("Host", "api.xw.feedss.com");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// conn.setConnectTimeout(10000);//连接超时 单位毫秒
		// conn.setReadTimeout(2000);//读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		params.append("type=1").append("&").append("typeId=0").append("&")
				.append("published=0").append("&").append("channelid=43");
		byte[] bypes = params.toString().getBytes();
		conn.getOutputStream().write(bypes);// 输入参数
		InputStream inStream = conn.getInputStream();
		String result = ReadInputStreamTools.readInputStream(inStream);
		JSONObject jb = new JSONObject(result);
		JSONObject jb1 = new JSONObject(jb.get("data").toString());
		JSONArray jsonArray = new JSONArray(jb1.get("list").toString());
		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jb2 = new JSONObject(jsonArray.get(i).toString());
			Map<String, String> tempHashMap = new HashMap<String, String>();
			tempHashMap.put("tag", "app");
			tempHashMap.put("type", "10");
			tempHashMap.put("column_name", "时政");
			tempHashMap.put("app_name", "新华炫闻");
			tempHashMap.put("title", jb2.getString("title"));
			tempHashMap.put("news_id", jb2.getString("newsId"));
			tempHashMap.put("news_text", jb2.getString("summary"));
			tempHashMap.put("surl", jb2.getString("shorturl"));
			tempHashMap.put("imgsrc", jb2.getString("thumbnail"));
			tempHashMap.put("pub_time", sdf.format(new Date(Long.parseLong(jb2
					.getString("published")) * 1000)));
			tempHashMap.put("add_time", new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			dataLists.add(tempHashMap);

		}
	}

	public static void junshi() throws Exception {
		MobappNews mobappNews = new MobappNews();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mobappNews.setAppName("新华炫闻");
		mobappNews.setColumnName("军事");
		String path = "http://api.xw.feedss.com/news/list";
		StringBuffer params = new StringBuffer();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");// 提交模式
		conn.setRequestProperty("UserAgent", "android");
		conn.setRequestProperty("userid", "960a014d9c6611e4b5810be55b1866b7");
		conn.setRequestProperty("language", "zh_CN");
		conn.setRequestProperty("phoneResolution", "1080x1920");
		conn.setRequestProperty("channelid", "yingyonghui_xinwen");
		conn.setRequestProperty("deviceid",
				"ffffffff-f5b5-e83d-f002-adf200000000");
		conn.setRequestProperty("sdkVersion", "4.4.4");
		conn
				.setRequestProperty("User-Agent",
						"android-async-http/1.4.3 (http://loopj.com/android-async-http)");
		conn.setRequestProperty("Accept-Encoding", "gzip");
		conn.setRequestProperty("productid", "4.2.2");
		conn.setRequestProperty("phoneType", "Xiaomi MI 3");
		conn.setRequestProperty("uniqueid", "960a014d9c6611e4b5810be55b1866b7");
		conn.setRequestProperty("Host", "api.xw.feedss.com");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// conn.setConnectTimeout(10000);//连接超时 单位毫秒
		// conn.setReadTimeout(2000);//读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		params.append("type=1").append("&").append("typeId=0").append("&")
				.append("published=0").append("&").append("channelid=35");
		byte[] bypes = params.toString().getBytes();
		conn.getOutputStream().write(bypes);// 输入参数
		InputStream inStream = conn.getInputStream();
		String result = ReadInputStreamTools.readInputStream(inStream);
		JSONObject jb = new JSONObject(result);
		JSONObject jb1 = new JSONObject(jb.get("data").toString());
		JSONArray jsonArray = new JSONArray(jb1.get("list").toString());
		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jb2 = new JSONObject(jsonArray.get(i).toString());
			Map<String, String> tempHashMap = new HashMap<String, String>();
			tempHashMap.put("tag", "app");
			tempHashMap.put("type", "10");
			tempHashMap.put("column_name", "军事");
			tempHashMap.put("app_name", "新华炫闻");
			tempHashMap.put("title", jb2.getString("title"));
			tempHashMap.put("news_id", jb2.getString("newsId"));
			tempHashMap.put("news_text", jb2.getString("summary"));
			tempHashMap.put("surl", jb2.getString("shorturl"));
			tempHashMap.put("imgsrc", jb2.getString("thumbnail"));
			tempHashMap.put("pub_time", sdf.format(new Date(Long.parseLong(jb2
					.getString("published")) * 1000)));
			tempHashMap.put("add_time", new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			dataLists.add(tempHashMap);

		}
	}
}
