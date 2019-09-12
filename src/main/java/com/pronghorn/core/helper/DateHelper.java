package com.pronghorn.core.helper;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** Date 日期处理
 * 
 * @author 王磊
 * @version 2015年8月28日 15:31:39 王磊 补充头信息 */
public class DateHelper {

	/** 计算2个日期的天数之差 */
	public static int diff(Date startDate, Date endDate) {
		DateTime startDateJoda = new DateTime(startDate);
		DateTime endDateJoda = new DateTime(endDate);
		return Days.daysBetween(startDateJoda, endDateJoda).getDays() + 1;
	}

	/** 大于等于 */
	public static boolean ge(Date date, Date compareToDate) {
		DateTime dateTime = new DateTime(date);
		DateTime compareToDateTime = new DateTime(compareToDate);
		int result = dateTime.compareTo(compareToDateTime);
		return result > -1;
	}

	/** 大于 */
	public static boolean gt(Date date, Date compareToDate) {
		DateTime dateTime = new DateTime(date);
		DateTime compareToDateTime = new DateTime(compareToDate);
		int result = dateTime.compareTo(compareToDateTime);
		return result > 0;
	}

	/** 小于等于 */
	public static boolean le(Date date, Date compareToDate) {
		DateTime dateTime = new DateTime(date);
		DateTime compareToDateTime = new DateTime(compareToDate);
		int result = dateTime.compareTo(compareToDateTime);
		return result < 1;
	}

	/** 小于 */
	public static boolean lt(Date date, Date compareToDate) {
		DateTime dateTime = new DateTime(date);
		DateTime compareToDateTime = new DateTime(compareToDate);
		int result = dateTime.compareTo(compareToDateTime);
		return result < 0;
	}

	/** 获取当前年的字符串 */
	public static String getCurrentYear() {
		return DateTime.now().getYear() + "";
	}

	/** 获取当前年的短字符串 */
	public static String getCurrentShortYear() {
		return StringHelper.right(getCurrentYear(), 2);
	}

	/** 获取当前日期 yyyy-MM-dd */
	public static String getCurrentDay() {
		return parseDateToString(new Date(), "yyyy-MM-dd");
	}

	/** 获取传入时间的月份加上amount月份后的年月 */
	public static Date addMonths(Date date, int amount) {
		return DateUtils.addMonths(date, amount);
	}

	/** 获取传入时间的月份加上day月份后的年月 */
	public static Date addDays(Date date, int amount) {
		return DateUtils.addDays(date, amount);
	}

	/** 将传入的时间格式化为yyyy/MM/dd */
	public static String parseDateToString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}

	/** 将传入的时间,按照指定样式 格式化 */
	public static String parseDateToString(Date date, String style) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return sdf.format(date);
	}

	public static Date parseDate(String dateString) {
		DateFormat[] acceptDateFormats = { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd HH:mm"), new SimpleDateFormat("yyyy-MM-dd"),
				new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"), new SimpleDateFormat("yyyy/MM/dd") };

		for (DateFormat dateFormat : acceptDateFormats) {
			try {
				return dateFormat.parse(dateString);
			} catch (ParseException e) {}
		}

		return null;
	}

	/** 计算两个日期的时间差  st增加 **/
	// "1"是开始日期    "2"是结束日期( 2 - 1)
	public static int diffDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		//不同年
		if(year1 != year2) {
			int timeDistance = 0 ;

			if(year2 > year1) {
				for(int i = year1 ; i < year2 ; i ++) {
					//闰年
					if(i%4==0 && i%100!=0 || i%400==0) {
						timeDistance += 366;
						//不是闰年
					} else {
						timeDistance += 365;
					}
				}
			}else {
				for(int i = year2 ; i < year1 ; i ++) {
					//闰年
					if(i%4==0 && i%100!=0 || i%400==0) {
						timeDistance += -366;
						//不是闰年
					} else {
						timeDistance += -365;
					}
				}
			}

			return timeDistance + (day2-day1) ;

		} else {
			//同一年
			return day2-day1;
		}
	}

}
