# activemq1

## API

### Endpoint Summary
/message/send

## H2
[H2 Console](http://localhost:18190/develop/h2_console/)
url=jdbc:h2:tcp://localhost:18290/~/h2/activemq1-msg;USER=admin;PASSWORD=admin  

## Curl examples
```
curl -H "Content-Type: application/json" \
    -d '{ "sender":"me", "recipient":"you", "body":"hello" }' \
    http://localhost:18190/message/send
```

## Links
https://dzone.com/articles/using-jms-in-spring-boot-1
https://liuzy163.wordpress.com/2016/08/03/use-springboot-to-deploy-activemq-broker-to-tomcat/
https://liuzy163.wordpress.com/2016/08/04/use-springboot-to-deploy-activemq-broker-to-tomcat-22/
https://liuzy163.wordpress.com/2016/09/26/use-springboot-to-deploy-activemq-broker-to-tomcat-33/
https://activemq.apache.org/artemis/docs/1.0.0/ha.html
http://activemq.apache.org/masterslave.html
https://medium.com/@joeclever/using-multiple-datasources-with-spring-boot-and-spring-data-6430b00c02e7
http://activemq.apache.org/how-do-i-use-ssl.html
https://stackoverflow.com/questions/34334629/spring-boot-ssl-tcpclient-stompbrokerrelaymessagehandler-activemq-undertow
https://www.ivankrizsan.se/2016/08/15/embedded-activemq-with-ssl/
https://dzone.com/articles/jdbc-master-slave-persistence-setup-with-activemq