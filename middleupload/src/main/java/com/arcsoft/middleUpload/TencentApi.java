package com.arcsoft.middleUpload;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.GetActionSequenceRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetActionSequenceResponse;
import com.tencentcloudapi.faceid.v20180301.models.LivenessRequest;
import com.tencentcloudapi.faceid.v20180301.models.LivenessResponse;

/**
 * @author zh6753
 * @date 2019/7/19 15:17 
 */
public class TencentApi {
    /**
     * 调用的是tencent的活体检测的api
     *
     * @param livenessType
     * @param videoBase64
     * @return String
     */
    public static LivenessResponse getTencentLiveness(String livenessType, String videoBase64, String validateData) {
        try {

            Credential cred = new Credential("secretIdAyq7sRp11..............AO", "secretKeyvTq84qfP..........N3");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"VideoBase64\":\"" + videoBase64 + "\",\"LivenessType\":\"" + livenessType + "\"}";
            LivenessRequest req = LivenessRequest.fromJsonString(params, LivenessRequest.class);

            LivenessResponse resp = client.Liveness(req);
            return resp;
            // System.out.println(LivenessRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println("腾讯的接口检测出现错误");
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * 获取动作顺序
     * @return
     */
    public static GetActionSequenceResponse getTencentActionSequence()
    {
        try{

            Credential cred = new Credential("secretIdAyq7sRp11..............AO", "secretKeyvTq84qfP..........N3");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            String params = "{}";
            GetActionSequenceRequest req = GetActionSequenceRequest.fromJsonString(params, GetActionSequenceRequest.class);
            GetActionSequenceResponse resp = client.GetActionSequence(req);
            return resp;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
