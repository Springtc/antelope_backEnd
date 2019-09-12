package com.pronghorn.core.helper;

import com.pronghorn.core.config.FileConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取系统中涉及到的文件路径
 *
 * @author myang
 */

/**
 * <br>
 * 文件夹中包含年份，因此做修改，获取路径时请根据不同情况使用getFilePath，不同类型的文件夹请配置常量FOLDER_TYPE_* <br>
 * 对应的UploadHelper中请使用doUpload(File file,String fileName,String folder_type)
 *
 * @author gbx
 * @version 2014-01-09
 */
public class FilePathHelper {

    @Autowired
    private FileConfig fileConfig;

    private FilePathHelper() {
        fileConfig.getRootPath();
    }

    public static String getFilePath(String folder_type, String year) {
        return mkdirs(getFileRoot() + year + "/" + folder_type + "/");
    }

    public static String getFilePath(String path) {
        return mkdirs(getFileRoot() + path + "/");
    }

    public static String getNewPath(String folder_type) {
        return mkdirs(getFileRoot() + DateHelper.getCurrentYear() + "/" + folder_type + "/");
    }

    public static String getRelativePath(String fileName, String folder_type, String year) {
        return year + "/" + folder_type + "/" + fileName;
    }

    /**
     * 获取文件服务器路径
     */
    public static String getFileRoot() {
        return "C:\\\\antelop";
    }

    /**
     * if path not exsits,create directory
     */
    public static String mkdirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return path;
    }

    /**
     * 拼接URL参数
     *
     * @param baseUrl 基本的URL
     * @param params  参数key
     * @return
     */
    public static String appendUrl(String baseUrl, String... params) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (params.length > 0) {
            for (int i = 0; i < params.length; i += 2) {
                map.put(params[i], ((i + 1 < params.length) ? params[i + 1] : ""));
            }
        }
        return appendUrl(baseUrl, map);
    }

    /**
     * 拼接URL参数
     *
     * @return
     */
    public static String appendUrl(String baseUrl, Map<String, String> map) {
        String url = baseUrl;
        if (null != map && map.size() > 0) {
            List<String> params = new ArrayList<String>();
            url += StringHelper.contains(baseUrl, "?") ? "&" : "?";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (StringHelper.isNotBlank(entry.getKey())) {
                    params.add(entry.getKey() + "=" + encodeParameter(entry.getValue()));
                }
            }
            url += StringHelper.join(params, "&");
        }

        return url;
    }

    private static String encodeParameter(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

}