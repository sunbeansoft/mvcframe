package com.baidu.nuomi.crm.ddrm.server;

import com.baidu.nuomi.crm.ddrm.server.model.DDRMResult;
import com.baidu.nuomi.crm.ddrm.server.model.DDRMServiceResult;
import com.baidu.nuomi.crm.ddrm.server.service.DDRMService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DDRMTransferServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(DDRMTransferServerHandler.class.getName());

    @Autowired
    private DDRMService ddrmService ;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("receive from client:" + msg);
        DDRMServiceResult message = ddrmService.operateClientRequest(msg, ctx.channel());
        if (message.isSuccess() && message.isNeedWrite()) {
            ctx.writeAndFlush(message.getResult());
            new ScheduledThreadPoolExecutor(1).scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    DDRMResult message = ddrmService.queryDomainProperties("crm");
                    ddrmService.pushPropertiesToDomain("crm", message);
                }
            }, 10000, 5000, TimeUnit.MILLISECONDS);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.log(Level.WARNING, "Unexpected exception from downstream.",
                cause);
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (!GlobalSession.dropChannel(ctx.channel())) {
            logger.log(Level.WARNING, "删除失败");
        }
        DDRMServiceResult message = ddrmService.dropChannel(ctx.channel());
        if (message.isSuccess() && message.isNeedWrite()) {
            ctx.writeAndFlush(message);
        }
        super.channelInactive(ctx);
    }

}