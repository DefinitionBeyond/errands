package com.campus.dev.service.impl;

import com.campus.dev.bean.BadException;
import com.campus.dev.bean.HttpRequestOptions;
import com.campus.dev.bean.TransactionalForAll;
import com.campus.dev.dao.mapper.FileMapper;
import com.campus.dev.enums.FileType;
import com.campus.dev.model.FileDO;
import com.campus.dev.service.FileService;
import com.campus.dev.util.MD5GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${base.pic.dir:/data/pic}")
    private String baseDir;

    @Autowired
    private FileMapper fileMapper;

    @TransactionalForAll
    @Override
    public FileDO upload(int type, MultipartFile file, HttpRequestOptions httpRequestOptions) throws Exception {

        if(file.isEmpty()){
            throw new BadException("文件为空");
        }

        FileDO fileDO = writeFile(file, type, httpRequestOptions);


        fileMapper.insert(fileDO);

        return fileDO;


    }

    private FileDO writeFile(MultipartFile file, int type, HttpRequestOptions httpRequestOptions) throws Exception {
        String orgFileName = file.getOriginalFilename();
        String newFIleName = MD5GeneratorUtil.getMd5Utf8(orgFileName, UUID.randomUUID().toString());
        String childDir = FileType.getPathByType(type);

        File dest = new File(baseDir +"/"+ childDir + "/" + newFIleName);

        file.transferTo(dest);

        return FileDO.builder()
                .creator(httpRequestOptions.getUserId())
                .name(newFIleName)
                .type(type)
                .build();
    }

    @TransactionalForAll
    @Override
    public List<FileDO> bulkUpload(MultipartFile[] files, int type, HttpRequestOptions httpRequestOptions) throws Exception {
        List<FileDO> fileDOList = new ArrayList<>();

        for (MultipartFile file : files) {
            if(file.isEmpty()){
                continue;
            }

            FileDO fileDO = writeFile(file, type, httpRequestOptions);

            fileDOList.add(fileDO);

        }

        if(!CollectionUtils.isEmpty(fileDOList)){
            fileMapper.bulkInsert(fileDOList);
        }

        return fileDOList;

    }

    @Override
    public List<FileDO> list(int type) {
        return fileMapper.list(type);
    }

    @TransactionalForAll
    @Override
    public boolean deleteById(int id) {
        FileDO fileDO = fileMapper.getById(id);
        if(null == fileDO){
            throw new BadException("找不到对应文件");
        }
        return false;

    }
}
