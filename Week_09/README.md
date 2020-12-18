# 第一题
## 题目
改造自定义RPC的程序，提交到github
## 主要工作
1. 在服务端增加`RpcfxResolver`的实现[ReflexResolver](rpc01/rpcfx-demo-provider/src/main/java/io/kimmking/rpcfx/demo/provider/ReflexResolver.java)，该从使用反射的方式查找`Service`与之前从IOC容器中取不同的是每次都是新建。
2. 创建[RpcfxException](rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/error/RpcfxException.java)统一异常类型，该类型继承`RuntimeException`，好处是业务代码不用捕获处理该类异常，如要处理可在处理体系中统一增加切面统一处理。
3. 在服务端，将`Result`改为用`Xstream`编码。
4. 使用枚举实现单例工具类[NettyHttpClient](rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/util/NettyHttpClient.java)，该类使用netty实现了httpClient，注意netty发送POST请求务必在请求头中增加`content-length`，否则spring服务端无法获取request body。
5. 在客户端，老方式是动态代理获取获取`Service`以及将调用`service`方法改为`RPC`调用。新的方式是使用AOP [ClientAOP](rpc01/rpcfx-demo-consumer/src/main/java/io/kimmking/rpcfx/demo/consumer/aop/ClientAOP.java)截取所有对`Service`方法的调用，直接不请求IOC容器内Bean的方法，而是直接改为`rpc`调用。
6. 在5方法实现过程中，为了能使用AOP还是需要在Spring容器中放入各种`Service`的Bean，但在client端我们不想也不关系这些Service的实现。所以我们使用动态字节码增强技术，在client端实现实现所有`Service`接口，实现的方式就是所有方法直接返回null。因为在AOP中我们并不会调用这些方法，仅仅是需要有个实现的类，方便放入IOC容器中。[ProxyHandler](rpc01/rpcfx-demo-consumer/src/main/java/io/kimmking/rpcfx/demo/consumer/bean/ProxyHandler.java)，实现了使用字节码增强技术动态的实现一个接口并实例化这个类。

