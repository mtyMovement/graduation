package com.graduation.jaguar.core.common.base;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/4/30 0030
*/

import lombok.Data;

@Data
public class QiniuMedia {
    private static QiniuMedia media = null;
    public static final String ACCESSKEY = "XK-p4j1naOJxdp619agD2sO8goVc20cvcQc7spU0";
    public static final String SECRETKEY = "fTcRZjuw_8mnTwnL1k22uO3HSRITdE0w6uDoSTAh";
    public static final String BUCKETNAME = "jaguar_qiniu_video";
    public static final String DOMAIN = "pseglv8kw.bkt.clouddn.com";
    private String accessKey;// 设置accessKey
    private String secretKey;// 设置secretKey
    private String bucketName;// 设置存储空间
    private String domain;// 设置七牛域名

    /**
     * 实例化一个QiNiuMedia实例
     * @uesr "xinzhifu@knet.cn"
     * @date 2016年11月19日下午2:58:27
     */
    public static synchronized QiniuMedia getInstance() {
        if (media == null) {
            media = new QiniuMedia();
            media.setAccessKey(ACCESSKEY);
            media.setSecretKey(SECRETKEY);
            media.setBucketName(BUCKETNAME);
            media.setDomain(DOMAIN);
        }
        return media;
    }


}
