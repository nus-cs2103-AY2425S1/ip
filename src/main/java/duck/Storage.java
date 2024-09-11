package duck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {
    private final File filePath;
    private static final String COMMA_DELIMITER = ",";

    private List<List<String>> records = new ArrayList<>();

    public Storage(File filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from CSV and returns a list of lists.
     * Each list corresponds to a row in the CSV file.
     *
     * @return List of lists
     * @throws IOException
     */
    public List<List<String>> getRecords() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
            br.close();
            return records;
        }
    }

    /**
     * Updates CSV with records as a list of lists.
     * Each list corresponds to a row in the CSV file.
     *
     * @throws IOException
     */
    public void updateRecords(List<List<String>> records) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (List<String> record : records) {
                bw.write(String.join(",", record) + "\n");
            }
            bw.close();
        }
    }

    /**
     * Updates CSV with records as a list of strings.
     * Each String corresponds to a row in the CSV file.
     *
     * @param records
     * @throws IOException
     */
    public void updateRecordsWithStrings(List<String> records) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String record : records) {
                bw.write(record + "\n");
            }
            bw.close();
        }
    }
}
