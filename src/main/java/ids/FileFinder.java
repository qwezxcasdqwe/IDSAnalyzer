
import java.io.File;

public class FileFinder {

    public static File findLatestCsv() {

        File dataDir = new File("data");

        if (!dataDir.exists() || !dataDir.isDirectory()) {
            throw new RuntimeException("Папка data не найдена");
        }

        File[] files = dataDir.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".csv")
        );

        if (files == null || files.length == 0) {
            throw new RuntimeException("В папке data нет CSV файлов");
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