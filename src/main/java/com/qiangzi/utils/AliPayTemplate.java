package com.qiangzi.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.qiangzi.config.AliPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by qiang on 2019/2/27.
 * 支付工具类
 */
@Component
public class AliPayTemplate {


    @Autowired
    private AliPayConfig aliPayConfig;

    /**
     *  功能：支付宝手机网站支付接口(alipay.trade.wap.pay)接口
     *
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param subject 订单名称，必填
     * @param total_amount 付款金额，必填
     * @param describe 商品描述，可空
     * @param timeout_express 超时时间 可空  2m
     * @param product_code 销售产品码 必填
     * @return 返回表单
     */
    public String webPay(String out_trade_no , String subject,String total_amount,String describe,String timeout_express,String product_code ) throws AlipayApiException{
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = getAliPayClient();
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(describe);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        // 设置同步地址
        alipay_request.setReturnUrl(aliPayConfig.getReturnUrl());
        // form表单生产
        String form =  client.pageExecute(alipay_request).getBody();
        return form ;
    }

    /**
     *  功能：支付宝电脑网站PC支付接口(alipay.trade.page.pay )接口
     *
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param subject 订单名称，必填
     * @param total_amount 付款金额，必填
     * @param describe 商品描述，可空
     * @param timeout_express 超时时间 可空  2m
     * @param product_code 销售产品码 必填
     * @return 返回表单
     */
    public String pagePay(String out_trade_no , String subject,String total_amount,String describe,String timeout_express,String product_code ) throws AlipayApiException{
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = getAliPayClient();
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(describe);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipayRequest.setBizModel(model);
        // 设置异步通知地址
        alipayRequest.setNotifyUrl(aliPayConfig.getNotifyUrl());
        // 设置同步地址
        alipayRequest.setReturnUrl(aliPayConfig.getReturnUrl());
        // form表单生产
        String form =  client.pageExecute(alipayRequest).getBody();
        return form ;

    }

    /**
     * SDK调用前需要进行初始化
     * @return
     */
    private AlipayClient getAliPayClient() {
        return new DefaultAlipayClient(
                aliPayConfig.getUrl(),
                aliPayConfig.getAppId(),
                aliPayConfig.getRsaPrivateKey(),
                aliPayConfig.getFormat(),
                aliPayConfig.getCharset(),
                aliPayConfig.getAliPayPublicKey(),
                aliPayConfig.getSignType());
    }


    /**
     * 功能：支付宝alipay.trade.query (统一收单线下交易查询)
     *
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param trade_no  支付宝交易号
     * @return
     */
    public String  tradeQuery(String out_trade_no , String trade_no) throws AlipayApiException{
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        AlipayClient client = getAliPayClient();
        AlipayTradeQueryRequest alipay_request = new AlipayTradeQueryRequest();

        AlipayTradeQueryModel model=new AlipayTradeQueryModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        alipay_request.setBizModel(model);
        AlipayTradeQueryResponse aliPayResponse =client.execute(alipay_request);
        String body = aliPayResponse.getBody();
        return body;
    }

    /**
     * 功能： 支付宝alipay.trade.refund (统一收单交易退款接口)
     *
     * @param out_trade_no 商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
     *                        商户订单号，和支付宝交易号二选一
     * @param trade_no 支付宝交易号，和商户订单号二选一
     * @param refund_amount   退款金额，不能大于订单总金额
     * @param refund_reason  退款的原因说明
     * @param out_request_no  标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     * @return
     */
    public String tradeRefund(String out_trade_no , String trade_no , String refund_amount , String refund_reason,String  out_request_no) throws AlipayApiException{
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        AlipayClient client = getAliPayClient();
        AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();

        AlipayTradeRefundModel model=new AlipayTradeRefundModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        model.setRefundAmount(refund_amount);
        model.setRefundReason(refund_reason);
        model.setOutRequestNo(out_request_no);
        alipay_request.setBizModel(model);
        //调用 获取响应数据
        AlipayTradeRefundResponse aliPayResponse =client.execute(alipay_request);
        return  aliPayResponse.getBody();
    }


    /**
     * 功能： 支付宝alipay.trade.fastpay.refund.query (统一收单交易退款查询)
     *
     * @param out_trade_no 商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
     *                        商户订单号，和支付宝交易号二选一
     * @param trade_no   支付宝交易号，和商户订单号二选一
     * @param out_request_no  请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     * @return
     */
    public String refundQuery(String out_trade_no , String trade_no , String out_request_no) throws AlipayApiException{
        AlipayClient client = getAliPayClient();

        AlipayTradeFastpayRefundQueryRequest alipay_request = new AlipayTradeFastpayRefundQueryRequest();

        AlipayTradeFastpayRefundQueryModel model=new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        model.setOutRequestNo(out_request_no);
        alipay_request.setBizModel(model);

        AlipayTradeFastpayRefundQueryResponse aliPayResponse=client.execute(alipay_request);
        return  aliPayResponse.getBody();
    }

    /**
     *  功能： 支付宝alipay.trade.close (统一收单交易关闭接口)
     *
     * @param out_trade_no 商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
     *                        商户订单号，和支付宝交易号二选一
     * @param trade_no   支付宝交易号，和商户订单号二选一
     * @return
     */
    public String closeTrade(String out_trade_no , String trade_no) throws AlipayApiException{
        AlipayClient client = getAliPayClient();
        AlipayTradeCloseRequest alipay_request=new AlipayTradeCloseRequest();

        AlipayTradeCloseModel model =new AlipayTradeCloseModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        alipay_request.setBizModel(model);

        AlipayTradeCloseResponse aliPayResponse=client.execute(alipay_request);
        return aliPayResponse.getBody();
    }

    /**
     * 功能：支付宝alipay.data.dataservice.bill.downloadurl.query (查询对账单下载地址)
     *
     * @param bill_type 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
     *                    trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
     * @param bill_date 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
     * @return
     */
    public String billDownloadUrlQuery(String bill_type,String bill_date) throws AlipayApiException{
        AlipayClient client = getAliPayClient();

        AlipayDataDataserviceBillDownloadurlQueryRequest alipay_request = new AlipayDataDataserviceBillDownloadurlQueryRequest();

        AlipayDataDataserviceBillDownloadurlQueryModel model =new AlipayDataDataserviceBillDownloadurlQueryModel();
        model.setBillType(bill_type);
        model.setBillDate(bill_date);
        alipay_request.setBizModel(model);

        AlipayDataDataserviceBillDownloadurlQueryResponse alipay_response = client.execute(alipay_request);
        return  alipay_response.getBillDownloadUrl();
    }
}
