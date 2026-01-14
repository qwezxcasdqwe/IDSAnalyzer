public class RuleEngine {

    public static AttackType classify(Session s) {

        double synRatio = FeatureExtractor.synFloodRatio(s);
        double cps = FeatureExtractor.connectionsPerSecond(s);
        boolean incomplete = FeatureExtractor.isIncomplete(s);

        // DOS / SYN flood
        if (
            cps > 500 &&
            incomplete &&
            (synRatio > 3 || s.packets > 10)
        ) {
            return AttackType.DOS;
        }

        // Brute Force
        if (
            cps > 20 &&
            s.duration > 5 &&
            s.packets > 5 &&
            (s.dstPort == 22 || s.dstPort == 21 || s.dstPort == 80)
        ) {
            return AttackType.BRUTE_FORCE;
        }

        // Port Scan
        if (
            s.packets <= 3 &&
            FeatureExtractor.isShortSession(s) &&
            cps < 100
        ) {
            return AttackType.PORT_SCAN;
        }

        // Подозрительное
        if (synRatio > 3 || cps > 30) {
            return AttackType.SUSPICIOUS;
        }

        return AttackType.NORMAL;
    }
}