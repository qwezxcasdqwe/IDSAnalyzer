public class Session {
    public String srcIp;
    public String dstIp;
    public int srcPort;
    public int dstPort;
    public int packets;
    public long bytes;
    public String protocol;
    public double duration;

    public int synCount;
    public int synAckCount;

    public AttackType attackType = AttackType.NORMAL;
}