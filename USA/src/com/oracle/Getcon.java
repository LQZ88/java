package com.oracle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Getcon {
	private static String driver="oracle.jdbc.driver.OracleDriver";//�����������ַ���
	private static String url="jdbc:oracle:thin:@localhost:1521:orcl";//�������ݿ������ַ���
	private static Connection con=null;//�������ݿ����Ӷ�������
	private static String id="scott";
	private static String pwd="tiger";
	public static Connection getCON(){
		try {
			Class.forName(driver);//����������
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ��");
		}
		try {
			con=DriverManager.getConnection(url,id,pwd);//����
		} catch (SQLException e) {
			System.out.println("����ʧ��");
		}
		return con;
	}
	public static boolean Login(String id,String pass) throws SQLException{//��¼
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
	public static boolean Reg(String id,String password,String name,String sex,String age,String address) throws Exception{//�û�ע��
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
		System.out.println("����");
		ps.close();
		con.close();
		return false;
	}
	public static ArrayList<DBlist> getselect(String sql) throws SQLException{ //���ݿ��ѯ�࣬��ѯ��������б��� 
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
    public static boolean sqldo(String sql) throws SQLException{//���ݲ�����
        boolean flag = false;
        Connection con=getCON();
    	Statement stmt=con.createStatement();
    	stmt.executeUpdate(sql);
    	stmt.close();
        con.close();
        return flag;
    }
}
