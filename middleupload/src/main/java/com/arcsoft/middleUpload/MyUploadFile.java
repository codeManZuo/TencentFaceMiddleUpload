package com.arcsoft.middleUpload;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zh6753
 * @date 2019/7/19 15:17 
 */
public class MyUploadFile {

    /**
     * 接收客户端上传的视频数据 保存为临时文件,
     *
     * @param video
     * @return
     */
    public static String receiveFile(MultipartFile video, HttpServletRequest request) {

        try {
            File fileMkdir = new File("E:\\fileTest");
            if (!fileMkdir.exists()) {
                fileMkdir.mkdir();
            }
            String oldFileName = video.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "")
                    + oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
            File file = new File(fileMkdir+"\\"+newFileName);
            video.transferTo(file);
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "获取视频文件错误";
        }

    }

 }





