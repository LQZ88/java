package com.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.mozilla.universalchardet.UniversalDetector;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

public class FileUtils {
    private static final int MAX_SIZE = 1024000;// 1000kb
	private static final PropertiesUtil pUtil = PropertiesUtil.createPropertiesUtil("");//IConstant.UPLOADPATH_PROPERTIES);
    /**
     * 对配置的进行路径转换
     * 
     * @param path 配置的路径
     * @return 配置路径
     */
    public static String tomcatPath(String path) {
        StringBuffer filePath = new StringBuffer(100);
        Pattern p = Pattern.compile("\\$\\{[a-zA-Z0-9.]+\\}");
        Matcher m = p.matcher(path);
        if (m.find()) {
            String str = m.group();
            str = str.substring(2, str.length() - 1);
            filePath.append(System.getProperty(str)).append(
                    path.substring(path.indexOf("}") + 1));
        } else {
            return path;
        }
        return filePath.toString();
    }

    /**
     * 上传文件路径
     * 
     * @return
     */
    public static String filePath() {
        PropertiesUtil pUtil = PropertiesUtil.createPropertiesUtil("");//IConstant.UPLOADPATH_PROPERTIES);
        String path = pUtil.getProperty("");//IConstant.UPLOADPATH_PATH);
        path = FileUtils.replaceSeparator(path);// 替换斜杠
        String filePath = tomcatPath(path);
        return filePath;
    }

    /**
     * 创建目录
     * 
     * @param path
     * @return File
     */
    public static File mkdirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 删除文件
     * 
     * @param sourcePath 路径名称
     * @return 0:删除失败 1:删除成功 2:文件不存在
     */
    public static int fileDelete(String sourcePath) {
        File file = new File(sourcePath);
        int mark = 0;
        if (file.exists()) {
            boolean flag = file.delete();
            if (flag) {
                mark = 1;// 删除成功
            } else {
                mark = 0;// 删除失败
            }
        } else {
            mark = 2;// 文件不存在
        }
        return mark;
    }

    /**
     * 替换斜杠符号
     * 
     * @param url
     * @return
     */
    public static String replaceSeparator(String url) {
        url = url.replace("//", File.separator);
        url = url.replace("/", File.separator);
        url = url.replace("\\\\", File.separator);
        url = url.replace("\\", File.separator);
        return url;
    }

    public static String replaceSeparator2(String url) {
        url = url.replace("//", "/");
        url = url.replace("/", "/");
        url = url.replace("\\\\", "/");
        url = url.replace("\\", "/");
        return url;
    }

    /**
     * 将斜杠替换成指定的符号
     * 
     * @param url
     * @param specify
     * @return
     */
    public static String replaceSeparatorSpecify(String url, String specify) {
        url = url.replace("//", specify);
        url = url.replace("/", specify);
        url = url.replace("\\\\", specify);
        url = url.replace("\\", specify);
        return url;
    }

    /**
     * 替换空格
     * 
     * @param source
     * @return
     */
    public static String replaceSpace(String source) {
        source = source.replace(" ", "");
        return source;
    }

    /**
     * 文件压缩工具类
     * 
     * @param Map<String,String> sourcePath key:源路径,value:文件名称
     * @param outPath 输出临时路径
     * @param ourFileName 输出的文件名称
     */
    public static void fileZipCompress(Map<String, String> source,
            String outPath, String outFileName) {
        BufferedOutputStream bos = null;
        try {
            File dict = new File(outPath);
            if (!dict.exists()) {
                dict.mkdirs();
            }

            File outFile = new File(outPath + File.separator + outFileName);

            FileOutputStream fos = new FileOutputStream(outFile);
            // 输出校验流,采用Adler32更快
            CheckedOutputStream cos = new CheckedOutputStream(fos,
                    new Adler32());
            // 创建压缩输出流
            ZipOutputStream zos = new ZipOutputStream(cos);
            // 缓冲输出流
            bos = new BufferedOutputStream(zos);
            // 设置Zip文件注释
            // zos.setComment("A test of java Zipping");
            
            zos.setEncoding("UTF-8");

            Iterator<String> it = source.keySet().iterator();
            while (it.hasNext()) {
                String filePath = it.next();// 文件路径
                String fileName = source.get(filePath);// 文件名称 中文

                File file = new File(filePath);
                if (!file.exists()) {
                    break;
                }
                // 针对单个文件建立读取流
                // BufferedReader br = new BufferedReader(new FileReader(file));
                FileInputStream fis = new FileInputStream(file);
                // ZipEntry ZIP 文件条目
                // putNextEntry 写入新条目，并定位到新条目开始处
                zos.putNextEntry(new ZipEntry(fileName));

                byte[] buff = new byte[MAX_SIZE];
                int size = 0;
                while ((size = fis.read(buff)) != -1) {
                    bos.write(buff, 0, size);
                }
                fis.close();
                bos.flush();
            }
        } catch (FileNotFoundException e) {
            //LoggerUtil.loggerError(FileUtils.class, "FileNotFoundException", e);
        } catch (IOException e) {
            //LoggerUtil.loggerError(FileUtils.class, "IOException", e);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                //LoggerUtil.loggerError(FileUtils.class, "关闭流异常!", e);
            }
        }
    }

    /**
     * 文件下载
     * 
     * @param response
     * @param filePath
     */
    public static synchronized void fileDownload(HttpServletResponse response,
            String filePath, String fileName) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                bis = new BufferedInputStream(new FileInputStream(filePath));// 放到缓冲流里面

                // 获取文件输出IO流
                bos = new BufferedOutputStream(response.getOutputStream());
                // response.setContentType("application/x-download");//
                // 设置response内容的类型
                response.setHeader(
                        "Content-disposition",
                        "attachment;filename="
                                + URLEncoder.encode(fileName, "UTF-8"));// 设置头部信息
                int size = -1;
                byte[] buff = new byte[MAX_SIZE];
                // 开始向网络传输文件流
                while ((size = bis.read(buff)) != -1) {
                    bos.write(buff, 0, size);
                }
                bos.flush();// 这里一定要调用flush()方法
            }
        } catch (IOException e) {
            //LoggerUtil.loggerError(FileUtils.class, "", e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                //LoggerUtil.loggerError(FileUtils.class, "", e);
            }
        }
    }

    /**
     * 文件大小转换
     * 
     * @param s
     * @return
     */
    public static String formatSize(long s) {
        // 设置数量级
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "0B";
        if (s < 1024) {
            fileSizeString = df.format((double) s) + "B";
        } else if (s < 1048576) {
            fileSizeString = df.format((double) s / 1024) + "KB";
        } else if (s < 1073741824) {
            fileSizeString = df.format((double) s / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) s / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 截取后缀名
     * 
     * @param str 要截取的字符串
     * @return 后缀
     */
    public static String getSuffix(String str) {
        return str.substring(str.lastIndexOf(".") + 1, str.length());
    }

    /**
     * 截取后缀名
     * 
     * @param str 要截取的字符串
     * @param separator 指定的分隔符
     * @return 后缀
     */
    public static String getSuffix(String str, String separator) {
        return str.substring(str.lastIndexOf(separator) + 1, str.length());
    }

    /**
     * 截取后缀以前的字符串
     * 
     * @param str 要截取的字符串
     * @return 后缀以前的字符串
     */
    public static String getPrefix(String str) {
        return str.substring(0, str.lastIndexOf("."));
    }

    /**
     * 文件复制
     * 
     * @param source源文件路径
     * @param target目标文件路径
     */
    public static void copyFile(String source, String target) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fcin = null;
        FileChannel fcout = null;

        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            fcin = fis.getChannel();// 得到对应的文件通道
            fcout = fos.getChannel();// 得到对应的文件通道
            fcin.transferTo(0, fcin.size(), fcout);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            //LoggerUtil.loggerError(FileUtils.class, "复制文件出错!", e);
        } finally {
            try {
                fis.close();
                fcin.close();
                fos.close();
                fcout.close();
            } catch (IOException e) {
                //LoggerUtil.loggerError(FileUtils.class, "关闭文件流出错!", e);
            }
        }
    }

    /**
     * 指定编码复制文件
     * 
     * @param sourcePath 源文件路径
     * @param sourceCode 源文件编码
     * @param targetPath 目标文件路径
     * @param targetCode 目标文件编码
     */
    public static void copyFile(String sourcePath, String sourceCode,
            String targetPath, String targetCode) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(sourcePath), sourceCode));

            String allLine = "";
            String line;
            while ((line = br.readLine()) != null) {
                allLine += line + "\r\n";
            }
            br.close();

            BufferedWriter ow = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(targetPath), targetCode));
            ow.write(allLine);
            ow.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String txtCode(String fileName) {
        String code = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
            int p = (bis.read() << 8) + bis.read();
            switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String getUniversalDetector(String filePath) {
        byte[] buf = new byte[4096];
        FileInputStream fis = null;
        String encoding = null;
        try {
            fis = new FileInputStream(filePath);

            UniversalDetector detector = new UniversalDetector(null);
            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            encoding = detector.getDetectedCharset();
            detector.reset();
        } catch (FileNotFoundException e) {
            //LoggerUtil.loggerError(FileUtils.class, "FileNotFoundException", e);
        } catch (IOException e) {
            //LoggerUtil.loggerError(FileUtils.class, "IOException", e);
        }
        return encoding;
    }

    public static String getCpDetector(String filePath) {
        String result = "";

        try {

            /*------------------------------------------------ 
            detector是探测器，它把探测任务交给具体的探测实现类的实例完成。 
            cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 
            加进来，如ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector。   
            detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的 
            字符集编码。 
            ---------------------------------------------------*/
            CodepageDetectorProxy proxy = CodepageDetectorProxy.getInstance();
            /*------------------------------------------------------
              ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于 
              指示是否显示探测过程的详细信息，为false不显示。 
            -------------------------------------------------------*/

            proxy.add(new ParsingDetector(false));
            //proxy.add(JChardetFacade.getInstance());
            proxy.add(ASCIIDetector.getInstance());
            proxy.add(UnicodeDetector.getInstance());

            /*----------------------------------------------
               JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码 
               测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以 
               再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。 
             ---------------------------------------------------------*/
            Charset cset = null;
            File file = new File("D:/bug.txt");
            cset = proxy.detectCodepage(file.toURI().toURL());
            if (cset != null) {
                result = cset.name();
            } else {
                System.out.println("查不到对应的编码格式");
            }
        } catch (Exception e) {
            System.out.println("    调用getFileCodeByPlugin 方法 产生异常           ");
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 得到相对路径
     * @param filePath
     * @return
     */
    public static String getRelaticePath() {
    	String path = pUtil.getProperty("");//IConstant.UPLOADPATH_PATH);
		path = FileUtils.replaceSeparator(path);// 替换斜杠
		path = FileUtils.tomcatPath(path);
		return path;
    }
    public static void main(String[] args) {
        // System.out.println(tomcatPath(BusinessTypeConstants.SWF_PATH));
        // System.out.println(replaceSpace("sac. -- / sd"));
        System.out.println(getSuffix("sdfafd.txt"));

        // copyFile("C:/Users/Administrator/Desktop/fujian", "d:/");

        try {
            File file = new File("C:/Users/Administrator/Desktop/fujianl");
            if (file.exists())
                org.apache.commons.io.FileUtils.copyDirectory(file, new File("d:/fujians"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
