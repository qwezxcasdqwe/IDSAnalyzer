import java.io.*;
import java.util.*;

public class ReportWriter {

    public static void write(List<Session> sessions) throws Exception {
        File out = new File("ids_report.csv");
        PrintWriter pw = new PrintWriter(out);

        pw.println("srcIp,dstIp,attackType,synRatio,connectionsPerSecond");

        for (Session s : sessions) {
            pw.printf(
                "%s,%s,%s,%.2f,%.2f%n",
                s.srcIp,
                s.dstIp,
                s.attackType,
                FeatureExtractor.synFloodRatio(s),
                FeatureExtractor.connectionsPerSecond(s)
            );
        }
        pw.close();
    }
}