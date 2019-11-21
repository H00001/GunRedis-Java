package top.gunplan.redis.java.handle;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyChildrenHandle;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.filter.GunNettyFilter;
import top.gunplan.netty.impl.channel.GunNettyChildChannel;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutbound;
import top.gunplan.redis.java.data.StandResponse;

import java.net.SocketAddress;

public class RedisStdHandle implements GunNettyChildrenHandle {

    @Override
    public GunNetOutbound dealDataEvent(GunNetInbound gunNetInbound) throws GunException {
        return StandResponse.SEND;
    }

    @Override
    public void dealCloseEvent(SocketAddress socketAddress) {

    }

    @Override
    public GunNettyFilter.DealResult dealExceptionEvent(GunChannelException e) {
        return null;
    }
}
