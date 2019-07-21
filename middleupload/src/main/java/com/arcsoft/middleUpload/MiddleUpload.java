package com.arcsoft.middleUpload;

import com.tencentcloudapi.faceid.v20180301.models.GetActionSequenceResponse;
import com.tencentcloudapi.faceid.v20180301.models.LivenessResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;
import sun.plugin2.util.PojoUtil;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @author zh6753
 * @date 2019/7/19 9:57
 */
@RestController
public class MiddleUpload {

    /**
     * @param video
     * @param request
     * @return 腾讯返回的响应
     */
    @RequestMapping("/uploadVideo")
    public String uploadFileToTencent(MultipartFile video,HttpServletRequest request)
    {
        String videoBase64 = "";
        String livenessType = "ACTION";
        String validateData = "";
        //接收客户端文件并保存文件
        String fileCompleteName = MyUploadFile.receiveFile(video, request);
        File file;
        if(StringUtils.isEmpty(fileCompleteName))
        {
            return "获取文件失败";
        }
        else
        {
            String filePath = "E:\\fileTest"+"\\"+fileCompleteName;
            file = new File(filePath);
        }
        //将file文件转换为base64编码格式的字符串
        try {
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
        //VideoBase64参数
            videoBase64 = new BASE64Encoder().encode(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        //到这里已经获得了客户端上传的视频,开始调用腾讯的接口
        GetActionSequenceResponse getActionSequenceResponse = TencentApi.getTencentActionSequence();
        LivenessResponse livenessResponse = TencentApi.getTencentLiveness(livenessType, videoBase64, getActionSequenceResponse.getActionSequence());
        String respJsonString = PojoUtil.toJson(livenessResponse);
        //上传给腾讯后删除临时文件
        if(file.exists())
        {
            file.delete();
        }
        //将腾讯的响应返回
        return respJsonString;
    }

}
