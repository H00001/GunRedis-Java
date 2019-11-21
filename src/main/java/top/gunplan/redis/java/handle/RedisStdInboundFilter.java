package top.gunplan.redis.java.handle;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.filter.GunNettyInboundFilter;
import top.gunplan.netty.impl.checker.GunInboundChecker;
import top.gunplan.redis.java.data.StandRequest;

import java.util.Arrays;

public class RedisStdInboundFilter implements GunNettyInboundFilter {
    @Override
    public DealResult doInputFilter(GunInboundChecker gunInboundChecker) throws GunChannelException {
        gunInboundChecker.tranToObject(StandRequest.class);
        return DealResult.NEXT;
    }
}
