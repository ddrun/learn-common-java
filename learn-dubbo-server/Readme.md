  #<dubbo:registry> 注册中心配置
    http://dubbo.incubator.apache.org/books/dubbo-user-book/references/xml/dubbo-registry.html
    address:
        (0)string；必填项；
        (1)<ip:port>或<ip:port,ip:port>       
        (2)注册中心服务器地址，如果地址没有端口号时默认端口为9090;
        (3)同一集群内的多个地址用逗号分隔；
        (4)不同集群的注册中心，需配置多个<dubbo:registry>
    protocol:
        (0)string；非必填；默认缺省值为dubbo
        (1)<protocol>
        (4)注册中心地址协议；
        (4)类型支持：dubbo/http/local
           