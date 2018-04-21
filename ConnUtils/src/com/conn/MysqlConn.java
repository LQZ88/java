package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.DbList;

/**
 * 
 * @author LQZ
 *
 */
public class MysqlConn {
	/**
	 * 声明驱动类字符串 </br>
	 * com.mysql.jdbc.Driver</br>
	 * org.gjt.mm.mysql.Driver</br>
	 */
	public static String driver = "org.gjt.mm.mysql.Driver";
	/**
	 * 声明地址</br>
	 * localhost
	 */
	public static String address = "localhost";
	/**
	 * 声明端口</br>
	 * 3306
	 */
	public static String port = "3306";
	/**
	 * 声明表名</br>
	 * testmvc
	 */
	public static String table = "testmvc";
	/**
	 * 声明数据库连接字符串
	 */
	public static String url = "jdbc:mysql://" + address + ":" + port + "/" + table
			+ "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
	/**
	 * 声明数据库连接对象引用
	 */
	public static Connection con = null;
	/**
	 * 声明账号</br>
	 * root
	 */
	public static String name = "root";
	/**
	 * 声明密码</br>
	 * root
	 */
	public static String pwd = "root";

	/**
	 * 获取数据库链接
	 * 
	 * @return
	 */
	public static Connection getMysqlCON() {
		try {
			// 加载驱动类
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动失败:\n" + e.getMessage());
		}
		try {
			// 连接
			con = DriverManager.getConnection(url, name, pwd);
		} catch (SQLException e) {
			System.out.println("链接失败:\n" + e);
		}
		return con;
	}

	/**
	 * 登录
	 * 
	 * @param id
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	public static boolean toLogin(String id, String pass) {
		boolean flag = false;
		try {
			Connection con = getMysqlCON();
			String sql = "SELECT * FROM QQ id=? and passwoed=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				flag = true;
			}
			st.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 用户注册
	 * 
	 * @param id
	 * @param password
	 * @param name
	 * @param sex
	 * @param age
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public static boolean toReg(String id, String password, String name, String sex, String age, String address) {
		try {
			Connection con = getMysqlCON();
			PreparedStatement ps = null;
			ps = con.prepareStatement("insert into QQ values(?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, sex);
			ps.setString(5, age);
			ps.setString(6, address);
			ps.executeUpdate();
			System.out.println("插入");
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 数据库查询类，查询结果集以列表返回
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<DbList> getselect(String sql) {
		ArrayList<DbList> list = new ArrayList<DbList>();
		try {
			Connection con = getMysqlCON();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DbList wname = new DbList();
				list.add(wname);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 数据操作类
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static boolean sqldo(String sql) throws SQLException {
		boolean flag = false;
		try {
			Connection con = getMysqlCON();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
