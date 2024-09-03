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

    private final Path filePath;

    /**
     * Constructs a new storage.
     *
     * @param fileName Name of the file storing the records.
     * @throws IOException If there is input or output failure with the file.
     */
    Storage(String fileName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        Path folder = Paths.get(currentDirectory, "data");
        Path filePath = Paths.get(folder.toString(), fileName);

        if (Files.notExists(folder)) {
            Files.createDirectory(folder);
        }

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
    public ArrayList<String> retrieve() throws IOException {
        ArrayList<String> records = new ArrayList<>();
        String record;

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(
                        filePath.toFile()
                )
        );

        while ((record = bufferedReader.readLine()) != null) {
            records.add(record);
        }

        return records;
    }

    /**
     * Saves the list of records.
     *
     * @param records Records that are in a string representation.
     * @return If the records were save to the storage.
     */
    public boolean save(ArrayList<String> records) {
        try {
            FileWriter fileWriter = new FileWriter(filePath.toFile());
            for (String record : records) {
                fileWriter.write(record + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
