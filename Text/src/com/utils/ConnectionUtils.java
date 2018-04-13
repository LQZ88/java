package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getConn(String calssName,String Url,String Name, String Password)throws SQLException{//连接
    	Connection con=null;
    	try {
			Class.forName(calssName);
			System.out.println("驱动成功");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动失败");
			e.printStackTrace();
		}
		con=DriverManager.getConnection(Url, Name, Password);
		System.out.println("《数据已连接》");
		return con;
    }
}
