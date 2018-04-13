package com.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.utils.ConnectionUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Text {
	public static void main(String[] args) {
		//toSyatem(12);
		//toStringNum(100);
		//torundNum();
		/*if(0<=0){
			System.out.println("1");
		}else{
			System.out.println("2");
		}
		*/
		/*
		Command cmd = new Command();
		cmd.setCmd("cmd");
		cmd.writeCmd("D:\\Tomcat\\tomcat\\bin\\startup.bat");
		cmd.readCmd();
		*/
		// LinkedList<String> list = new LinkedList<String>();
		// list.add("dir/b");
		// list = cmd.doCmd(list);
		// for(String s:list){
		// System.out.print(s);
		// }
		/*String val="[{\"type\":\"城际高速1\",\"station\":\"天津1\",\"stationNO\":\"1\",\"days\":\"1\",\"arriveTime\":\"-09:15\"},"
				+ "{\"type\":\"城际高速2\",\"station\":\"天津2\",\"stationNO\":\"2\",\"days\":\"2\",\"arriveTime\":\"09:15\"},"
				+ "{\"type\":\"城际高速3\",\"station\":\"天津3\",\"stationNO\":\"3\",\"days\":\"3\",\"arriveTime\":\"09:15\"},"
				+ "{\"type\":\"城际高速4\",\"station\":\"天津4\",\"stationNO\":\"4\",\"days\":\"4\",\"arriveTime\":\"09:15\"},"
				+ "{\"type\":\"城际高速5\",\"station\":\"天津5\",\"stationNO\":\"5\",\"days\":\"5\",\"arriveTime\":\"09:15\"},"
				+ "{\"type\":\"城际高速6\",\"station\":\"天津6\",\"stationNO\":\"6\",\"days\":\"6\",\"arriveTime\":\"09:15\"}]";
		List<String[]> listData = getListData(val);
		wordTableAddRow(listData,new File("D:\\下载\\word.docx"));
		*/
		//getwebService();
		//totxtFile("2017-10-26","2017-11-01");
		/*try {
			List<Object> list = ExceluploadUtils.exportListFromExcel("D:\\下载\\模版.xls");
			for(Object bean:list){
				System.out.println(bean);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*
		String a = "null ";
		System.out.println(a.contains("null"));
		
		BigDecimal after = new BigDecimal("145.607");
		BigDecimal beforeData = new BigDecimal("161.1156");
		BigDecimal oneMinuteChange = after.subtract(beforeData).abs();
		System.out.println(oneMinuteChange);
		*/
		/*
		try {
			Connection con7 = ConnectionUtils.getConn("dm7.jdbc.driver.DmDriver", "jdbc:dm://10.51.41.109:5236", "SYSDBA", "SYSDBA");
			Connection con6 = ConnectionUtils.getConn("dm6.jdbc.driver.DmDriver", "jdbc:dm://10.51.41.109:12345", "SYSDBA", "SYSDBA");
			Statement stmt=con7.createStatement();
	    	ResultSet rssql=stmt.executeQuery("SELECT * FROM XNY.OBJECT_INFO");
	    	while(rssql.next()){
		    	String i=rssql.getString(1);
		    	String j=rssql.getString(2);
		    	String k=rssql.getString(3);
		    	String l=rssql.getString(4);
		    	String m=rssql.getString(5);
		    	String n=rssql.getString(6);
		    	System.out.println(i+"\t"+j+"\t"+k+"\t"+l+"\t"+m+"\t"+n);
	    	}
	    	rssql.close();
			stmt.close();
			con7.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		//setFilesTwodoOtherSave("E:\\text\\201701\\","E:\\text\\201702\\","E:\\text\\201703\\");
		
		
	}
	/**
	 * 将不同的两个文件加下相同文件的内容写到同一个文件中去
	 * @param filepath1
	 * @param filepath2
	 * @param filepath3
	 */
	private static void setFilesTwodoOtherSave(String filepath1,String filepath2,String filepath3){
		File f1 = new File(filepath1);
		File f2 = new File(filepath2);
		File f3 = new File(filepath3);
		Map<String, Object> map = new HashMap<>();
		for(File fi1: f1.listFiles()){
			map.put(fi1.getName(), fi1.getName());
		}
		for(File fi2: f2.listFiles()){
			String filename = (String) map.get(fi2.getName());
			String read1 = readTxtFile(f1.getPath()+ File.separator +filename);
			String read2 = readTxtFile(f2.getPath()+ File.separator +filename);
			String readStr = read1+read2;
			try {
				System.out.println("文件内容是:"+ "\r\n" + readStr);
				writeTxtFile(readStr, f3.getPath()+ File.separator +filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    public static BufferedReader bufread;
    /**
     * 读取文本文件.
     * @param path 文件位置
     * @return
     */
    public static String readTxtFile(String path){
        String read;
        FileReader fileread;
        String readStr ="";
        try {
        	File filename = new File(path);
            fileread = new FileReader(filename);
            bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {
                    readStr = readStr + read+ "\r\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return readStr;
    }
    /**
     * 写文件.
     * @param Str 内容
     * @param path 保存位置
     * @throws IOException
     */
    public static void writeTxtFile(String Str,String path) throws IOException{
        //先读取原有文件内容，然后进行写入操作
        RandomAccessFile mm = null;
        try {
        	File filename = new File(path);
            mm = new RandomAccessFile(filename, "rw");
            mm.writeBytes(Str);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
	private static void totxtFile(String startTime,String endTime){
		SimpleDateFormat smdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			String filepath = "E:\\home\\d5000\\sichuan\\xny\\rnjy";
			long timeLongs=smdf.parse(startTime).getTime();//开始时间毫秒数
			long timeLonge=smdf.parse(endTime).getTime();//结束时间毫秒数
			Calendar cal=Calendar.getInstance();
			cal.setTime(smdf.parse(startTime));
			if(timeLongs<=timeLonge){
				while(true){
					String stTime = smdf.format(cal.getTime());
					String filePaths = "PWJY_"+stTime.replaceAll("-", "")+"_";
					String filePathe = ".txt";
					File file=new File(filepath);
					File[] files=file.listFiles();
					for(int i = 0; i < files.length; i++){ 
						if(files[i].getName().startsWith(filePaths)&&files[i].getName().endsWith(filePathe)){
							String dofile = file.getPath()+File.separator+files[i].getName();
							InputStreamReader read = new InputStreamReader(new FileInputStream(dofile),"gbk");       
				            BufferedReader bf3=new BufferedReader(read); 
							String sthLine="";
							while((sthLine=bf3.readLine())!=null){
								if(StringUtils.isNotEmpty(sthLine)){
									String[] sthValues=sthLine.split(" ");
									String objname = sthValues[1].toString();
									if(StringUtils.isNotEmpty(objname)){
										String time = stTime+" "+sthValues[2].toString()+":00";
										String maxpoer = sthValues[3].toString();
										String minpoer = sthValues[4].toString();
										System.out.println(objname+"  "+time+"  "+maxpoer+"  "+minpoer);
										
									}
								}
							}
						}
					 }
					cal.add(Calendar.DATE, 1);
					if(cal.getTimeInMillis()>timeLonge){
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	private static boolean getwebService(){
		String WEB_SERVICE_URL ="http://192.168.43.222:8080/sfireServcer/service/IUserService";
		String WEB_SERVICE_TNS ="http://www.chinajavaworld.com/IUserService";
		String method ="HitShelf";
		try{
	        // 创建一个服务(service)调用(call)     
	        org.apache.axis.client.Service service = new org.apache.axis.client.Service();    
	        org.apache.axis.client.Call call = (Call) service.createCall();// 通过service创建call对象     
	        // 设置service所在URL    
	        call.setTargetEndpointAddress(new java.net.URL(WEB_SERVICE_URL));   
	        call.setOperationName(new QName(WEB_SERVICE_TNS,method)); 
	        //Add 是net 那边的方法 "http://tempuri.org/" 这个也要注意Namespace 的地址,不带也会报错
	        /*
	        call.addParameter(new javax.xml.namespace.QName(WEB_SERVICE_TNS,param[0]), 
	        org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
	        call.addParameter(new javax.xml.namespace.QName(WEB_SERVICE_TNS,param[1]), 
	    	org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
	        */
	        call.setUseSOAPAction(true); 
	        call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING); //返回参数的类型
	        call.setSOAPActionURI(WEB_SERVICE_TNS+method); //这个也要注意 就是要加上要调用的方法Add,不然也会报错
	        // Object 数组封装了参数，参数为"This is Test!",调用processService(String arg)     
	        String ret = (String) call.invoke(new Object[] {}); 
	        ret = ret.replaceAll(":}", ":\"无\"}").replaceAll(":,", ":\"无\",");
		}catch(Exception e){
			System.out.println("关联专利WebService接口数据异常："+e.getMessage());
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 将json集合转换为List<String[]>
	 * @param values 界面获取的数据
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<String[]> getListData(String values){
		List<String[]> list =new ArrayList<String[]>();
		if(values!=null){
		    JSONArray array=JSONArray.fromObject(values);//将其转换为JSONArray数组格式
		    Iterator iter =  array.iterator();
		    Map<String, Object> map = JSONObject.fromObject(array.get(0));//转换为map获取所有键
		    String[][] ary = new String[array.size()+1][map.size()];
		    int j=0;String[] key = new String[map.size()];
		    for (Entry<String, Object> entry : map.entrySet()) {
		    	key[j] = entry.getKey();
		    	j++;
	        }
		    ary[0] = key;//将键赋予数组
		    int i=1; j=0;
		    while(iter.hasNext()){
		        JSONObject jObj = (JSONObject)iter.next();
		        ary[i] = new String[map.size()];
		        for(int k=0;k<map.size();k++)//根据map的键数量获取值放入ary数组中最后放入list集合中
		        ary[i][j] = (String)jObj.get(key[j++]);
		        list.add(ary[i]);i++;j=0;
		    }
		}
	    return list;
	}
	public static File wordTableAddRow(List<String[]> rowValues, File file) {
		OutputStream out = null;
		InputStream in = null;
		XWPFDocument document = null;
		try {
			File newFile = new File(file.getParent()+"\\"+UUID.randomUUID().toString()+"-"+file.getName());
			out = new FileOutputStream(newFile);
			in = new FileInputStream(file);
			document = new XWPFDocument(in);
			Iterator<XWPFTable> tables = document.getTablesIterator();
			while(tables.hasNext()){
				XWPFTable table = tables.next();
				int rowIndex = table.getNumberOfRows();
				for(int i = 0; i < rowValues.size(); i++){
					String[] values = rowValues.get(i);
					table.addRow(table.getRow(rowIndex-1));
					table.addNewRowBetween(rowIndex, ++rowIndex);
					XWPFTableRow row = table.getRow(rowIndex);
					List<XWPFTableCell> cells = row.getTableCells();
					for(int j = 0; j < values.length && j < cells.size(); j++){
						XWPFTableCell cell = cells.get(j);
						cell.setText(values[j]);
					}
					rowIndex++;
				}
			}
			document.write(out);
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(document != null){
					//document.close();
				}
				if(in != null){
					in.close();
				}
				if(out != null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return file;
	}

	private static void torundNum(){
		List<String> list = new ArrayList<>();
		list.add("46087088972f4bb6beae06e71e2d86a4");
		list.add("df86cfdee38a4903a47c9895e3a571f4");
		list.add("cc47e2f57e0a4b79b4ae187b451b8f3f");
		list.add("56b4f996a93348069018ca39eba89f3e");
		String[] b = new String[list.size()];
		for(int i=0,j=list.size();i<j;i++){
			b[i] = list.get(i).toString();
		}
		Random rand = new Random();
		int num = rand.nextInt(1);
		System.out.println(b[num]);
		createRandomList(list ,2);
	}
	/**从数组中随机抽取元素 
     * @return   
     * @Title: createRandomArray  
     * @Description: TODO 
     * @param arr 
     * @param i  
     * @return void   
     * @throws  
     */   
    private static int[] createRandomArray(int[] arr, int n) {  
        // TODO Auto-generated method stub  
        Map map = new HashMap();  
        int[] arrNew = new int[n];  
        if(arr.length<=n){  
            return arr;  
        }else{  
            int count = 0;//新数组下标计数  
            while(map.size()<n){  
                int random = (int) (Math.random() * arr.length);  
                if (!map.containsKey(random)) {  
                    map.put(random, "");  
                    System.out.println(random+"==========="+arr[random]);  
                    arrNew[count++] = arr[random];  
                }  
            }  
            return arrNew;  
        }  
    }  
  
    /**从list中随机抽取元素 
     * @return   
     * @Title: createRandomList  
     * @param list 数据源
     * @param i  获取数量
     * @return void   
     * @throws  
     */   
    private static List<String> createRandomList(List<String> list, int n) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        List<String> listNew = new ArrayList<String>();
        if(list.size()<=n){
            return list;
        }else{
            while(map.size()<n){
                int random = (int) (Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    listNew.add(list.get(random));
                }
            }
            return listNew;  
        }
    } 
	private static void toStringNum(int i) {
		String str = "delete from CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ where 1= 1  and APPLIEDTIME between to_date('2017-06-23','yyyy-MM-dd') and  to_date('2017-07-28','yyyy-MM-dd'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26100100','2','0',NULL); insert into CHK.PLAN_UNIT"
				+ "CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26100100','0','0.005',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26100100','1','0.068',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ("
				+ " APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26100200','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26100200','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,P"
				+ "SVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26100200','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26101100','2','0','1'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values("
				+ "'2017-06-23','2213','26101100','0','0','1'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26101100','1','0','1'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26101200','"
				+ "2','0','1'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26101200','0','0','1'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26101200','1','0','1'); insert into CHK.PLA"
				+ "N_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26102100','2','1.968',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26102100','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_F"
				+ "Z ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26102100','1','1.100',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26109121','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,"
				+ "OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26109121','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26109121','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH)"
				+ "values( '2017-06-23','2213','26109122','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26109122','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','2"
				+ "6109122','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26109211','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26109211','0','0',NULL); insert"
				+ " into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26109211','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26111231','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENER"
				+ "GY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26111231','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26111231','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METER"
				+ "CODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26111232','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26111232','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBL"
				+ "ISH) values( '2017-06-23','2213','26111232','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26112211','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','221"
				+ "3','26112211','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26112211','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26112212','2','0',NULL); i"
				+ "nsert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26112212','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26112212','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK"
				+ "_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26114100','2','0','1'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26114100','0','0','1'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,ME"
				+ "TERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26114100','1','0','1'); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26120100','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPU"
				+ "BLISH) values( '2017-06-23','2213','26120100','0','0.034',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26120100','1','0.014',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-0"
				+ "6-23','2213','26130100','2','1.783',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26130100','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26130100','1"
				+ "','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131131','2','0.324',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131131','0','0.078',NULL); insert in"
				+ "to CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131131','1','0.138',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131132','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENE"
				+ "RGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131132','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131132','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METE"
				+ "RCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131231','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131231','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUB"
				+ "LISH) values( '2017-06-23','2213','26131231','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131232','2','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','22"
				+ "13','26131232','0','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26131232','1','0',NULL); insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26140100','2','0',NULL);"
				+ "insert into CHK.PLAN_UNIT_CHECK_ENERGY_DRPT_FZ ( APPLIEDTIME,METERCODE,OBJID,PSVCODE,DATA,ISPUBLISH) values( '2017-06-23','2213','26140100','0','3.343',NULL); insert into"
				+ ""
				+ "";
		System.out.println(str);
		System.out.println(str.length());
		System.out.println(str.substring(i));
	}
	/**
	 * 重载
	 * @param num
	 */
	@SuppressWarnings("resource")
	private static void toSyatem(int num) {
		/*for(int i=0;i<50;i++){
			System.out.println(i);
			if(i>40){
				try{
					String v = String.valueOf("d"+i);
					int x = Integer.parseInt(v);
					System.out.println(x);
				}catch (Exception e) {
					toSyatem(num);
					e.printStackTrace();
				}
			}
		}*/
		try {
			Scanner myScanner = new Scanner(System.in);
			System.out.println("请输入整数:");
			int myInt = myScanner.nextInt();
			System.out.println(myInt);
		} catch (Exception e) {
			System.out.println("输入错误，请输入正确的数值!!!"+num);
			toSyatem(num);
		}
		
	}
}
