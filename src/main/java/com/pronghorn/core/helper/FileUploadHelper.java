package com.pronghorn.core.helper;

import com.google.common.io.Files;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class FileUploadHelper {

	private static Logger logger = LoggerFactory.getLogger(FileUploadHelper.class);

	public static UploadFileBean doUpload(File filedata, String filedataFileName, String filedataContentType, String writePath) throws IOException {
		String storageFileName = generateStorageFileName(filedataFileName);
		String storageFilePath = writePath + storageFileName;	// 文件磁盘存储路径
		File storageFile = new File(storageFilePath);
		try {
			Files.createParentDirs(storageFile);
			Files.copy(filedata, storageFile);
			return new UploadFileBean(storageFileName, storageFilePath, filedataFileName);
		} catch (IOException e) {
			throw e;
		}
	}

	public static UploadFileBean doFormUpload(File filedata, String filedataFileName, String writePath, Calendar calendar) throws IOException {
		String storageFileName = calendar.getTime().getTime() + "_" + filedataFileName;
		String storageFilePath = writePath + "\\" + storageFileName;	// 文件磁盘存储路径
		File storageFile = new File(storageFilePath);
		try {
			Files.createParentDirs(storageFile);
			Files.copy(filedata, storageFile);
			return new UploadFileBean(storageFileName, storageFilePath, filedataFileName);
		} catch (IOException e) {
			throw e;
		}
	}

	private static String generateStorageFileName(String fileName) {
		String extName = Files.getFileExtension(fileName);
		String uuid = UUID.randomUUID().toString();
		return String.format("%s.%s", uuid, extName);
	}

	/** 文件上传
	 *
	 * @author gbx
	 * @version 2014-01-09
	 * @param file
	 * @param fileName
	 * @param folder_type 请使用FilePathHelper中的常量(路径)
	 * @return 相对路径
	 * @throws IOException */
	public static String doUpload(File file, String fileName, String folder_type) throws IOException {
		String storageFileName = Calendar.getInstance().getTime().getTime() + "_" +  PinyinUtils.trans2PinYin(fileName);
		String year = DateTime.now().toString("yyyyMM");
		String writePath = FilePathHelper.getFileRoot() + "/" + storageFileName;
		File storageFile = new File(writePath);
		try {
			logger.info(writePath);
			logger.info(storageFile.getPath());
			Files.createParentDirs(storageFile);
			Files.copy(file, storageFile);
			//return FilePathHelper.getRelativePath(storageFileName, folder_type, year);
			return "/" + storageFileName;
		} catch (IOException e) {
			throw e;
		}
	}

	public static String doUpload(MultipartFile file){
//		CommonsMultipartFile cf = (CommonsMultipartFile)file;
//		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		String relativePath = null;
		try {
			relativePath = FileUploadHelper.doUpload(FileHelper.multipartToFile(file), file.getName(), "");
		} catch (Exception e) {
			logger.error("上传文件出错", e);
		}
		return relativePath;
	}

	public static class UploadFileBean {

		private String storageFileName;

		private String storageFilePath;

		private String fileName;

		public UploadFileBean(String storageFileName, String storageFilePath, String fileName) {
			this.storageFileName = storageFileName;
			this.storageFilePath = storageFilePath;
			this.fileName = fileName;
		}

		/** 实际文件名 */
		public String getStorageFileName() {
			return storageFileName;
		}

		/** 实际文件路径，包含文件名 */
		public String getStorageFilePath() {
			return storageFilePath;
		}

		/** 原始文件名 */
		public String getFileName() {
			return fileName;
		}
	}
}
