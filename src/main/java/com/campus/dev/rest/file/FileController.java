package com.campus.dev.rest.file;

import com.campus.dev.bean.HttpRequestOptions;
import com.campus.dev.dto.ResultDTO;
import com.campus.dev.model.FileDO;
import com.campus.dev.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/v1/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private HttpRequestOptions httpRequestOptions;

    @PostMapping("/upload/{type}")
    public ResultDTO<FileDO> upload(@PathVariable("type")int type, @RequestParam("file")MultipartFile file) throws Exception {
        return new ResultDTO<>(fileService.upload(type, file, httpRequestOptions));

    }

    @PostMapping("/uploads/{type}")
    public ResultDTO<List<FileDO>> bulkUpload(@RequestParam("files")MultipartFile[] files, @PathVariable("type")int type) throws Exception {
        if(files.length > 9)throw new Exception("文件数量超出限制");
        return new ResultDTO<>(fileService.bulkUpload(files, type, httpRequestOptions));
    }


    @GetMapping("list")
    public ResultDTO<List<FileDO>> list(@RequestParam("type")int type){
        return new ResultDTO<>(fileService.list(type));
    }

    @PostMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteById(@PathVariable("id")int id){
        return new ResultDTO<>(fileService.deleteById(id));
    }

}
