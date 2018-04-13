package com.oracle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Getcon {
	private static String driver="oracle.jdbc.driver.OracleDriver";//声明驱动类字符串
	private static String url="jdbc:oracle:thin:@localhost:1521:orcl";//声明数据库连接字符串
	private static Connection con=null;//声明数据库连接对象引用
	private static String id="scott";
	private static String pwd="tiger";
	public static Connection getCON(){
		try {
			Class.forName(driver);//加载驱动类
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动失败");
		}
		try {
			con=DriverManager.getConnection(url,id,pwd);//连接
		} catch (SQLException e) {
			System.out.println("链接失败");
		}
		return con;
	}
	public static boolean Login(String id,String pass) throws SQLException{//登录
		Connection con=getCON();
		boolean flag=false;
		String sql="SELECT * FROM QQ id=? and passwoed=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setString(1, id);
		st.setString(2, pass);
		ResultSet rs=st.executeQuery();
		while(rs.next()){
			flag=true;
		}
		st.close();
		rs.close();
		con.close();
        return flag;
	}
	public static boolean Reg(String id,String password,String name,String sex,String age,String address) throws Exception{//用户注册
		Connection con=getCON();
    	PreparedStatement ps=null;
		ps=con.prepareStatement("insert into QQ values(?,?,?,?,?,?)");
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
		return false;
	}
	public static ArrayList<DBlist> getselect(String sql) throws SQLException{ //数据库查询类，查询结果集以列表返回 
		ArrayList<DBlist> list=new ArrayList<DBlist>();
	   	Connection con=getCON();
	   	Statement stmt=con.createStatement();
	   	ResultSet rs=stmt.executeQuery(sql);
	   	while(rs.next()){
	   		DBlist wname=new DBlist();
	   		list.add(wname);
	   		}
	    con.close();
	   	return list;
	}
    public static boolean sqldo(String sql) throws SQLException{//数据操作类
        boolean flag = false;
        Connection con=getCON();
    	Statement stmt=con.createStatement();
    	stmt.executeUpdate(sql);
    	stmt.close();
        con.close();
        return flag;
    }
}
