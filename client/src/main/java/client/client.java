package client;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.nio.charset.StandardCharsets;


public class client {

    public static String Msg;
    public static void main(String[] args) {

        var eventLoopGroup = new NioEventLoopGroup(4);


        var bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ClientChannelInitializer());

        try {
            int i = 1;
            while (i<=3){
                switch (i)
                {case 1: Msg = "5:hello"; break;
                    case 2: Msg = "4:cool";  break;
                    case 3: Msg = "6:haha";  break;
                    default: Msg = null;  break;
                };
                var channelFuture = bootstrap.connect("localhost", 8081).sync();
                channelFuture.channel().closeFuture().sync();
                //   channelFuture.closeFuture();
                //      System.out.println(i);
                //System.out.println(textMsg.getMsg());

                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}