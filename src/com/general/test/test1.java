package com.general.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.general.dao.ILogDAO;
import com.general.dao.imp.LogDAO;
import com.general.model.Log;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ILogDAO logDAO = new LogDAO();
		Log log = new Log();
		Date now = new Date(123454);
		log.setDate(now);
		log.setOperation("散散心");
		log.setAccount("12324");
//		logDAO.insert(log);
		String hql = "from Log where account=:account";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", log.getAccount());
		List<Log> list = logDAO.find(hql, params);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getAccount()+" "+list.get(i).getLog_id());
		}
		System.out.println("-----------------------");
		hql = "from Log";
		List<Log> list1 = logDAO.find(hql);
		for(int i=0; i<list1.size(); i++) {
			System.out.println(list1.get(i).getAccount()+" "+list1.get(i).getLog_id());
		}
		System.out.println("-----------------------");
		List<Log> list2 = logDAO.findAll();
		for(int i=0; i<list1.size(); i++) {
			System.out.println(list2.get(i).getAccount()+" "+list2.get(i).getLog_id());
		}
	}

}
