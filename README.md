# activemq1

## API

### Endpoint Summary
/message/send

## H2
[H2 Console](http://localhost:18190/develop/h2_console/)
url=jdbc:h2:~/h2/activemq1-msg  
user admin  
password admin  

## Curl examples
```
curl -H "Content-Type: application/json" \
    -d '{ "sender":"me", "recipient":"you", "body":"hello" }' \
    http://localhost:18190/message/send
```

## Links
https://dzone.com/articles/using-jms-in-spring-boot-1
https://liuzy163.wordpress.com/2016/09/26/use-springboot-to-deploy-activemq-broker-to-tomcat-33/comment-page-1/
