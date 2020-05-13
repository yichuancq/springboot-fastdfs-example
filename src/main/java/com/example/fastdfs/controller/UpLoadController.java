package com.example.fastdfs.controller;

import com.example.fastdfs.client.FastDFSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UpLoadController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FastDFSClientUtil dfsClient;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @PostMapping("/upload")
    public String fdfsUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            String fileUrl = dfsClient.uploadFile(file);
            logger.info("成功上传文件 URL {}", fileUrl);
            request.setAttribute("msg", "成功上传文件，  '" + fileUrl + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * http://localhost/deleteFile?filePath=group1/M00/00/00/wKgIZVzZaRiAZemtAARpYjHP9j4930.jpg
     *
     * @param filePath group1/M00/00/00/wKgIZVzZaRiAZemtAARpYjHP9j4930.jpg
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/deleteFile")
    public String delFile(String filePath, HttpServletRequest request, HttpServletResponse response) {

        try {
            dfsClient.delFile(filePath);
        } catch (Exception e) {
            // 文件不存在报异常 ： com.github.tobato.fastdfs.exception.FdfsServerException: 错误码：2，错误信息：找不到节点或文件
            e.printStackTrace();
        }
        request.setAttribute("msg", "成功删除，'" + filePath);
        return "index";
    }


}
