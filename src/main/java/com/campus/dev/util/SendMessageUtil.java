package com.campus.dev.util;

import com.campus.dev.cache.UserIdentifyCodeManage;
import com.campus.dev.config.SMsProperties;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessageUtil {

//GeneratorSecretUtil.getSixValidationCode() + "为您的登录验证码，请于" + 10 + "分钟内填写。如非本人操作，请忽略本短信。",

    @Autowired
    UserIdentifyCodeManage cache;

    @Autowired
    private SMsProperties sMsProperties;


    public void sendMsgByTxPlatform(String phone) throws Exception {

        SmsSingleSender sSender = new SmsSingleSender(Integer.valueOf(sMsProperties.appId), sMsProperties.appKey);
        String code = GeneratorSecretUtil.getSixValidationCode();
        String[] params = {code, "2"};
        //第一个参数0表示普通短信,1表示营销短信
        SmsSingleSenderResult result = sSender.sendWithParam("86",
                phone,
                Integer.valueOf(sMsProperties.templateId),
                 params, sMsProperties.smsSign,"","");

        if (result.result != 0) {
            throw new Exception("send phone validateCode is error" + result.errMsg);
        }
        cache.updateUserIdentifyCode(phone, code);
    }

}
