package com.graduation.jaguar.core.service;
/**
 @author maoty
 @DESCRIPTION 七牛云服务
 @create 2019/4/30 0030
*/

import com.qiniu.api.auth.AuthException;
import org.json.JSONException;

import java.io.File;
import java.io.InputStream;

public interface QiniuMediaService {
    boolean uploadFile(String localFile) throws AuthException, JSONException;
    boolean uploadFile(File file) throws AuthException, JSONException;
    boolean uploadFile(String fileName, InputStream in) throws AuthException, JSONException;
    String getFileResourceUrl(String filename) throws Exception;
    void delete(String fileName);
    String getUpToken() throws AuthException, JSONException;
}
