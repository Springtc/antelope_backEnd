package com.pronghorn.coffee.common.service.impl;

import com.pronghorn.coffee.common.entity.FileInfo;
import com.pronghorn.coffee.common.repository.FileInfoRepository;
import com.pronghorn.coffee.common.service.FileInfoService;
import com.pronghorn.core.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 09:31 2019-06-03
 * @Modified By: 09:31 2019-06-03
 */
@Service
@Transactional
public class FileInfoServiceImpl extends GenericServiceImpl<FileInfo, Integer> implements FileInfoService {
    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Override
    public void save(FileInfo fileInfo) {
        fileInfoRepository.saveAndFlush(fileInfo);
    }
}
