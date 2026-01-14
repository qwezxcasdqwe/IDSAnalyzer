public class FeatureExtractor {

    public static double synRatio(Session s) {
        return (s.packets > 0) ? (double) s.synCount / s.packets : 0;
    }

    public static boolean isShortSession(Session s) {
        return s.duration < 1.0;
    }

    public static boolean isIncomplete(Session s) {
        return s.finCount == 0 && s.rstCount == 0;
    }
}