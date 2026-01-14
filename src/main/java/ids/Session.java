public class Session {
    public String srcIP;
    public String dstIP;
    public int srcPort;
    public int dstPort;
    public String protocol;

    public int packets;
    public int synCount;
    public int finCount;
    public int rstCount;
    public double duration;

    public AttackType attackType; // default значение можно установить в конструкторе

    public Session(String srcIP, String dstIP, int srcPort, int dstPort,
                   String protocol, int packets, int synCount,
                   int finCount, int rstCount, double duration) {

        this.srcIP = srcIP;
        this.dstIP = dstIP;
        this.srcPort = srcPort;
        this.dstPort = dstPort;
        this.protocol = protocol;
        this.packets = packets;
        this.synCount = synCount;
        this.finCount = finCount;
        this.rstCount = rstCount;
        this.duration = duration;

        this.attackType = AttackType.NORMAL;
    }
}