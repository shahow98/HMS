package com.general.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;

//���ݿ�������ʼ��
public class DButil {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/studentscore?useUnicode=true&characterEncoding=UTF-8";
	private String username = "root";
	private String password = "root";
	private Connection conn = null;
		
	private static DButil instance = null;
	
	public static synchronized DButil getInstance() {
		if (instance == null) {
			instance = new DButil();
		}
		return instance;
	}
	
	public Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("���ݿ����ӳɹ�");
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ����������Ҳ���!");
			return null;
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ��!");
			return null;
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement pstm, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
			}
			System.out.println("���ݿ�رճɹ�");
		} catch (SQLException e) {
			System.out.println("���ݿ�ر�ʧ��!");
		}
	}
	//test
	public static void main(String[] args){
		DButil db = DButil.getInstance(); 
		Connection connection = db.getConnection();
		db.close(connection, null, null);
		
	}
}
