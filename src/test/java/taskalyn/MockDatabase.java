package taskalyn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Creates a MockDatabase that uses a different database file from the main Taskalyn application.
 */
public class MockDatabase extends Database {
    private static final String DIRECTORY = "./data";
    private static final String FILE_NAME = "taskalyn_test.txt";
    private static final Path FILE_PATH = Paths.get(DIRECTORY, FILE_NAME);

    /**
     * Constructs a MockDatabase and creates a new database file.
     */
    public MockDatabase() {
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
     * Creates a new database file at the given file path.
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
     * Writes task data to the database file.
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
     * Reads task data from the database file.
     *
     * @return List of task data.
     * @throws IOException If an I/O error occurs during reading.
     */
    public List<String> readFromDatabase() throws IOException {
        return Files.readAllLines(FILE_PATH);
    }

    /**
     * Returns the total number of tasks in the database file.
     *
     * @return Total number of tasks.
     * @throws IOException If an I/O error occurs during reading.
     */
    public int getDatabaseSize() throws IOException {
        List<String> textLines = readFromDatabase();
        return textLines.size();
    }

    /**
     * Deletes the database.
     * This method is used before each unit test.
     *
     * @throws IOException If an I/O error occurs during execution.
     */
    public void clearDatabase() throws IOException {
        Files.deleteIfExists(FILE_PATH);
    }
}
