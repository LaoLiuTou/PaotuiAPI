package com.paotui.utils.wxpay;


import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class WXConfigUtil implements WXPayConfig {
    private byte[] certData;
    public static final String APP_ID = "wx810cc7454a48b9c9";
    public static final String KEY = "90948b0f6dfb074db992f8682e86e9a0";
    public static final String MCH_ID = "1537878521";

    public WXConfigUtil() throws Exception {
        String certPath = "E:/cert/apiclient_cert.p12";//从微信商户平台下载的安全证书存放的路径
        

        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return APP_ID;
    }

    //parnerid，商户号
    public String getMchID() {
        return MCH_ID;
    }

    public String getKey() {
        return KEY;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}

