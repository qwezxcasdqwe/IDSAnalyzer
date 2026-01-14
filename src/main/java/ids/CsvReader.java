import java.io.*;
import java.util.*;

public class CsvReader {

    public static List<Session> readSessions(String filePath) throws Exception {
        List<Session> sessions = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String headerLine = br.readLine();

        if (headerLine == null) return sessions;

        String[] headers = headerLine.split(",");

        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            index.put(headers[i].trim(), i);
        }

        String line;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(",", -1);

            String srcIP = row[index.get("Src IP")];
            String dstIP = row[index.get("Dst IP")];

            int srcPort = parseInt(row[index.get("Src Port")]);
            int dstPort = parseInt(row[index.get("Dst Port")]);

            String protocol = row[index.get("Protocols")];

            int packets = parseInt(row[index.get("Packets")]);

            int synCount = parseInt(row[index.get("TCP Flag SYN")]);
            int finCount = 0;
            int rstCount = 0;

            double duration = calcDuration(
                    row[index.get("Start Time")],
                    row[index.get("Stop Time")]
            );

            Session s = new Session(
                    srcIP, dstIP, srcPort, dstPort,
                    protocol, packets, synCount,
                    finCount, rstCount, duration
            );

            sessions.add(s);
        }

        br.close();
        return sessions;
    }

    private static int parseInt(String s) {
        if (s == null || s.isEmpty()) return 0;
        return Integer.parseInt(s);
    }

    private static double calcDuration(String start, String stop) {
        try {
            long s = Long.parseLong(start);
            long e = Long.parseLong(stop);
            return (e - s) / 1000.0;
        } catch (Exception e) {
            return 0;
        }
    }
}