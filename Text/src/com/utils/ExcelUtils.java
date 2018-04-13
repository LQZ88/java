package com.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;



public class ExcelUtils {
	/**
	 * 
	 * @param list 字段对应数据的map集合
	 * @param fieldArr 字段集合标识
	 * @param title 第一栏标题名称中文
	 * @param filepath 导出路径
	 * @param fileName 导出名称
	 */
	public static void fileExcel(List<Map<Object, Object>> list,String[] fieldArr,String title,String filepath,String fileName) throws Exception{
		if(fieldArr.length>0){
			HSSFWorkbook wb = new HSSFWorkbook();//创建HSSFWorkbook对象(excel的文档对象)
			HSSFSheet sheet=wb.createSheet(fileName);//建立新的sheet对象（excel的表单）
			HSSFRow row=sheet.createRow(0);//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
			HSSFCell cell=row.createCell(0);//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
			HSSFCellStyle cellStyle = wb.createCellStyle(); cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
			HSSFFont font=wb.createFont(); font.setFontHeightInPoints((short)14); cellStyle.setFont(font);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,fieldArr.length-1));//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
			cell.setCellValue(fileName);  row.setHeightInPoints(20);	
			cell.setCellStyle(cellStyle);	
			sheet.setDefaultColumnWidth(20);	sheet.setDefaultRowHeightInPoints(16);
			HSSFCellStyle cellStyle1 = wb.createCellStyle(); cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
			HSSFFont font1=wb.createFont(); font1.setFontHeightInPoints((short)10); cellStyle1.setFont(font1);
			row=sheet.createRow(1);//在sheet里创建第二行
			if(!title.equals(null)){
				for(int i=0; i<title.split(",").length;i++){
					cell = row.createCell(i);
					cell.setCellStyle(cellStyle1);
					cell.setCellValue(title.split(",")[i]);
				}
				//在sheet里创建第三行 
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i+2);
					for (int j = 0; j < fieldArr.length; j++) {
						cell = row.createCell(j);
						// 得到第一行的样式
						cell.setCellStyle(cellStyle1);
						String value = BeanUtils.getProperty(list.get(i),fieldArr[j]);
						cell.setCellValue(value);
					}
				}
				//输出Excel文件
				File File = new File(filepath);
				File newFile = new File(File.getParent());
				if (!newFile.exists()) {
					newFile.mkdirs();
				}
				FileOutputStream fileOut = new FileOutputStream(filepath);
				wb.write(fileOut);
				fileOut.close();
			}
		}
	}
	public static void fileDownLoad(HttpServletResponse response,String filePath,String downName) throws Exception{
    	File file = new File(filePath); 
		if(!file.exists()) throw new Exception("文件不存在!"); 
		FileInputStream fileInputStream = new FileInputStream(file); 
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream); 
		OutputStream outputStream = response.getOutputStream(); 
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream); 
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(downName.replace(" ", ""), "UTF-8")); 
		
		int bytesRead = 0; 
		byte[] buffer = new byte[1024]; 
		while ((bytesRead = bufferedInputStream.read(buffer)) != -1) { 
			bufferedOutputStream.write(buffer, 0, bytesRead); 
		} 
		bufferedOutputStream.flush(); 
		fileInputStream.close(); 
		bufferedInputStream.close(); 
		outputStream.close(); 
		bufferedOutputStream.close();
    }
}
