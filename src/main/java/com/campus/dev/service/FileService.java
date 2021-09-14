package com.campus.dev.service;

import com.campus.dev.bean.HttpRequestOptions;
import com.campus.dev.model.FileDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileDO upload(int type, MultipartFile file, HttpRequestOptions httpRequestOptions) throws Exception ;

    List<FileDO> bulkUpload(MultipartFile[] files, int type, HttpRequestOptions httpRequestOptions) throws Exception;

    List<FileDO> list(int type);

    boolean deleteById(int id);

}
