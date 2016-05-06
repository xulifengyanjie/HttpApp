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
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import com.twsm.app.common.AppTools1;
import com.twsm.app.common.SocketUtil;
/**
 * 
    @Title: 中国经济网移动客户端抓取
 * @author xulifeng
   @date 2016-4-21下午05:21:41
   @version V1.0 
 *
 */
public class SpringQtzChinaEconomy {
	@Value("${REMOTEIP}")
	private String REMOTEIP;
	@Value("${PORT}")
	private Integer PORT;
	public static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("国际新闻", "http://pad.ce.cn/sy/guoji/news_list.xml");
		map.put("金融新闻", "http://pad.ce.cn/sy/jinrong/news_list.xml");
		map.put("产业新闻", "http://pad.ce.cn/sy/chanye/news_list.xml");

	}

	@Test
	public void jobChinaEconomy() throws UnknownHostException, IOException,
			DocumentException {
		System.out.println("中国经济网移动客户端抓取任务开始");
		List<Map<String, String>> dataLists = new ArrayList<Map<String, String>>();
		Document doc = null;
		Set<String> key = map.keySet();
		Integer j = 20;
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			// System.out.println(map.get(s));
			String newsString = AppTools1.getwebpushResponse(map.get(s));
			if (newsString != null && !"".equals(newsString)) {
				doc = DocumentHelper.parseText(newsString);// 将字符串转为XML
				Element rootElt = doc.getRootElement(); // 获取根节点
				Iterator itemiIterator = rootElt.elementIterator("news");
				while (itemiIterator.hasNext()) {
					Element itemNodeEle = (Element) itemiIterator.next();
					Element eName = itemNodeEle.element("title");
					Element e1 = eName.element("a");
					Map<String, String> tempHashMap = new HashMap<String, String>();
					tempHashMap.put("tag", "app");
					tempHashMap.put("column_name", s);
					tempHashMap.put("app_name", "中国经济网");
					tempHashMap.put("source", itemNodeEle
							.elementTextTrim("source"));
					tempHashMap.put("title", e1.getTextTrim());
					tempHashMap.put("news_text", itemNodeEle
							.elementTextTrim("summary"));
					tempHashMap.put("pub_time", itemNodeEle
							.elementTextTrim("date"));
					if (s.equals("国际新闻")) {
						String url = itemNodeEle.elementTextTrim("add");
						tempHashMap.put("surl", "http://pad.ce.cn/sy/guoji"
								+ ("http://pad.ce.cn/sy/guoji" + url.replace(
										"\n", "")).substring(26));
					} else if (s.equals("金融新闻")) {
						String url = itemNodeEle.elementTextTrim("add");
						tempHashMap.put("surl", "http://pad.ce.cn/sy/jinrong"
								+ ("http://pad.ce.cn/sy/jinrong" + url.replace(
										"\n", "")).substring(28));
					} else if (s.equals("产业新闻")) {
						String url = itemNodeEle.elementTextTrim("add");
						tempHashMap.put("surl", "http://pad.ce.cn/sy/chanye"
								+ ("http://pad.ce.cn/sy/chanye" + url.replace(
										"\n", "")).substring(27));
					}
					tempHashMap.put("add_time", new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(new Date()));
					dataLists.add(tempHashMap);
				}

			}
		}

		SocketUtil socket = new SocketUtil(REMOTEIP, PORT);
		socket.sendData(dataLists);
	}
}
