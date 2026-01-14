import java.io.*;
import java.util.*;

public class CsvReader {

    public static List<Session> readSessions(String filePath) throws IOException {
        List<Session> sessions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                Session session = new Session(
                        parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]),
                        parts[4],
                        Integer.parseInt(parts[5]),
                        Integer.parseInt(parts[6]),
                        Integer.parseInt(parts[7]),
                        Integer.parseInt(parts[8]),
                        Double.parseDouble(parts[9])
                );

                sessions.add(session);
            }
        }
        return sessions;
    }
}