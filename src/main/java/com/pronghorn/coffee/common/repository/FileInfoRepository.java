package com.pronghorn.coffee.common.repository;

import com.pronghorn.coffee.common.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 23:56 2019-06-06
 * @Modified By: 23:56 2019-06-06
 */
public interface FileInfoRepository extends JpaRepository<FileInfo, Integer> {
}
