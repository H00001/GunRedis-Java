package top.gunplan.redis.java;

import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunNettySystemService;
import top.gunplan.netty.GunServerStateManager;
import top.gunplan.netty.filter.GunNettyConnFilter;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.channel.GunNettyChildChannel;
import top.gunplan.netty.impl.checker.GunOutboundChecker;
import top.gunplan.netty.impl.property.GunGetPropertyFromNet;
import top.gunplan.netty.observe.GunNettyBaseObserve;
import top.gunplan.redis.java.handle.RedisStdHandle;
import top.gunplan.redis.java.handle.RedisStdInboundFilter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class BootService {

    public static void main(String[] args) {

        GunNettySystemService.PROPERTY_MANAGER.setStrategy(new GunGetPropertyFromNet("https://p.gunplan.top/static/redis.html"));
        GunBootServer server = GunBootServerFactory.newInstance();

        server.useStealMode(false).
                setExecutors(10, 10)
                .onHasChannel(ch -> ch
                        .addDataFilter(new GunNettyStdFirstFilter())
                        .addConnFilter(new GunNettyConnFilter() {
                            @Override
                            public DealResult doConnFilter(GunNettyChildChannel<SocketChannel> gunNettyChildChannel) {
                                return DealResult.NEXT;
                            }

                            @Override
                            public DealResult doOutputFilter(GunOutboundChecker gunOutboundChecker) throws GunChannelException {
                                try {
                                    gunOutboundChecker.channel().channel().write(ByteBuffer.wrap("PING".getBytes()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return DealResult.NEXT;
                            }
                        })
                        //  .addDataFilter(new StressTestOutputFilter())
                        .addDataFilter(new RedisStdInboundFilter())
                        .setHandle(new RedisStdHandle())
                );
        try {
            server.setSyncType(true);
            int val = server.sync();
            if ((val | GunServerStateManager.GunNettyWorkState.BOOT_ERROR_2.state) != 0) {
                System.err.println("BOOT FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
