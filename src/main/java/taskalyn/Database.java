package taskalyn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Manages reading from and writing to the database file.
 */
public class Database {
    private static final String DIRECTORY = "./data";
    private static final String FILE_NAME = "taskalyn.txt";
    private static final Path FILE_PATH = Paths.get(DIRECTORY, FILE_NAME);

    /**
     * Constructs the database object.
     */
    public Database() {
        try {
            List<String> textLines = Files.readAllLines(FILE_PATH);
            if (Files.notExists(FILE_PATH)) {
                Files.createDirectory(Paths.get(DIRECTORY));
                Files.createFile(FILE_PATH);
            }
        } catch (NoSuchFileException e) {
            this.createDatabase();
        } catch (IOException e) {
            System.out.println("Error getting database: " + e.getMessage());
        }
    }

    /**
     * Creates database file at given file path.
     */
    public void createDatabase() {
        try {
            Files.createDirectories(FILE_PATH.getParent());
            Files.createFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error creating database: " + e.getMessage());
        }
    }

    /**
     * Writes a list of task data to the database file.
     *
     * @param lines List of task data to write.
     */
    public void writeToDatabase(List<String> lines) {
        try {
            Files.write(FILE_PATH, lines);
        } catch (IOException e) {
            System.out.println("Error writing database: " + e.getMessage());
        }
    }

    /**
     * Reads the task data from the database file.
     *
     * @return List of task data.
     * @throws IOException If an error happens while reading.
     */
    public List<String> readFromDatabase() throws IOException {
        return Files.readAllLines(FILE_PATH);
    }

    /**
     * Returns the total number of tasks in the database.
     *
     * @return Number of tasks in database.
     * @throws IOException If an error happens while reading.
     */
    public int getDatabaseSize() throws IOException {
        List<String> textLines = readFromDatabase();
        return textLines.size();
    }
}
