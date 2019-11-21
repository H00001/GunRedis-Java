package top.gunplan.redis.java.data;

import top.gunplan.netty.protocol.GunNetInbound;

public class StandRequest implements GunNetInbound {
    String c;
    String k;
    String v;

    public StandRequest() {
    }

    public StandRequest(String c, String k, String v) {
        this.c = c;
        this.k = k;
        this.v = v;
    }

    public String getC() {
        return c;
    }


    public String getK() {
        return k;
    }


    public String getV() {
        return v;
    }


    @Override
    public boolean unSerialize(byte[] bytes) {
        String[] s = new String(bytes).split(" ");
        this.c = s[0];
        this.k = s[1];
        this.v = s[2];
        return true;
    }
}
