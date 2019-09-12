package com.pronghorn.core.helper;

import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/** 下载工具类
 * 
 * @author Joinfun
 * @version 2015年8月28日 16:09:19 王磊 补充头信息
 * @version 2015年10月26日 19:47:47 王磊 补充判断语句后的跨号 */
public class FileDownloadHelper {

	private static Logger logger = LoggerFactory.getLogger(FileDownloadHelper.class);

	/** 使用拉模式下载文件 <br>
	 * 不会自动关闭输出流，必要时请手动关闭
	 * 
	 * @author 管帮翔
	 * @version 2014-01-03 */
	/*public static void downloadFile(String filePath, OutputStream out) throws IOException {
		File file = new File(filePath);
		downloadFile(file, out);
	}*/

	/** 使用拉模式下载文件 <br>
	 * 不会自动关闭输出流，必要时请手动关闭
	 * 
	 * @author 管帮翔
	 * @version 2014-01-06 */
	/*public static void downloadFile(File file, OutputStream out) throws IOException {
		if (null != file) {
			InputSupplier<FileInputStream> inputSupplier = Files.newInputStreamSupplier(file);
			try {
				ByteStreams.copy(inputSupplier.getInput(), out);
			} catch (IOException e1) {
				logger.error("下载文件出错:" + file, e1);
				throw e1;
			}
		} else {
			logger.error("下载文件出错:没有权限下载");
		}

	}*/

	public static String encodeFileName(String fileName) throws UnsupportedEncodingException {
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = StringHelper.replace(fileName, "+", "%20");
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = URLDecoder.decode(fileName, "UTF-8");
		return fileName;
	}

	public static void downloadFile(InputStream in, OutputStream out) throws IOException {
		try {
			ByteStreams.copy(in, out);
		} catch (IOException e1) {
			logger.error("下载错误", e1);
			throw e1;
		}
	}
}
