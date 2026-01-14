import java.util.*;

public class Main {

    public static void main(String[] args) {

        try {
            List<Session> sessions =
                    CsvReader.readSessions("sessions.csv");

            AttackClassifier.classify(sessions);

            for (Session s : sessions) {
                System.out.printf(
                        "%s -> %s:%d | %s | %s%n",
                        s.srcIP,
                        s.dstIP,
                        s.dstPort,
                        s.protocol,
                        s.attackType
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
