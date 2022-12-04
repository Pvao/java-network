package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.charset.StandardCharsets;

import static client.client.Msg;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ClientHandler.class);

    private ByteBuf messageBuf;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        messageBuf = ctx.alloc().buffer(10);
        messageBuf.writeBytes(Msg.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(messageBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("Messege: {}", ((ByteBuf)msg).toString(CharsetUtil.UTF_8));
        messageBuf.clear();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

