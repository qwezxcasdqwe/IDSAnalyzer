import java.io.*;
import java.util.*;


import java.io.*;
import java.util.*;

public class CsvReader {

    public static List<Session> read(File file) throws Exception {
        List<Session> sessions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String header = br.readLine(); // пропускаем заголовок

        String line;
        while ((line = br.readLine()) != null) {
            String[] c = line.split(",", -1); // -1 чтобы сохранять пустые поля

            if (c.length < 24) continue; // пропускаем некорректные строки

            Session s = new Session();

            long startTime = parseLongSafe(c[0]);
            long stopTime = parseLongSafe(c[1]);
            s.duration = (stopTime - startTime) / 1000.0;

            s.srcIp = c[2];
            s.dstIp = c[5];
            s.srcPort = parseIntSafe(c[4]);
            s.dstPort = parseIntSafe(c[7]);

            s.packets = parseIntSafe(c[8]);
            s.bytes = parseLongSafe(c[10]);

            s.protocol = c[12];

            s.synCount = parseIntSafe(c[22]);
            s.synAckCount = parseIntSafe(c[23]);

            sessions.add(s);
        }

        br.close();
        return sessions;
    }

    private static int parseIntSafe(String s) {
        if (s == null || s.isEmpty()) return 0;
        return Integer.parseInt(s);
    }

    private static long parseLongSafe(String s) {
        if (s == null || s.isEmpty()) return 0L;
        return Long.parseLong(s);
    }

    private static double parseDoubleSafe(String s) {
        if (s == null || s.isEmpty()) return 0.0;
        return Double.parseDouble(s);
    }
}