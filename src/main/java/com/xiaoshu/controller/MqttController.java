package com.xiaoshu.controller;

import com.ubtechinc.sevice.MqttGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : function description
 * ---------------------------------
 * @Author : deane
 * @Date : Create in 2020/3/2 19:18
 * <p>
 * Copyright (C)2013-2020 小树盛凯科技 All rights reserved.
 */
@RestController
@RequestMapping(value = "/mqtt")
@Slf4j
public class MqttController {
    /**
     * mqtt message send handler
     */
    private MqttGateway mqttGateway;

    @Autowired(required = false)
    public void setMqttGateway(MqttGateway mqttGateway) {
        this.mqttGateway = mqttGateway;
    }

    /**
     * 发送MQTT消息信息；
     * @param content
     * @return
     */
    @RequestMapping(value = "/send")
    public String  sendMessage(String content){
        log.info("send Message ...");
        mqttGateway.sendToMqtt(content.getBytes(),"/test/123456");
        return "success";
    }
}
