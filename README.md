# mqtt-spring-boot-starter-example

#### 介绍
   示例， 介绍如何快速集成 mqtt-spring-boot-starter， 

#### application.yml
```
server:
  port: 8888
  servlet:
    context-path: /example

spring:
  mqtt:
    url: tcp://127.0.0.1:1883
    userName: admin
    password: 123456
    ## 接收客户端和订阅topic
    inbound:
      clientId: iot-thinker-inbound
      topics: /test/123456
    ## 发送客户端和发送topic
    outbound:
      clientId: iot-thinker-outbound
      topics: /test/123456

```



#### MqttController

···

@RestController  
@RequestMapping(value = "/mqtt")  
@Slf4j  
public class MQTTController {

    @Autowired
    private MqttGateway mqttGateway;


    @RequestMapping(value = "/send")
    public String  sendMessage(){
        log.info("send Message ...");
        mqttGateway.sendToMqtt("The test Message".getBytes(),"/test/123456");
        return "success";
    }
}

···


#### 参与贡献

···

@Component(value = "messageHandler")  
public class ReceiveMessagehandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) {
        System.out.println("MyMessageHandler ====>  收到订阅消息: ==>" + message);
        String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
        System.out.println("MyMessageHandler ====> 消息主题：==>" + topic);
        Object payLoad = message.getPayload();

        // 如果不设置转换器这里强转byte[]会报错
        byte[] data = (byte[]) payLoad;
        System.out.println("MyMessageHandler====> Message is : ==> "  + new String(data));
    }
}

···


