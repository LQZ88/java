package com.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class WordUtils {
	/**
	 * 生成word附加
	 * @param saveName 文件夹
	 * @param fielName
	 * @param savepath
	 * @param UserId
	 * @param textMap
	 * @param imagePathMap
	 * @param fileName
	 * @param businessKey
	 * @param big_class_id
	 * @param wordModel
	 * @return
	 * @throws Exception
	 */
	public boolean dealWord(String saveName,String fielName, String savepath,String UserId,
			Map<String, String> textMap, Map<String, ?> imagePathMap,
			String fileName, String businessKey, String big_class_id,
			Map wordModel) throws Exception {
		boolean rs = true;
		String templatePath = this.getClass().getResource("/").getPath();
		templatePath = templatePath + File.separator + "config" + File.separator + "template" + File.separator + fielName;
		rs = reportByTemplate(templatePath, savepath, textMap, imagePathMap, wordModel);
		String swfPath = "";
		if (rs) {
			// 将路径截取存入数据库
			savepath = savepath.substring(savepath.lastIndexOf(File.separator));
			savepath = saveName + savepath;
			/*UploadFile uploadFile = new UploadFile();
			uploadFile.setBusinessKey(businessKey);
			uploadFile.setBig_class_id(big_class_id);
			uploadFile.setBusinessType(saveName);
			File saveFile = new File(FileUtils.filePath() + File.separator + savepath);
			uploadFile.setFileSize(FileUtils.formatSize(saveFile.length()));
			uploadFile.setSourcePath(savepath);
			uploadFile.setSwfPath(swfPath);
			uploadFile.setFileName(fileName);
			uploadFile.setCreate_user(UserId);
			uploadFile.setTaskId(wordModel.getTaskId());
			uploadFile.setCreateTime(DateUtils.getDate());
			uploadFileServices.insertFile(uploadFile);*/
		}
		return rs;
	}
	/**
	 * 根据模板生成报告,模板支持${标记} “:”为特殊标记符，应避免使用
	 * 
	 * @param templateFilePath 模板文件路径
	 * @param outFilePath 输出文件路径
	 * @param textMap 文本数据key=value for word:${key}
	 * @param imagePathMap 图片标记及对应属性 key=imageMark key=imageMark:width
	 *            key=imageMark:height 图片数据 value 图片文件路径、File对象、文件流 、key like
	 *            ":height" value:num、":width:300"
	 * @param wordModel
	 * @return boolean 生成报告是否成功 each engineer has a duty to keep the code
	 *         elegant
	 * @throws Exception
	 */
	public static boolean reportByTemplate(String templateFilePath,
			String outFilePath, Map<String, String> textMap,
			Map<String, ?> imagePathMap, Map wordModel) throws Exception {
		List<Map<String, String>> textMapList = new ArrayList<Map<String, String>>();
		textMapList.add(textMap);
		List<Map<String, ?>> imagePathMapList = new ArrayList<Map<String, ?>>();
		imagePathMapList.add(imagePathMap);
		return reportByTemplate(templateFilePath, outFilePath, textMapList,
				imagePathMapList, wordModel);
	}
	/**
	 * 根据模板生成报告,模板支持${标记} “:”为特殊标记符，应避免使用 ${:start}模板循环开始标记，独占一行
	 * ${:end}模板循环结束标记，独占一行
	 * 
	 * @param templateFilePath 模板文件路径
	 * @param outFilePath 输出文件路径
	 * @param textMapList 多组文本数据key=value for word:${key}
	 * @param imagePathMapList 多组图片数据 图片标记及对应属性 key=imageMark key=imageMark:width
	 *            key=imageMark:height 图片数据 value 图片文件路径、File对象、文件流 、key like
	 *            ":height" value:num、":width:300"
	 * @param wordModel
	 * @return  boolean 生成报告是否成功 each engineer has a duty to keep the code
	 *         elegant
	 * @throws Exception
	 */
	public static boolean reportByTemplate(String templateFilePath,
			String outFilePath, List<Map<String, String>> textMapList,
			List<Map<String, ?>> imagePathMapList,Map wordModel)
			throws Exception {
		boolean success = false;
		long startTime = System.currentTimeMillis();
		String configPath = templateFilePath.replaceAll("%20", " ");
		FileOutputStream outStream = null;
		OPCPackage opcPackage = null;
		try {
			System.out.println("加载Word模板....");
			// 加载模板文档HWPFDocument
			opcPackage = POIXMLDocument.openPackage(configPath);
			CustomXWPFDocument document = new CustomXWPFDocument(opcPackage);
			markComb(document); // 梳理模板标记
			int listMaxSize = 0;
			if (textMapList != null) {
				listMaxSize = textMapList.size();
			}
			if (imagePathMapList != null
					&& imagePathMapList.size() > listMaxSize) {
				listMaxSize = imagePathMapList.size();
			}
			if (listMaxSize > 1) {
				System.out.println("包含多组数据,处理多组数据模板....");
				document = rebuildDocumentForListData(document, listMaxSize - 1);
			}
			System.out.println("开始填充段落标记数据....");
			setDataForMark(document, textMapList, imagePathMapList); // 填充段落标记数据
			
			// markClear(document); // 清理无用标记
			System.out.println("生成Word文件:" + outFilePath);
			outStream = new FileOutputStream(outFilePath);
			document.write(outStream);
			outStream.close();
			success = true;
		} catch (Exception e) {
			success = false;
			System.out.println("word处理" + (success ? "成功" : "失败") + "...耗时：" + (System.currentTimeMillis() - startTime / 1000f) + "s");
			throw new RuntimeException("加载模板错误：" + configPath);
		} finally {
			if (outStream != null) {
				outStream.flush();
				outStream.close();
			}
			if (opcPackage != null) {// opcPackage.close();
			}// POIXMLDocument.
		}
		return success;
	}
	/**
	 * 梳理文档${标记}，保留标记整体样式 word字符存储时有各种情况会将连续字符存到不同的XWPFRun里，对处理造成困难
	 * 本方法将连续的标记梳理到相同的run里，便于处理
	 * @param document
	 */
	private static void markComb(XWPFDocument document) {
		boolean markBeginMissing = false; // 出现一半开始符
		int markBeginRunIndex = -1; // 开始标记符出现的run位置
		StringBuffer markKeySB = null; // 叠加标记字符串
		List<XWPFParagraph> paragraphList = document.getParagraphs();
		List<XWPFRun> runList = null;
		XWPFParagraph paragraph = null;
		XWPFRun markRun = null;
		for (int i = 0; i < paragraphList.size(); i++) {
			paragraph = paragraphList.get(i);
			runList = paragraph.getRuns();
			for (int p = 0; p < runList.size(); p++) {
				markRun = runList.get(p);
				String runTempText = markRun.toString(); // 当前循环run文本
				if (!"".equals(runTempText) && runTempText != null) {
					int markEndStrIndex;
					int markBeginStrIndex;
					if (markBeginRunIndex > -1) {
						/* 找到开始标记，重新组织标记 */
						markEndStrIndex = runTempText.lastIndexOf("}");// 结束标记位置
						if (markEndStrIndex > -1) {// 标记结束
							if (runTempText.length() > ++markEndStrIndex) {
								markKeySB.append(runTempText.substring(0, markEndStrIndex));
								markRun.setText(runTempText.substring(markEndStrIndex), 0); // 截去当前位置标记文本
							} else {
								markKeySB.append(runTempText);
								paragraph.removeRun(p--);// 去掉当前位置标记
							}
							// 重写标记
							markRun = runList.get(markBeginRunIndex);
							markRun.setText(markKeySB.toString().trim(), 0); // setText是替换文本并保留原样式不变的最佳方案
							markBeginRunIndex = -1;
						} else {
							paragraph.removeRun(p--);
							markKeySB.append(runTempText);
						}
					} else {
						/* 定位开始标记 */
						markBeginStrIndex = runTempText.lastIndexOf("${");
						if (markBeginStrIndex > -1) {
							// 标记开始后是否有结束符
							markEndStrIndex = runTempText.substring(markBeginStrIndex).lastIndexOf("}");
							if (markEndStrIndex < 0) {
								markKeySB = new StringBuffer(runTempText);
								markBeginRunIndex = p; // 记录run索引
							}
						} else if (markBeginMissing && runTempText.startsWith("{")) {
							// 标记开始
							markEndStrIndex = runTempText.lastIndexOf("}");
							markBeginStrIndex = runTempText.lastIndexOf("${");// 最后一个开始符
							if (markEndStrIndex < 0 || markEndStrIndex < markBeginStrIndex) { // 没有结束或结束符在开始符之前
								markKeySB = new StringBuffer("$");
								markKeySB.append(runTempText);
								paragraph.removeRun(p--); // 移除当前位置
								markBeginRunIndex = p; // 记录run索引
							}
						}
					}
					markBeginMissing = runTempText.endsWith("$"); // 文本最后包含部分标记
				}
			}
		}
	}
	/**
	 * 重建模板，拷贝迭代数据部分
	 * @param document
	 * @param copyNum 拷贝数量
	 * @return
	 * @throws Exception
	 */
	private static CustomXWPFDocument rebuildDocumentForListData(
			CustomXWPFDocument document, int copyNum) throws Exception {
		XWPFParagraph paragraph;
		int startLineIndex = -1;
		List<XWPFParagraph> paragraphCopyList = new ArrayList<XWPFParagraph>(); // 存储拷贝的段落
		List<XWPFParagraph> paragraphList = document.getParagraphs();
		for (int i = 0; i < paragraphList.size(); i++) {
			paragraph = paragraphList.get(i);
			if (startLineIndex > -1) {
				paragraphCopyList.add(paragraphList.get(i));
				if (paragraph.getRuns().toString().contains("$:end}")) {
					// 循环结束，跳出，结束标签也写入模板
					break;
				}
			} else if (paragraph.getRuns().toString().contains("$:start}")) {
				// 循环开始标记位置
				startLineIndex = i;
				document.removeBodyElement(i--); // 移除开始标签位置整行
			}
		}
		for (int copyTimes = 0; copyTimes < copyNum; copyTimes++) { // 拷贝copyNum次
			for (int i = 0; i < paragraphCopyList.size(); i++) {
				document.createParagraph(); // 创建位置，再将copy的部分追加到索引位置
				document.setParagraph(paragraphCopyList.get(i), document.getParagraphs().size() - 1);
			}
		}
		// 转换循环模板文件，重新加载document(此种方式实现是由于poi无法进行完整对象拷贝，复制的段落无法单独操作)
		System.out.println("生成多组数据临时模板文件....");
		File templateTempFile = null;
		FileOutputStream outStream = null;
		try {
			templateTempFile = File.createTempFile("templateTemp" + System.currentTimeMillis(), ".docx");
			outStream = new FileOutputStream(templateTempFile);
			document.write(outStream);
			outStream.close();
			document = new CustomXWPFDocument(new FileInputStream(templateTempFile));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (templateTempFile != null) {
					System.out.println("清理临时模板...");
					templateTempFile.delete();
				}
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return document;
	}
	/***
	 * 标记变量填充数据，可完整保留标记样式
	 * @param document
	 * @param mapList  多组填充数据 key=value对应${key}=value
	 * @param imagePathMapList 多组图片 图片标记及对应属性 key=imageMark key=imageMark:width
	 *            key=imageMark:height 图片数据 value 图片文件路径、File对象、文件流 、key like
	 *            :height value:num、:width:300 each engineer has a duty to keep
	 *            the code elegant
	 * @throws Exception
	 */
	private static void setDataForMark(CustomXWPFDocument document,
			List<Map<String, String>> mapList,
			List<Map<String, ?>> imagePathMapList) throws Exception {
		List<XWPFRun> runList;
		XWPFRun markRun;
		String markKey; // ${标记}字符串
		int dataIndex = 0; // 当前数据组
		int markTextListSize = mapList == null ? 0 : mapList.size();
		int markImageListSize = imagePathMapList == null ? 0 : imagePathMapList.size();
		Map<String, String> markTextMap = markTextListSize == 0 ? null : mapList.get(dataIndex); // 标记及对应数据
		Map<String, ?> markImageMap = markImageListSize == 0 ? null : imagePathMapList.get(dataIndex); // 标记及对应数据
		List<XWPFParagraph> paragraphList = document.getParagraphs(); // 所有段落
		for (int i = 0; i < paragraphList.size(); i++) {
			XWPFParagraph paragraph = paragraphList.get(i);
			runList = paragraph.getRuns();
			if (runList.toString().contains("$:end}")) {
				// 一组数据结束，删除结束标签
				document.removeBodyElement(i--);
				if (++dataIndex < markTextListSize) {
					// 更换下组标记数据
					markTextMap = mapList.get(dataIndex);
				} else {
					markTextMap = null;
				}
				if (dataIndex < markImageListSize) {
					markImageMap = imagePathMapList.get(dataIndex);
				} else {
					markImageMap = null;
				}
				if (markTextMap == null && markImageMap == null) {
					break;
				}
			} else {
				if (markTextMap != null) {
					// 设置文本数据
					for (String key : markTextMap.keySet()) {
						markKey = "${" + key.trim() + "}";
						if (runList.toString().contains(markKey)) {// 包含key标记
							for (int p = 0; p < runList.size(); p++) {
								markRun = runList.get(p);
								// 替换匹配文本
								String textVlaue = markTextMap.get(key) == null ? "" : markTextMap.get(key);
								markRun.setText(markRun.toString().replace(markKey, "" + textVlaue + ""), 0);
							}
						}

					}
				}
				for (String key : markTextMap.keySet()) {
					// 处理特殊的key
					if (runList.toString().contains(key)) {//
						for (int p = 0; p < runList.size(); p++) {
							markRun = runList.get(p);//
							// 替换匹配文本
							String textVlaue = markTextMap.get(key) == null ? "" : markTextMap.get(key);
							markRun.setText(markRun.toString().replace(key, "   " + textVlaue + "   "), 0);
						}
					}
				}
				if (markImageMap != null) {
					// 设置图片数据
					Integer width = 184;
					Integer height = 56;
					setImageForMark(paragraph, markImageMap, document, width,height);
				}
			}
		}
	}
	/**
	 * * 标记变量填充图片数据，可完整保留标记样式
	 * @param paragraph
	 * @param imagePathMap 图片标记及对应属性 key=imageMark key=imageMark:width
	 *            key=imageMark:height 图片数据 value 图片文件路径、File对象、文件流 、key like
	 *            :height value:num、:width:300 each engineer has a duty to keep
	 *            the code elegant
	 * @param document
	 * @param width
	 * @param height
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void setImageForMark(XWPFParagraph paragraph,
			Map<String, ?> imagePathMap, CustomXWPFDocument document,
			Integer width, Integer height) throws Exception {
		List<XWPFRun> runList;
		String markKey;
		XWPFRun markRun;
		for (String key : imagePathMap.keySet()) {
			Object obj = imagePathMap.get(key);
			String runTempText;
			if (obj != null && !key.contains(":")) {
				runList = paragraph.getRuns();
				markKey = "${" + key.trim() + "}";
				// markKey = key;
				for (int p = 0; p < runList.size(); p++) {
					markRun = runList.get(p);
					runTempText = markRun.toString(); // 当前循环run文本
					if (runTempText.equals(markKey)) {
						markRun.setText("", 0);
						try {
							InputStream inputStream;
							// 将各类型转换为文件流
							List<String> filePaths = (List<String>) obj;
							for (String filePath : filePaths) {
								inputStream = new FileInputStream(filePath);// 文件地址
								ByteArrayInputStream byteInputStream = new ByteArrayInputStream(inputStream2ByteArray(inputStream, true));
								try {
									// obj 图片地址
									// String filePath = obj.toString();
									String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
									//BufferedImage bi = ImageIO.read(new File(filePath));

									int picType = getPictureType(extension);
									int ind = document.addPicture(byteInputStream, picType);
									// 设置图片的格式
									// 当你设定的分辨率是72像素/英寸时，A4纸的尺寸的图像的像素是595×842，
									// 当你设定的分辨率是150像素/英寸时，A4纸的尺寸的图像的像素是1240×1754，
									// 当你设定的分辨率是300像素/英寸时，A4纸的尺寸的图像的像素是2479×3508，
									document.createPicture(ind, width, height, paragraph);

								} catch (Exception e) {
									throw new RuntimeException(e);
								}
							}
						} catch (Exception ex) {
							System.out.println(ex);
							throw new Exception(ex);
						}
					}
				}
			}
		}
	}
	/**
	 *  将输入流中的数据写入字节数组
	 * @param in
	 * @param isClose
	 * @return
	 */
	public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
		byte[] byteArray = null;
		try {
			int total = in.available();
			byteArray = new byte[total];
			in.read(byteArray);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (isClose) {
				try {
					in.close();
				} catch (Exception e2) {
					System.out.println("关闭流失败");
				}
			}
		}
		return byteArray;
	}
	/**
	 * 根据图片类型，取得对应的图片类型代码
	 * @param picType
	 * @return
	 */
	private static int getPictureType(String picType) {
		int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
		if (picType != null) {
			if (picType.equalsIgnoreCase("png")) {
				res = CustomXWPFDocument.PICTURE_TYPE_PNG;
			} else if (picType.equalsIgnoreCase("dib")) {
				res = CustomXWPFDocument.PICTURE_TYPE_DIB;
			} else if (picType.equalsIgnoreCase("emf")) {
				res = CustomXWPFDocument.PICTURE_TYPE_EMF;
			} else if (picType.equalsIgnoreCase("jpg")
					|| picType.equalsIgnoreCase("jpeg")) {
				res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
			} else if (picType.equalsIgnoreCase("wmf")) {
				res = CustomXWPFDocument.PICTURE_TYPE_WMF;
			}
		}
		return res;
	}
	
	
	/**
	 * 根据数据生成execl文件
	 * @param fielName 模版名称
	 * @param savepath 保存路径
	 * @param filName 生成文件名
	 * @param fileName 写入数据库的文件名
	 * @param taskId 流程id
	 * @param UserId 用户id
	 * @param applyId 该操作id
	 * @param list 写入数据源
	 * @param fieldArr 写入字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean dealExecl(String fielName,String savepath,String filName,String fileName,String taskId,String UserId,
			String applyId,Map<String, Object> map,String[] fieldArr,String filepath){
		// 生成execl文件
		boolean falg = true;
		try{
			String newFilePath = "";
			List<Object> list = new ArrayList<Object>();
			Iterator<String> it=map.keySet().iterator();
			while(it.hasNext()){
				String key=(String) it.next();
				if("ApplyRegProjectModel".equals(key)){
					list = (List<Object>) map.get("ApplyRegProjectModel");
				}else if("AptitudesBusinessPersonModel".equals(key)){
					list = (List<Object>) map.get("AptitudesBusinessPersonModel");
				}else if("BusinessListModel".equals(key)){
					list = (List<Object>) map.get("BusinessListModel");
				}
			}
			String templatePath = this.getClass().getResource("/").getPath();
			templatePath = templatePath + File.separator + "config" + File.separator + "template" + File.separator + fielName;
			File dbfFile = new File(templatePath);
			if (!dbfFile.exists() || dbfFile.isDirectory()) {
				dbfFile.createNewFile();
			}
			System.out.println("加载execl模板....");
			InputStream is = new FileInputStream(dbfFile);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row = null;
			// 在相应的单元格进行赋值
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i+1);
					for (int j = 0; j < fieldArr.length; j++) {
						XSSFCell cell = null;
						if (i == 0) {
							cell = row.createCell(j);
						} else {
							cell = row.createCell(j);
						} //得到行的样式
						XSSFRow styleRow = sheet.getRow(1);
						cell.setCellStyle(styleRow.getCell(j).getCellStyle());
						String value = BeanUtils.getProperty(list.get(i),fieldArr[j]);
						cell.setCellValue(value);
					}
				}
			}
			// 修改模板内容导出新模板
			File newFile = new File(savepath); 
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
			if (newFile != null && newFile.getPath() != "") {
				newFilePath = newFile.getPath();
			}
			FileOutputStream out = new FileOutputStream(newFilePath + File.separator + filName);
			wb.write(out);
			out.close();
			System.out.println("生成execl成功....");
			//保存数据到数据库
			/*
			savepath = filepath + File.separator + filName;
			UploadFile uploadFile = new UploadFile();
			uploadFile.setBusinessKey(applyId);
			uploadFile.setBig_class_id(applyId);
			uploadFile.setBusinessType(filepath);
			File saveFile = new File(FileUtils.filePath() + File.separator+ savepath);
			uploadFile.setFileSize(FileUtils.formatSize(saveFile.length()));
			uploadFile.setSourcePath(savepath);
			uploadFile.setFileName(fileName);
			uploadFile.setCreate_user(UserId);
			uploadFile.setTaskId(taskId);
			uploadFile.setCreateTime(DateUtils.getDate());
			uploadFileServices.insertFile(uploadFile);
			*/
			falg = true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("生成execl文件错误！"+e);
		}
		return falg;
				
	}
	
	/**
	 * 向图片写入数据  生成图片
	 * @param list X Y VALUE   位置和值
	 * @param length 写入数量
	 * @param fontColor  字体颜色
	 * @param fontSize  字体大小
	 * @param qualNum  图片质量
	 * @param fileName  模版名称
	 * @param PathName  保存文件夹
	 * @param FileName  中文 文件名称
	 * @param applyId  操作Id
	 * @param userId  操作用户Id
	 * @param taskId  流程Id
	 * @param other  特殊设置标识
	 * @return
	 */
	public boolean createImageMarks(List<HashMap<Object, String[]>> list,int length,Color fontColor,int fontSize,float qualNum,
			String fileName,String PathName,String FileName,String applyId,String userId,String taskId,String other){
		boolean falg = false;
		try{
			String filePath = this.getClass().getResource("/").getPath();
			filePath = filePath + File.separator + "config" + File.separator + "template" + File.separator + fileName;
			String savepath = ""; //UploadFileSingleUtil.backPath(fileName,PathName);
			savepath = savepath.substring(0, savepath.lastIndexOf(".")) + ".png";
			
			ImageIcon imgIcon=new ImageIcon(filePath);		Image theImg =imgIcon.getImage();
			int width=theImg.getWidth(null);		int height= theImg.getHeight(null);
			BufferedImage bimage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2d=bimage.createGraphics();		graphics2d.setColor(fontColor);
			graphics2d.setBackground(Color.white);		graphics2d.drawImage(theImg, 0, 0, null );
			for(HashMap<Object, String[]> lists : list){//填充数据
				for(int a=0;a<length;a++){
					if("year".equals(other)){//年审图片字体
						int axisX = Integer.parseInt(lists.get("X")[a].toString());
						int axisY = Integer.parseInt(lists.get("Y")[a].toString());
						String value = lists.get("value")[a].toString();
						if(a>=10&&a!=11){
							graphics2d.setFont(new Font("楷体", Font.BOLD, 18)); //字体、字型、字号
						}else{
							graphics2d.setFont(new Font("楷体", Font.BOLD, 26)); //字体、字型、字号
						}
						if(value.length()>=17){
							String stavalue = value.substring(0, 17);
							int staY = axisY - 12;
							graphics2d.drawString(stavalue, axisX, staY);
							String endvalue = value.substring(17,value.length());
							int endY = axisY + 8;
							graphics2d.drawString(endvalue, axisX, endY);
						}else{
							graphics2d.drawString(value, axisX, axisY);
						}
					}else{
						graphics2d.setFont(new Font("楷体", Font.BOLD, fontSize)); //字体、字型、字号
						int axisX = Integer.parseInt(lists.get("X")[a].toString());
						int axisY = Integer.parseInt(lists.get("Y")[a].toString());
						String value = lists.get("value")[a].toString();
						graphics2d.drawString(value, axisX, axisY);
					}
				}
			}
			graphics2d.dispose();
		
			FileOutputStream out=new FileOutputStream(savepath); //先用一个特定的输出文件名
			JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(qualNum, true);	encoder.encode(bimage, param);
			out.close();
			/*
			savepath = savepath.substring(savepath.lastIndexOf(File.separator));
			savepath = PathName + savepath;		UploadFile uploadFile = new UploadFile();
			uploadFile.setBusinessKey(applyId);			uploadFile.setBig_class_id(applyId);
			uploadFile.setBusinessType(PathName);
			File saveFile = new File(FileUtils.filePath() + File.separator + savepath);
			uploadFile.setFileSize(FileUtils.formatSize(saveFile.length()));
			uploadFile.setSourcePath(savepath);			uploadFile.setFileName(FileName);
			uploadFile.setCreateUser(userId);			uploadFile.setTaskId(taskId);
			uploadFile.setCreateTime(new Date());
			uploadFileServices.insertFile(uploadFile);
			*/
			falg = true;
		}catch(Exception e){
			System.out.println(e);
			throw new RuntimeException("生成图片错误", e);
		}
		return falg;
	}
	
	/**
	 * 压缩文件并删除多余文件
	 * @param savepath 存放文件夹名
	 * @param zipName 附加文件名
	 * @param fileName 数据库附加文件名
	 * @param applyId  该操作id
	 * @param taskId 流程id
	 * @param UserId 用户id
	 */
	public String dealZip(String savepath,String zipName,String fileName,
			String applyId,String taskId,String UserId) {
		String path = "";
		try {
			System.out.println("获取所需文件数据....");
			/*
			UploadFile uploadFile = new UploadFile();
			uploadFile.setBig_class_id(applyId);
			uploadFile.setTaskId(taskId);
			List<UploadFile> Filelist = uploadFileServices.selectByParamsAll(uploadFile);
	        Map<String, String> maps = new HashMap<String, String>();
	        String parentPath = FileUtils.filePath();// 文件父路径
	        for (UploadFile map : Filelist) {
	            String fileNames = map.getFileName();
	            String sourcePath = map.getSourcePath();
	            if (StringUtils.isNotEmpty(sourcePath) && StringUtils.isNotEmpty(fileNames)) {
	                sourcePath = parentPath + File.separator + sourcePath;
	            }
	            maps.put(sourcePath, fileNames);
	        }
	        String outPath = parentPath + File.separator + savepath;
	        System.out.println("开始打包....");
	        FileUtils.fileZipCompress(maps, outPath, zipName);// 压缩文件
	        for (UploadFile map : Filelist) {//删除生成的数据
	             uploadFile.setUuidId(map.getUuidId());
	             uploadFile.setSwfPath(map.getSwfPath());
	             uploadFile.setSourcePath(map.getSourcePath());
	             uploadFileServices.deleteByPrimaryKey(uploadFile);
	        }
	        System.out.println("打包完成....");
			//保存数据到数据库
	        uploadFile.setBusinessKey(applyId);
			uploadFile.setBig_class_id(applyId);
			uploadFile.setBusinessType(savepath);
			savepath +=  File.separator + zipName;
			File saveFile = new File(FileUtils.filePath() + File.separator+ savepath);
			uploadFile.setFileSize(FileUtils.formatSize(saveFile.length()));
			uploadFile.setSourcePath(savepath);
			uploadFile.setFileName(fileName);
			uploadFile.setRemark("zip");
			uploadFile.setCreate_user(UserId);
			uploadFile.setTaskId(taskId);
			uploadFile.setCreateTime(DateUtils.getDate());
			uploadFileServices.insertFile(uploadFile);
			*/
			path = savepath;
		} catch (Exception e) {
			throw new RuntimeException("生成失败", e);
		}
		return path;
	}
	
}
