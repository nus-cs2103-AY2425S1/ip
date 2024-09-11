package easton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents the storage of the program.
 */
public class Storage {

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String STORAGE_DIRECTORY = "data";
    private final Path filePath;

    /**
     * Constructs a new storage.
     *
     * @param fileName Name of the file storing the records.
     * @throws IOException If there is input or output failure with the file.
     */
    Storage(String fileName) throws IOException {
        assert fileName != null : "fileName should not be null";
        assert !fileName.isBlank() : "fileName should not be blank";

        Path folder = Paths.get(CURRENT_DIRECTORY, STORAGE_DIRECTORY);
        if (Files.notExists(folder)) {
            Files.createDirectory(folder);
        }

        Path filePath = Paths.get(folder.toString(), fileName);
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }

        this.filePath = filePath;
    }

    /**
     * Retrieves the list of records from the storage.
     *
     * @return A list of string representation of the records.
     * @throws IOException If there is input or output failure with the file.
     */
    public ArrayList<String> retrieveRecords() throws IOException {
        FileReader fileReader = new FileReader(filePath.toFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<String> records = new ArrayList<>();
        String record;
        while ((record = bufferedReader.readLine()) != null) {
            records.add(record);
        }

        return records;
    }

    /**
     * Saves the list of records.
     *
     * @param records Records that are in a string representation.
     */
    public void saveRecords(ArrayList<String> records) {
        try {
            FileWriter fileWriter = new FileWriter(filePath.toFile());
            for (String record : records) {
                fileWriter.write(record + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            // No Alternatives to IOException Thrown
        }
    }
}
