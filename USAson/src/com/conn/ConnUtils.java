package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ConnData;

/**
 * 
 * @author LQZ
 *
 */
public class ConnUtils {
	/**
	 * 声明数据库连接对象引用
	 */
	private static Connection con = null;
	public static ConnData connData = null;
	/**
	 * 获取数据库链接
	 * 
	 * @return
	 */
	public static Connection getMysqlCON() {
		try {
			if(connData==null){
				connData = new ConnData(null,null,null,null,null,null,null);
			}
			// 加载驱动类
			Class.forName(connData.getDriver());
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动失败:\n" + e.getMessage());
		}
		try {
			// 连接
			con = DriverManager.getConnection(connData.getUrl(), connData.getName(), connData.getPwd());
		} catch (SQLException e) {
			System.out.println("链接失败:\n" + e);
		}
		return con;
	}

	/**
	 * 数据库查询类，查询结果集以列表返回
	 * @param sql
	 * @return
	 */
	public static List<Map<String, Object>> doquery(String sql) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(16);
		PreparedStatement preStat = null;
		try {
			con = getMysqlCON();
			preStat = con.prepareStatement(sql);
			ResultSet rs = preStat.executeQuery();
			ResultSetMetaData rsMeta = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>(16);
				for(int i =1 ;i<rsMeta.getColumnCount();i++){
					map.put(rsMeta.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preStat.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	/**查询*/
	private static String Sel = "SELECT";
	/**
	 * 数据库操作类
	 * @param sql </br> 执行sql语句后判断是否执行成功
	 * @return 为true 成功否则失败
	 * @throws SQLException
	 */
	public static boolean isSuccess(String sql){
		boolean flag = false;
		Statement stmt = null;
		try {
			con = getMysqlCON();
			stmt = con.createStatement();
			if(sql.toLowerCase().indexOf(Sel.toLowerCase())==0){
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					flag = true;
				}
			}else{
				stmt.executeUpdate(sql);
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	public static void main(String[] args) {
		//ConnUtils.isSuccess("insert into userinfo(name, sex, age, adress, password) value ('liqize','na','22','yun','123')");
		System.out.println(ConnUtils.isSuccess("select * from userinfo"));
		List<Map<String, Object>> doquery = doquery("select * from userinfo");
		System.out.println(doquery);
		for(Map<String, Object> map: doquery){
			System.out.println(map.get("sex"));
		}
		
	}
}
