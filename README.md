# aliPay_tool
支付宝支付中间键简化开发流程嵌入配置式              
  
手机网站支付接口  
  webPay(String out_trade_no , String subject,String total_amount,String describe,String timeout_express,String product_code )
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
     *
     /
    
  d电脑网站支付接口   
  pagePay(String out_trade_no , String subject,String total_amount,String describe,String timeout_express,String product_code )
   / **
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
