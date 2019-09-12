package com.pronghorn.coffee.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FILE_INFO")
@Data
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;

    private String uniqueFileName;

    private String uploader;

    private Date uploadDate;

    private String type;

    private String fileAddress;
}