package com.github.liubt.mud;


import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> { //1
    
    private final String WS_URI = "/ws";


    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (WS_URI.equalsIgnoreCase(request.uri())) {
            ctx.fireChannelRead(request.retain());                  //2
        } else {
            if (HttpUtil.is100ContinueExpected(request)) {
                send100Continue(ctx);                               //3
            }


            File staticFile;
            try {
                URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
                String path = location.toURI() + request.uri();
                path = !path.contains("file:") ? path : path.substring(5);
                staticFile = new File(path);
                if(!staticFile.exists()) {
                    HttpResponse response = new DefaultHttpResponse(
                            request.protocolVersion(), HttpResponseStatus.NOT_FOUND);
                    ctx.write(response);                    //6
                    ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
                    // FixMe: 浏览器不显示404
                    return;
                }
            } catch (URISyntaxException e) {
                throw new IllegalStateException("Unable to locate file", e);
            }

            RandomAccessFile file = new RandomAccessFile(staticFile, "r");

            HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

            boolean keepAlive = HttpUtil.isKeepAlive(request);

            if (keepAlive) {                                        //5
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response);                    //6

            if (ctx.pipeline().get(SslHandler.class) == null) {     //7
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);           //8
            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);        //9
            }

            file.close();
        }
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}