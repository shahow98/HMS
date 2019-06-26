package com.general.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.general.model.Log;

public class exam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 获取配置文件信息
		Configuration configuration = new Configuration().configure();
		// 创建SessionFactory，生成Session
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		// 生成session
		Session session = sessionFactory.openSession();
		// 开始事务
		Transaction transaction = session.beginTransaction();
		// 创建进行持久化对象
		Log log = new Log();
		Date now = new Date(123454);
		log.setDate(now);
		log.setOperation("散散心");
		log.setAccount("12324");
		// 保存
		session.save(log);
		// 提交事务
		transaction.commit();
		System.out.println("successful");
		// 关闭session
		session.close();
		// 关闭sessionFactory
		sessionFactory.close();
		
	}

}
