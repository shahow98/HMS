package com.general.test;

import java.util.Date;
import java.util.List;

import com.general.model.Log;
import com.general.service.ILogService;
import com.general.service.imp.LogService;

public class servicetest {

	public static void main(String[] args) {
		ILogService logService  = new LogService();
		
		Log log = new Log();
		
		Date now = new Date(123454);
		log.setDate(now);
		log.setOperation("");
		log.setAccount("");
//		logDAO.insert(log);
		logService.insert(log);
		/*String hql = "from Log";
		List<Log> lists = logService.find(hql);
		for(int i=0; i<lists.size(); i++) {
			System.out.println(lists.get(i).getAccount()+" "+lists.get(i).getLog_id());
		}*/
		
	}
	
}
