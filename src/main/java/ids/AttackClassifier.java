
import java.util.*;

public class AttackClassifier {

    public static void classify(List<Session> sessions) {

        Map<String, Integer> connectionCounter = new HashMap<>();

        for (Session s : sessions) {

            String key = s.srcIP + ":" + s.dstPort;
            connectionCounter.put(key,
                    connectionCounter.getOrDefault(key, 0) + 1);

            // DoS SYN Flood
            if (s.synCount > 3 &&
                FeatureExtractor.synRatio(s) > 0.7 &&
                FeatureExtractor.isIncomplete(s)) {

                s.attackType = AttackType.DOS_SYN_FLOOD;
            }

            // Brute Force
            else if (connectionCounter.get(key) > 5 &&
                     FeatureExtractor.isShortSession(s)) {

                s.attackType = AttackType.BRUTE_FORCE;
            }
        }
    }
}