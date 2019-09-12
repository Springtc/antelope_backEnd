package com.pronghorn.coffee.common.service;

import com.pronghorn.coffee.common.entity.FileInfo;
import com.pronghorn.core.generic.GenericService;

import java.io.File;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 09:30 2019-06-03
 * @Modified By: 09:30 2019-06-03
 */
public interface FileInfoService extends GenericService<FileInfo, Integer> {
    public void save(FileInfo fileInfo);
}
