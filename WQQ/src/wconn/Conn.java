package wconn;
import java.sql.*;
import java.util.*;
public class Conn {
	private String Wname;
	public String getsname() {
		return Wname;
		}
		public void setname(String name) {
		this.Wname = name;
		}
	public static void main(String[] args) throws SQLException {
		/*try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("�����ɹ�");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		Connection con=DriverManager.getConnection(url,"scott","tiger");
		System.out.println("�����������ӡ�");//*/
		Connection con=getConn();//*/
		Statement stmt=con.createStatement();
    	/*ResultSet rs=stmt.executeQuery("create table WQQ(Wid int,Wpassword varchar2(20),Wname varchar2(20),Wage int,Wadress varchar2(50),wip varchar2(50))");
    	System.out.println("whyl���Ѵ���");
    	ResultSet rss=stmt.executeQuery("insert into WQQ values(103,103,'���鷨','20','����','192.168.1.10')");
    	System.out.println("�����Ѳ���");
		//ResultSet rssql=stmt.executeQuery("delete  FROM WQQ");//*/
    	ResultSet rssql=stmt.executeQuery("SELECT * FROM WQQ");
    	while(rssql.next()){
	    	String i=rssql.getString(1);
	    	String j=rssql.getString(2);
	    	String k=rssql.getString(3);
	    	String l=rssql.getString(4);
	    	String m=rssql.getString(5);
	    	String n=rssql.getString(6);
	    	System.out.println(i+"\t"+j+"\t"+k+"\t"+l+"\t"+m+"\t"+n);
    	}
    	/*rs.close();
    	rss.close();//*/
    	rssql.close();
		stmt.close();
		con.close();
	}
	public static boolean Login(String id,String password) throws SQLException{//��¼
		Connection con=getConn();
		boolean flag=false;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM WQQ");
		rs=st.getResultSet();
		while(rs.next()){
			String ids=rs.getString(1);  
			String pass=rs.getString(2);
			if(ids.equals(id)&&pass.equals(password)){
					flag=true;
			}
		}
		st.close();
		rs.close();
		con.close();
        return flag;
	}
	public static boolean Rrg(String id,String password,String name,String age,String address,String nowip) throws Exception{//�û�ע��
		Connection con=getConn();
    	PreparedStatement ps=null;
		ps=con.prepareStatement("insert into WQQ values(?,?,?,?,?,?)");
		ps.setString(1, id);
		ps.setString(2, password);
		ps.setString(3, name);
		ps.setString(4, age);
		ps.setString(5, address);
		ps.setString(6, nowip);
		ps.executeUpdate();
		System.out.println("����");
		ps.close();
		con.close();
		return false;
	}
    public static ArrayList<Conn> getList1(String sql) throws SQLException{ //���ݿ��ѯ�࣬��ѯ��������б��� 
    	ArrayList<Conn> list=new ArrayList<Conn>();
    	Connection con=getConn();
    	Statement stmt=con.createStatement();
    	ResultSet rs=stmt.executeQuery(sql);
    	while(rs.next()){
    		Conn wname=new Conn();
    		wname.setname(rs.getString("Wname"));
    		list.add(wname);
    	}
    	con.close();
    	return list;
    }
	public static boolean insert(String sql) throws SQLException{//���������
    	boolean flag = false;
    	Connection con=getConn();
    	Statement stmt=con.createStatement();
    	stmt.executeUpdate(sql);
    	stmt.close();
        con.close();
        return flag;
    }
    public static boolean update(String id,String ip) throws SQLException{//�޸�������
    	Connection con=getConn();
		boolean flag=false;
    	PreparedStatement ps=null;
		ps=con.prepareStatement("update WQQ set wip=? where Wid=?");
		ps.setString(1, ip);
		ps.setString(2, id);
		ps.executeUpdate();
		ps.close();
		con.close();
        return flag;
    }
    public static boolean delete(String sql) throws SQLException{//ɾ��������
        boolean flag = false;
        Connection con=getConn();
    	Statement stmt=con.createStatement();
    	stmt.executeUpdate(sql);
    	stmt.close();
        con.close();
        return flag;
    }
    public static Connection getConn()throws SQLException{//����
    	Connection con=null;
    	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("�����ɹ�");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		con=DriverManager.getConnection(url,"jack","java");
		System.out.println("�����������ӡ�");//*/
		/*try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url="jdbc:oracle:thin:@localhost:orcl";
		con=DriverManager.getConnection(url,"jack","java");
		System.out.println("�����������ӡ�");//*/
		return con;
    }
}
