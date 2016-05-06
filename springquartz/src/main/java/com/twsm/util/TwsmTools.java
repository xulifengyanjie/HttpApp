package com.twsm.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/*
 * 工具类
 * add by ChaiZhaohui
 * 2012-08-28
 * 
 */
@SuppressWarnings("unused")
public class TwsmTools {
	
	
	
	/**
	 * 把一个小数转换为整数的形式,
	 * 
	 * @param String currDecimal 当前的小数型的字符串
	 * @param int rate 转换后需要带几位小数
	 * @return String 例如 (0.23) return 23
	 */
	public static String diversionStr(String currDecimal) {
		//中国货币2位
		int rate = 2;
		StringBuffer result = new StringBuffer();
		int index = 0;
		if (currDecimal == null || "".equals(currDecimal) || 0 == rate) {
			return String.valueOf(currDecimal);
		}

		// 如果数字为0则直接返回

		if ("0".equals(currDecimal.trim())) {
			return currDecimal.trim();
		}

		if (currDecimal.indexOf(".") == -1) {
			result.append(currDecimal);
			for (int k = 0; k < rate; k++) {
				result.append("0");
			}
		} else {
			index = currDecimal.length() - currDecimal.indexOf(".") - 1;
			if (index < rate) {
				result.append(currDecimal
						.substring(0, currDecimal.indexOf(".")));
				result.append(currDecimal
						.substring(currDecimal.indexOf(".") + 1));
				for (int k = 0; k < rate - index; k++) {
					result.append("0");
				}
			}
			if (index == rate) {
				result.append(currDecimal
						.substring(0, currDecimal.indexOf(".")));
				result.append(currDecimal
						.substring(currDecimal.indexOf(".") + 1));
			}
			if (index > rate) {
				result.append(currDecimal
						.substring(0, currDecimal.indexOf(".")));
				result.append(currDecimal.substring(
						currDecimal.indexOf(".") + 1, currDecimal.indexOf(".")
								+ 1 + rate));
			}
		}
		while (result.indexOf("0") == 0 && result.length() - 1 == 1) {
			result = new StringBuffer(result.substring(1));
		}
		String val = result.toString();
		while (val.length() > 1 && val.indexOf("0") == 0) {
			val = val.substring(1);
		}
		return val;
	}
    
    //厘转换成元
    public static String convertlitoyuan(String dealvalue)
    {
        int valueconvert = Integer.parseInt(dealvalue);
        double dtemp = (valueconvert / (double) 10) / 100.0;
        String yuan = Double.toString(dtemp);
        int position = 0;
        if ((position = yuan.indexOf(".")) != -1)
        {
            if (yuan.substring(position + 1).length() == 1)
            {
                yuan = yuan + "0";
            }
        }
        return yuan;
    }
    
    //分转换成元
    public static String convertfentoyuan(String dealvalue)
    {
        int valueconvert = Integer.parseInt(dealvalue);
        double dtemp = (double) valueconvert / 100;
        String yuan = Double.toString(dtemp);
        int position = 0;
        if ((position = yuan.indexOf(".")) != -1)
        {
            if (yuan.substring(position + 1).length() == 1)
            {
                yuan = yuan + "0";
            }
        }
        return yuan;
    }
	
    /**
	 * @author chaizhaohui
	 * @param date
	 * @return ture or false
	 * @白名单校验


	 * @校验date是否合法
	 */
	public static boolean CheckDate(String Date) {
		if (Date == null || "".equals(Date.trim())) {
			return true;
		}
		Date = Date.trim();
		return Date.matches("^[0-9-/]{10}$");
	}
	
	public static boolean CheckMonth(String month) {
		if (month == null || "".equals(month.trim())) {
			return true;
		}
		month = month.trim();
		return month.matches("^[0-9-/]{7}$");
	}

	public static boolean CheckTime(String Time) {
		if (Time == null || "".equals(Time.trim())) {
			return true;
		}
		Time = Time.trim();
		if (Time.length() != 19) {
			return false;
		}
		String date = Time.substring(0, 10);
		String time = Time.substring(10);
		if (CheckDate(date) & time.matches("^ [0-9]{2}:[0-9]{2}:[0-9]{2}$")) {
			return true;
		}
		return false;
	}

	/**
	 * @author chaizhaohui
	 * @param email
	 * @return ture or false
	 * @白名单校验


	 * @校验email是否合法
	 */
	public static boolean CheckEmail(String email) {
		if (email == null || "".equals(email.trim())) {
			return true;
		}
		email = email.trim();
		if (email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*$")) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串中的特殊字符 & < > ” ’ ( )%+- ,替换为html编码格式
	 * 
	 * @param str
	 * @return 替换后的字符串
	 * 
	 * 
	 */
	public static String htmlReplace(String str) {
		if (null != str) {
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("\"", "&quot;");
			str = str.replaceAll("\'", "&#39;");
			str = str.replaceAll("\\(", "&#40;");
			str = str.replaceAll("\\)", "&#41;");
			str = str.replaceAll("%", "&#37;");
			str = str.replaceAll("\\+", "&#43;");
			str = str.replaceAll("-", "&#45;");
			str = str.replaceAll("·", "&#183;");
		}
		return str;
	}
	/*
	 * 进入页面操作转换
	 * 
	 */
	public static String htmlReplacePage(String str) {
		if (null != str) {
			str = str.replaceAll("&amp;","&");
			str = str.replaceAll("&lt;","<");
			str = str.replaceAll("&gt;",">");
			str = str.replaceAll("&quot;","\"");
			str = str.replaceAll("&#39;","\'");
			str = str.replaceAll("&#40;","\\(");
			str = str.replaceAll("&#41;","\\)");
			str = str.replaceAll("&#37;","%");
			str = str.replaceAll("&#43;","\\+");
			str = str.replaceAll("&#45;","-");
			str = str.replaceAll("&#183;","·");
		}
		return str;
	}

	/**
	 * sql语句中替换掉-- 和;
	 * 
	 * @param str
	 * @return 替换后的语句
	 */
	public static String sqlReplace(String str) {
		if (null != str) {
			// str = str.replaceAll("select ", " ");
			// str = str.replaceAll("delete ", " ");
			// str = str.replaceAll("update ", " ");
			// str = str.replaceAll("create ", " ");
			// str = str.replaceAll("insert ", " ");
			// str = str.replaceAll("or ", " ");
			str = str.replaceAll("--", " ");
			str = str.replaceAll(";", " ");
		}
		return str;
	}
	
	
    


    /**
     * 把字符串分割成二维数组，使用 & | 分割。支持每一行列数不相同，用于小规模的分割。

     * @param value 需要分割的字符串

     * @return 分割后的数组
     * @roseuid 3C85E0430185
     */
    @SuppressWarnings("unchecked")
	public static String[][] parse2Ds(String value)
    {
        List total = new ArrayList();
        int index1, index2;
        index1 = 0;
        index2 = value.indexOf("&", index1);
        String temp1;
        List temp2;
        int i1, i2;
        boolean breakTime = false;
        while (true)
        {
            if (index2 == -1)
            {
                breakTime = true;
                temp1 = value.substring(index1);
            }
            else
            {
                temp1 = value.substring(index1, index2);
            }
            i1 = 0;
            i2 = temp1.indexOf("|", i1);
            temp2 = new ArrayList();
            while (i2 != -1)
            {
                temp2.add(temp1.substring(i1, i2));
                i1 = i2 + 1;
                i2 = temp1.indexOf("|", i1);
            }
            temp2.add(temp1.substring(i1));
            total.add(convert1D(temp2));
            if (breakTime)
            {
                break;
            }
            index1 = index2 + 1;
            index2 = value.indexOf("&", index1);
        }
        return convert2D(total);
    }
    /**
     * 把列表转换成字符串数组，列表中存放的是String对象。

     * @param list 列表
     * @return 转换后的字符串数组

     */
    @SuppressWarnings("unchecked")
	public static String[] convert1D(Collection list)
    {
        if (list.size() == 0)
        {
            return null;
        }
        
        Iterator it = list.iterator();
        String[] result = new String[list.size()];
        for (int i = 0; it.hasNext(); i++)
        {
            result[i] = (String) it.next();
        }
        return result;
    }
    
    /**
     * 把列表转换成字符串二维数组，列表中存放的是String[]对象。

     * @param list 列表
     * @return 转换后的字符串二维数组

     */
    @SuppressWarnings("unchecked")
	public static String[][] convert2D(Collection list)
    {
        if (list == null || list.size() == 0)
        {
            return null;
        }
        
        Iterator it = list.iterator();
        String[][] result = new String[list.size()][];
        for (int i = 0; it.hasNext(); i++)
        {
            result[i] = (String[]) it.next();
        }
        return result;
    }
    
    /**
     * 将字符串中的&换为回车换行，将|换为tab键,在将字符串下载到客户端的文件中时，<br>
     * 使用该函数对其做转换<br>
     * @param data String<br>
     * @return String,转换后的字符串<br>
     */
    public static String convertStringToDownLoad(String data)
    {
        if (data == null || data.equals(""))
        {
            return data;
        }
        
        StringBuffer sbData = new StringBuffer(data);
        int iPos = 0;
        while (iPos < sbData.length())
        {
            if (sbData.charAt(iPos) == '|')
            {
                sbData.replace(iPos, iPos + 1, "\t");
                iPos++;
            }
            
            else if (sbData.charAt(iPos) == '&')
            {
                sbData.replace(iPos, iPos + 1, "\r\n");
                iPos += 2;
            }
            else
            {
                iPos++;
            }
        }
        return sbData.toString();
    }
    
    /**
     * 把字符串分割成二维int数组，使用 & | 分割。支持每一行列数不相同，用于小规模的分割。

     * @param value 需要分割的字符串

     * @return 分割后的数组
     * @roseuid 3C85E0430185
     */
    public static int[][] parse2Di(String value)
    {
        String[][] temp = parse2Ds(value);
        if (temp == null)
        {
            return null;
        }
        
        int[][] intTemp = new int[temp.length][];
        int length;
        for (int i = 0; i < temp.length; i++)
        {
            length = temp[i].length;
            if (length == 1 && temp[i][0].length() == 0)
            {
                continue;
            }
            intTemp[i] = new int[length];
            for (int j = 0; j < length; j++)
            {
                intTemp[i][j] = Integer.parseInt(temp[i][j]);
            }
        }
        return intTemp;
    }
    
    
    /**
     * 把数组组合成字符串，使用& 分割。

     * @param value 需要组合的数组
     * @return 组合后的字符串

     * @roseuid 3C85E082021C
     */
    public static String assemble(String[] value)
    {
        if (value == null || value.length == 0)
        {
            return "";
        }
        
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < value.length; i++)
        {
            temp.append(value[i]);
            temp.append('&');
        }
        temp.deleteCharAt(temp.length() - 1);
        return temp.toString();
    }
    
    /**
     * 把数组组合成字符串，使用| 分割。

     * @param value 需要组合的数组
     * @return 组合后的字符串

     * @roseuid 3C85E082021C
     */
    public static String assemble2(String[] value)
    {
        if (value == null || value.length == 0)
        {
            return "";
        }
        
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < value.length; i++)
        {
            temp.append(value[i]);
            temp.append('|');
        }
        temp.deleteCharAt(temp.length() - 1);
        return temp.toString();
    }
    
    /**
     * 把数组组合成字符串，使用 outDivider，inDivider 分割。

     * @param value 需要组合的数组
     * @return 组合后的字符串

     * @roseuid 3C85E08E0219
     */
    public static String assemble(String[][] value, String outDivider,
            String inDivider)
    {
        if (value == null || value.length == 0 || value[0] == null
                || value[0].length == 0)
        {
            return "";
        }
        
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < value.length; i++)
        {
            for (int j = 0; j < value[i].length; j++)
            {
                temp.append(value[i][j]);
                temp.append(inDivider);
            }
            temp.deleteCharAt(temp.length() - 1);
            temp.append(outDivider);
        }
        temp.deleteCharAt(temp.length() - 1);
        return temp.toString();
    }
    
    /**
     * 把数组组合成字符串，使用 &，| 分割。

     * @param value 需要组合的数组
     * @return 组合后的字符串

     * @roseuid 3C85E08E0219
     */
    public static String assemble(String[][] value)
    {
        if (value == null || value.length == 0 || value[0] == null
                || value[0].length == 0)
        {
            return "";
        }
        
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < value.length; i++)
        {
            for (int j = 0; j < value[i].length; j++)
            {
                temp.append(value[i][j]);
                temp.append('|');
            }
            temp.deleteCharAt(temp.length() - 1);
            temp.append('&');
        }
        temp.deleteCharAt(temp.length() - 1);
        return temp.toString();
    }
	
	/**
	*将时间格式HHmmss转换成界面显示的形式
	*/

	public static String formatDatetime(String sTime)
	{
		SimpleDateFormat oldSdf = new SimpleDateFormat("HHmmss");

		Date date = null;
		try
		{
			date = oldSdf.parse(sTime);
	    } catch (ParseException e)
        {
			e.printStackTrace();			
		} 

		SimpleDateFormat newSdf = new SimpleDateFormat("HH:mm:ss");
		return newSdf.format(date);

	}
	
	 /**
     * 当天日期日间函数
     * @param
     * @return 返回当天日期日间YYYY-MM-DD hh:mm:ss
     */
    @SuppressWarnings("deprecation")
	public static String getTodayDateTime()
    {
        Date nowDate = new Date();
        
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int date = nowDate.getDate();
        int hour = nowDate.getHours();
        int minute = nowDate.getMinutes();
        int second = nowDate.getSeconds();
        
        String mon = Integer.toString(month);
        if (mon.length() == 1)
        {
            mon = "0" + mon;
        }
        String dat = Integer.toString(date);
        if (dat.length() == 1)
        {
            dat = "0" + dat;
        }
        String hou = Integer.toString(hour);
        if (hou.length() == 1)
        {
            hou = "0" + hou;
        }
        String min = Integer.toString(minute);
        if (min.length() == 1)
        {
            min = "0" + min;
        }
        String sec = Integer.toString(second);
        if (sec.length() == 1)
        {
            sec = "0" + sec;
        }
        
        String loginTime = Integer.toString(year) +"-"+ mon+"-" + dat +" "+ hou +":"+ min+":" + sec; //得到时间字符串
        
        return loginTime;
    }
	
	/**
     * 当天日期函数
     * 
     * @param
     * @return 返回当天日期
     */
    public static String getTodayDate()
    {
        //
        Calendar RightNow = Calendar.getInstance();
        return changeDatetoString(RightNow);
    }
    
    
    /**
     * 当天日期之前或之后N天的日期
     * @param 
     * @return 返回YYYYMMDD
     */
    public static String getTodayBeforeOrAfterDate(int dayNum)
    {
        //
        Calendar RightNow = Calendar.getInstance();
        RightNow.add(Calendar.DATE, dayNum);
        return changeDatetoString(RightNow);
    }
    /**
     * 当天日期之前或之后N天的日期
     * @param 
     * @return 返回YYYYMMDD
     */
    public static String getTodayBeforeOrAfterDateTime(int dayNum)
    {
    	//
    	Calendar RightNow = Calendar.getInstance();
    	RightNow.add(Calendar.DATE, dayNum);
    	return changeDatetoString(RightNow);
    }
    /**
     * 当天月份之前或之后N月的日期
     * @param 
     * @return 返回YYYYMMDD
     */
    public static String getTodayBeforeOrAfterMonth(int monthNum)
    {
        //
        Calendar RightNow = Calendar.getInstance();
        RightNow.add(Calendar.MONTH, monthNum);
        return changeDatetoString(RightNow);
    }
    
    /**
     * 日期转换函数,把日历转换成字符串格式 YYYYMMDD
     * 
     * @param Calendar
     * @return 返回日期字符串


     */
    public static String changeDatetoString(Calendar cDate)
    {
        int Year;
        int Month;
        int Day;
        String sDate = "";
        
        Year = cDate.get(Calendar.YEAR);
        Month = cDate.get(Calendar.MONTH) + 1;
        Day = cDate.get(Calendar.DAY_OF_MONTH);
        
        sDate = Integer.toString(Year);
        
        if (Month >= 10)
        {
            sDate = sDate + Integer.toString(Month);
        }
        else
        {
            sDate = sDate + "0" + Integer.toString(Month);
        }
        
        if (Day >= 10)
        {
            sDate = sDate + Integer.toString(Day);
        }
        else
        {
            sDate = sDate + "0" + Integer.toString(Day);
        }
        return sDate;
    }
    
    /**
     * 取得当前月的天数
     */
    public static int getDayNum(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        
        switch (month)
        {
            case 2:
            {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                {
                    return 29;
                }
                else
                {
                    return 28;
                }
            }
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            default:
                return 30;
        }
    }
    /**
     * 得到当前时间与指定时间之间的差值：单位 秒。
     * 指定日期格式 yyyy-MM-dd HH:mm:ss
     * @param addTime
     * @return
     * @throws ParseException
     */
   public static String getNowDsecond(String addTime) throws ParseException{
	   	Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(addTime);
	   	Date nowDate = new GregorianCalendar().getTime();
		long subSeconds = (nowDate.getTime()-startDate.getTime())/(1000); 
		return ""+subSeconds;
   }
   /**
    * 把妙转换成2013-12-05 14:34:15格式的时间
    * @param times
    * @return
    */
   public static String getFormatTime(String times){
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   
	   return sdf.format(new Date(Long.parseLong(times+"000")));
   }
   /**
    * 把妙转换成2013-12-05 14:34:15格式的时间
    * @param times 2013/12/05 14:59:27
    * @return
 * @throws ParseException 
    */
   public static String getFormatTimeForString(String times) throws ParseException{
	   Date startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(times);
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   return sdf.format(startDate);
   }

    
    /*
     * 生成六位随机数
     */
    public static String getRandom(){
    	
    	Random random = new Random();
    	String randstr = "";
    	int next =random.nextInt(999999); 
    	randstr = ""+next;
    	return randstr;
    }
    
    //删除文件夹
    //param folderPath 文件夹完整绝对路径
    public static void delFolder(String folderPath) {
       try {
          delAllFile(folderPath); //删除完里面所有内容
          String filePath = folderPath;
          filePath = filePath.toString();
          java.io.File myFilePath = new java.io.File(filePath);
          myFilePath.delete(); //删除空文件夹
       } catch (Exception e) {
         e.printStackTrace(); 
       }
  }

     //删除指定文件夹下所有文件
     //param path 文件夹完整绝对路径
     public static boolean delAllFile(String path) {
         boolean flag = false;
         File file = new File(path);
         if (!file.exists()) {
           return flag;
         }
         if (!file.isDirectory()) {
           return flag;
         }
         String[] tempList = file.list();
         File temp = null;
         for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
               temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
               temp.delete();
            }
            if (temp.isDirectory()) {
               delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
               delFolder(path + "/" + tempList[i]);//再删除空文件夹
               flag = true;
            }
         }
         return flag;
       }
    
    public static void main(String[] args) throws ParseException {
		//System.out.println(diversionStr("2"));
		//System.out.println(convertfentoyuan("0"));
		
		 //Date nowDate = new GregorianCalendar().getTime();
    	
//		 Date startDate = new GregorianCalendar(2012, 2, 15,0,0,0).getTime();
//         Date passDate = new GregorianCalendar(2009, 2, 15,0,0,0).getTime();
//    	Date startDate = new SimpleDateFormat("yyyy-m-d").parse("");
//    	Date passDate = new SimpleDateFormat("yyyy-m-d").parse("");
//		 long subDays = (startDate.getTime()-passDate.getTime())/(24*60*60);
//		 System.out.println(subDays);
System.out.println(getFormatTime("1386224880"));
		
	}

}
