package com.qiangzi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

/**
 * Created by qiang on 2019/2/27.
 * 定义resource配置文件类   方便在properties.yml 中配置
 */

@Component
@ConfigurationProperties(prefix =  "alipay.config" )
public class AliPayConfig {
    // 商户appId
    private  String appId ;
    // 私钥 pkcs8格式的
    private  String rsaPrivateKey ;
    // 支付完成页面异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private  String notifyUrl ;
    // 支付完成页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    private  String returnUrl ;
    // 请求网关地址 正式环境：https://openapi.alipay.com/gateway.do  沙箱环境：https://openapi.alipaydev.com/gateway.do
    private  String url = "https://openapi.alipay.com/gateway.do";
    // 编码
    private  String charset = "UTF-8";
    // 返回格式
    private  String format = "json";
     // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String aliPayPublicKey;
    // 日志记录目录
    private  String logPath = "/log";
    // 签名方式
    private  String signType = "RSA2";
    //手机网站 销售产品码，商家和支付宝签约的产品码。该产品请填写固定值：QUICK_WAP_WAY
    private String productCodeWeb ="QUICK_WAP_WAY";
    //电脑网站 销售产品码，与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
    private String productCodePage ="FAST_INSTANT_TRADE_PAY";

    public String getProductCodeWeb() {
        return productCodeWeb;
    }

    public void setProductCodeWeb(String productCodeWeb) {
        this.productCodeWeb = productCodeWeb;
    }

    public String getProductCodePage() {
        return productCodePage;
    }

    public void setProductCodePage(String productCodePage) {
        this.productCodePage = productCodePage;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public void setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
