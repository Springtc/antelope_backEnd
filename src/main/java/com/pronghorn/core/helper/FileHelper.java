package com.pronghorn.core.helper;

import com.google.common.io.Files;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 文件处理工具类
 * 
 * @author Joinfun
 * @version 2015年8月28日 15:47:54 王磊 补充头信息
 * @version 2015年10月26日 19:47:47 王磊 补充判断语句后的跨号 */
public class FileHelper {

	private static Logger logger = LoggerFactory.getLogger(FileHelper.class);

	private final static String illegalString = "[*/?:><|\\\\]";

	/** 创建文件目录 */
	public static String mkdirs(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	/** 替换所有文件名里的非法字符 */
	public static String replaceInvaildChar(String str) {
		if (str == null) {
			return null;
		}
		Pattern pattern = Pattern.compile(illegalString);
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll("_");
	}

	/** 获取操作系统相关分隔符 */
	public static String getSlash() {
		return "/";
	}

	/** 获取文件扩展名 */
	public static String getExtensionFileName(File file) {
		if (file == null || file.isDirectory() || StringHelper.isEmpty(file.getName())) {
			return null;
		}

		String fileName = file.getName();

		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

		if (ext != null) {
			ext = ext.toLowerCase();
		}

		return ext;
	}

	/** 获取文件扩展名 ,带"." */
	public static String getExtensionFileName(String fileName) {
		if (null == fileName) {
			return null;
		}
		int pos = fileName.lastIndexOf(".");
		if (pos < 0) {
			return "";
		}

		String ext = fileName.substring(pos + 1);

		return "." + ext.toLowerCase();
	}

	/** 防止路径最后没有斜杠 */
	public static String getSafetyPath(String path) {
		if (path == null) {
			return null;
		}
		if (path.endsWith("/") || path.endsWith("\\")) {
			return path;
		} else {
			return path + getSlash();
		}
	}

	/** 获取文件名 */
	public static String getFileName(String path) {
		path = path.replace("/", getSlash());
		path = path.replace("\\", getSlash());
		int index = StringHelper.lastIndexOf(path, getSlash());
		return StringHelper.right(path, path.length() - index - 1);
	}

	/** 根据日期生成目录结构 yyyy/MM/dd */
	public static String getPathByDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.format(date) + getSlash();
	}

	/** 根据原始文件名生成唯一文件名 */
	public static String getUniqueFileName(String fileName) {
		return DateTime.now().getMillis() + "_" + fileName;
	}

	/** 上传文件到指定文件夹
	 * 
	 * @param filedata 上传文件
	 * @param fullFilePath 目标文件路径 */
	public static void doUpload(File filedata, String fullFilePath) throws IOException {
		File storageFile = new File(fullFilePath);
		try {
			Files.createParentDirs(storageFile);
			Files.copy(filedata, storageFile);
		} catch (IOException e) {
			throw e;
		}
	}

	/** 根据文件后缀名判断文件类型是否一致 */
	public static boolean justyTypeMatchedBySuffix(String fileType, File file) {
		String ext = getExtensionFileName(file);
		if ((StringHelper.isNotBlank(ext) && StringHelper.isNotBlank(fileType)) && (ext.equalsIgnoreCase(fileType) || fileType.toLowerCase().indexOf(ext) != -1)) {
			return true;
		}

		return false;
	}

	/** 根据文件后缀名判断文件类型是否一致 */
	public static boolean justyTypeMatchedBySuffix(String fileType, String fileName) {

		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (ext != null) {
			ext = ext.toLowerCase();
		}
		if ((StringHelper.isNotBlank(ext) && StringHelper.isNotBlank(fileType)) && (ext.equalsIgnoreCase(fileType) || fileType.toLowerCase().indexOf(ext) != -1)) {
			return true;
		}

		return false;
	}

	/** 检查文件是否符合格式,false为合格，true为不合格 */
	public static boolean checkContentTypeInvalid(String filedataFileName, List<String> invalidExt) {
		if (StringHelper.isBlank(filedataFileName) || null == invalidExt) {
			return false;
		}
		String extWithDot = FileHelper.getExtensionFileName(filedataFileName);
		return invalidExt.contains(extWithDot);
	}

	/** 计算文件行数 从零开始 */
	public static int countFileTotalRows(File Filedata) {

		int lineCount = 0;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Filedata));
			try {
				while (br.readLine() != null) {
					lineCount++;
				}
			} catch (IOException e) {
				logger.info("context", e);
				throw new RuntimeException(e.getMessage());
			} finally {
				try {
					if (null != br) {
						br.close();
					}
				} catch (IOException e) {
					logger.info("context", e);
				}
			}

		} catch (FileNotFoundException e) {
			logger.info("context", e);
			throw new RuntimeException(e.getMessage());
		}

		return lineCount;
	}

	/** MultipartFile 转换成File
	 *
	 * @param multfile 原文件类型
	 * @return File
	 * @throws IOException */
	public static File multipartToFile(MultipartFile multfile) throws IOException {
		CommonsMultipartFile cf = (CommonsMultipartFile) multfile;
		// 这个myfile是MultipartFile的
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File file = fi.getStoreLocation();
		// 手动创建临时文件
		if (file.length() < 2048) {
			File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getName());
			multfile.transferTo(tmpFile);
			return tmpFile;
		}
		return file;
	}
}
