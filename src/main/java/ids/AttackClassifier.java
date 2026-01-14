
import java.util.*;

import java.util.List;

public class AttackClassifier {

    public static void classify(List<Session> sessions) {
        for (Session s : sessions) {
            s.attackType = RuleEngine.classify(s);
        }
    }
}