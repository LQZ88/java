package com;

/**
 * 
 * @author LQZ
 *
 */
public class ConnData {
	/**
	 * 声明驱动类字符串 </br>
	 * com.mysql.jdbc.Driver</br>
	 * org.gjt.mm.mysql.Driver</br>
	 */
	private String driver;
	/**
	 * 声明地址</br>
	 * localhost
	 */
	private String address;
	/**
	 * 声明端口</br>
	 * 3306
	 */
	private String port;
	/**
	 * 声明表名</br>
	 * test
	 */
	private String table;
	/**
	 * 声明数据库连接字符串
	 */
	private String url;
	/**
	 * 声明账号</br>
	 * root
	 */
	private String name;
	/**
	 * 声明密码</br>
	 * root
	 */
	private String pwd;
	/**
	 * @param driver </br>
	 * 驱动默认:org.gjt.mm.mysql.Driver
	 * @param address </br>
	 * 地址默认:localhost</br>
	 * @param port </br>
	 * 端口默认:3306</br>
	 * @param table </br>
	 * 库名默认:test</br>
	 * @param url </br>
	 * 链接地址默认:jdbc:mysql://this.address:this.port/this.table</br>
	 * @param name </br>
	 * 账户默认:root</br>
	 * @param pwd </br>
	 * 密码默认:root</br>
	 */
	public ConnData(String driver, String address, String port, String table, String url, String name, String pwd) {
		super();
		if(measureNotNull(driver)){
			this.driver = driver;
		}else{
			this.driver = "org.gjt.mm.mysql.Driver";
		}
		if(measureNotNull(address)){
			this.address = address;
		}else{
			this.address = "localhost";
		}
		if(measureNotNull(port)){
			this.port = port;
		}else{
			this.port = "3306";
		}
		if(measureNotNull(table)){
			this.table = table;
		}else{
			this.table = "test";
		}
		if(measureNotNull(url)){
			this.url = url;
		}else{
			this.url = "jdbc:mysql://" + this.address + ":" + this.port + "/" + this.table
					+ "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
		}
		if(measureNotNull(name)){
			this.name = name;
		}else{
			this.name = "root";
		}
		if(measureNotNull(pwd)){
			this.pwd = pwd;
		}else{
			this.pwd = "root";
		}
	}
	
	@Override
	public String toString() {
		return "ConnData [driver=" + driver + ", address=" + address + ", port=" + port + ", table=" + table + ", url="
				+ url + ", name=" + name + ", pwd=" + pwd + "]";
	}

	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
     * 判断内容是否不为空
     * @param o 内容
     * @return
     */
    public static boolean measureNotNull(Object o) {
        return o != null && !o.equals("") && !o.equals("undefined");
    }
}
