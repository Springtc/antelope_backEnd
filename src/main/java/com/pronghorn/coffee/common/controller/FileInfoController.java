package com.pronghorn.coffee.common.controller;

import com.google.common.collect.Maps;
import com.pronghorn.coffee.common.entity.FileInfo;
import com.pronghorn.coffee.common.service.FileInfoService;
import com.pronghorn.core.helper.*;
import com.pronghorn.core.jsonResponse.CommonResponse;
import com.pronghorn.core.jsonResponse.FailedResponse;
import com.pronghorn.core.jsonResponse.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.hql.internal.ast.util.PathHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 17:24 2019-06-04
 * @Modified By: 17:24 2019-06-04
 */
@Slf4j
@RestController
@RequestMapping(value = "/files")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoServiceImpl;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> handleFileUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        String relativePath = null;
        Map<String, Object> responseMap = Maps.newHashMap();
        FileInfo fileInfo = new FileInfo();
        String fileType = request.getParameter("type");
        try {
            relativePath = FileUploadHelper.doUpload(FileHelper.multipartToFile(file), file.getOriginalFilename(), "");
            fileInfo.setFileName(file.getName());
            fileInfo.setUniqueFileName(file.getOriginalFilename());
            fileInfo.setUploadDate(new Date());
            fileInfo.setType(fileType);
            fileInfo.setFileAddress(relativePath);
            responseMap.put("relativePath", relativePath);
            responseMap.put("fileName", file.getOriginalFilename());
        } catch (Exception e) {
            log.error("上传文件出错:" + e.getMessage(), e);
        }

        log.info("relativePath:" + relativePath);

        if (StringHelper.isBlank(relativePath)) {
            return new ResponseEntity<CommonResponse>(FailedResponse.create("附件上传失败！请退出系统重新操作，如再次失败，请联系管理员！"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        fileInfoServiceImpl.save(fileInfo);
        responseMap.put("id", fileInfo.getId().toString());
        responseMap.put("attachment", fileInfo);
        return new ResponseEntity<CommonResponse>(SuccessResponse.create(responseMap, "上传文件"), HttpStatus.OK);
    }

    //根据IC查询文件
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("id") Integer id, HttpServletResponse response) {
        try {
            FileInfo file = fileInfoServiceImpl.getByPrimaryKey(id);
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + FileDownloadHelper.encodeFileName(file.getFileName()));
            File newFile = new File(FilePathHelper.getFileRoot() + file.getFileAddress());
            FileDownloadHelper.downloadFile(new FileInputStream(newFile), response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    //根据路径名查询文件
    @RequestMapping(value = "/downloadByFilePath/{filePath}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("filePath") String filePath, HttpServletResponse response) {
        try {
            File file = new File(FilePathHelper.getFileRoot() + filePath);
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + FileDownloadHelper.encodeFileName(file.getName()));
            FileDownloadHelper.downloadFile(new FileInputStream(file), response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
