package top.gunplan.redis.java.data;

import top.gunplan.netty.protocol.GunNetOutbound;


public class StandResponse implements GunNetOutbound {
    public static final StandResponse SEND = new StandResponse();

    @Override
    public byte[] serialize() {
        return new byte[]{'O', 'K'};
    }
}
