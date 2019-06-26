package com.general.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static DateUtil instance = null;

	public static synchronized DateUtil getInstance() {
		if (instance == null) {
			instance = new DateUtil();
		}
		return instance;
	}
	//��Ч����
	public boolean isValidDate(String sdate, String sd, String sp) {
		SimpleDateFormat sdf = new SimpleDateFormat(sd);  //��ʽ������
		try {
			Date date = sdf.parse(sdate);  //�ַ���ת��Ϊ����
			int year, month, day, y, m, d;
			String s[] = sdate.split(sp);  //�������
			year = Integer.parseInt(s[0]);  //��
			month = Integer.parseInt(s[1]);  //��
			day = Integer.parseInt(s[2]);  //��
			Calendar cal = Calendar.getInstance();  //ʹ��ָ��ʱ�������Ի������һ������
			cal.setTime(date);
			y = cal.get(Calendar.YEAR);
			m = cal.get(Calendar.MONTH) + 1;
			d = cal.get(Calendar.DAY_OF_MONTH);
			if (year != y || month != m || day != d) {  //�ж��Ƿ�����Ч����
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public Date getDate(String sdate, String sd) {
		SimpleDateFormat sdf = new SimpleDateFormat(sd);
		Date date;
		try {
			date = sdf.parse(sdate);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	public String getStringDate(Date date, String sd) {
		SimpleDateFormat sdf = new SimpleDateFormat(sd);
		return sdf.format(date);
	}

	public java.sql.Date getSqlDate(Date date, String sd) {
		return java.sql.Date.valueOf(getStringDate(date, sd));
	}
}
