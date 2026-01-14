import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            File csvFile = findLatestCsv("data");

            if (csvFile == null) {
                System.out.println("CSV files not found");
                return;
            }

            System.out.println("Using file: " + csvFile.getName());

            List<Session> sessions =
                    CsvReader.readSessions(csvFile.getPath());

            for (Session s : sessions) {
                AttackClassifier.classify(sessions);
                System.out.println(
                        s.srcIP + " -> " + s.dstIP +
                        " [" + s.dstPort + "] : " + s.attackType
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File findLatestCsv(String dirPath) {
        File dir = new File(dirPath);

        File[] files = dir.listFiles(
                (d, name) -> name.toLowerCase().endsWith(".csv")
        );

        if (files == null || files.length == 0) {
            return null;
        }

        File latest = files[0];
        for (File f : files) {
            if (f.lastModified() > latest.lastModified()) {
                latest = f;
            }
        }
        return latest;
    }
}