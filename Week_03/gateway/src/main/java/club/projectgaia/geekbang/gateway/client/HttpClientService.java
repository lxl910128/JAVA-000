package club.projectgaia.geekbang.gateway.client;

import club.projectgaia.geekbang.gateway.common.util.ResponseUtils;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Component
@Slf4j
public class HttpClientService {

    private CloseableHttpAsyncClient httpclient;
    private ExecutorService proxyService;


    @PostConstruct
    private void init() {
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        this.proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), new ThreadPoolExecutor.CallerRunsPolicy());


        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        this.httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((response, context) -> 6000)
                .build();
        this.httpclient.start();
    }

    public void handler(final ChannelHandlerContext ctx, String url, Boolean isKeeplive) {
        proxyService.submit(() -> doGet(ctx, url, isKeeplive));
    }

    public void doGet(final ChannelHandlerContext ctx, String url, Boolean isKeeplive) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                FullHttpResponse response = null;
                try {
                    // 返回错误
                    response = ResponseUtils.buildSuccess("{\"data\":\"" + EntityUtils.toString(httpResponse.getEntity()) + "\"}");

                } catch (Exception e) {
                    response = ResponseUtils.buildError();
                } finally {
                    if (!isKeeplive) {
                        ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                    } else {
                        response.headers().set(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
                        ctx.write(response);
                    }
                    ctx.flush();
                }

            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
                log.error("请求子服务失败！", e);
                FullHttpResponse response = ResponseUtils.buildError();
                if (!isKeeplive) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
                    ctx.write(response);
                }
                ctx.flush();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

}
