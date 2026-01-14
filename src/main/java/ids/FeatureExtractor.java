
public class FeatureExtractor {

    // SYN / (SYN + SYN-ACK) ratio
    public static double synFloodRatio(Session s) {
        int denom = s.synAckCount;
        return denom == 0 ? s.synCount : (double) s.synCount / denom;
    }

    // соединения в секунду
    public static double connectionsPerSecond(Session s) {
        return s.duration > 0 ? s.packets / s.duration : s.packets;
    }

    // неполное соединение (SYN есть, SYN-ACK нет)
    public static boolean isIncomplete(Session s) {
        return s.synCount > 0 && s.synAckCount == 0;
    }

    // короткая сессия
    public static boolean isShortSession(Session s) {
        return s.duration < 1.0;
    }
}
