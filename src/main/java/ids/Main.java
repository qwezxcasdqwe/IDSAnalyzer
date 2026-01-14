import java.io.File;
import java.util.List;



public class Main {

    public static void main(String[] args) throws Exception {

        File csv = FileFinder.findLatestCsv();
        System.out.println("Используется файл: " + csv.getName());

        List<Session> sessions = CsvReader.read(csv);
        AttackClassifier.classify(sessions);
        ReportWriter.write(sessions);

        System.out.println("Анализ завершён. Отчет: ids_report.csv");
    }
}