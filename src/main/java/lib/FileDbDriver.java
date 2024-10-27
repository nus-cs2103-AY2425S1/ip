package lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.exit;

/**
 * The {@code FileDbDriver} class implements the {@code DbDriverInterface} and provides
 * file-based database functionality for reading and writing data to a file called "db.txt".
 * It ensures that the file exists and handles basic file operations such as reading, writing, and initialization.
 */
public class FileDbDriver implements DbDriverInterface {

    public final String DEFAULT_DB_PATH = "db.txt";

    /**
     * Initializes the database by checking for the existence of the "db.txt" file.
     * If the file doesn't exist, it creates one. The contents of the file are then returned as a string.
     *
     * @return The contents of the "db.txt" file as a string, or an empty string if an error occurs.
     */
    public String init() {
        try {
            Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
            Path dbConfigPath = currentWorkingDirectory.resolve("config.txt");
            if (!Files.exists(dbConfigPath)) {
                Files.createFile(dbConfigPath);
                Files.write(dbConfigPath, DEFAULT_DB_PATH.getBytes());
            }
            String db_path = Files.readString(dbConfigPath);
            Path dbFilePath = currentWorkingDirectory.resolve(db_path);
            // Check if the file exists
            if (!Files.exists(dbFilePath)) {
                Files.createFile(dbFilePath);
            }
            return Files.readString(dbFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            exit(1);
        } catch (Exception e) {
            exit(1);
        }
        return "";
    }

    /**
     * Saves the given string to the "db.txt" file.
     * If the file doesn't exist, it creates one before writing to it.
     *
     * @param rawString The string content to be saved to the file.
     */
    public void save(String rawString) {
        try {
            Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
            Path dbConfigPath = currentWorkingDirectory.resolve("config.txt");
            if (!Files.exists(dbConfigPath)) {
                Files.createFile(dbConfigPath);
                Files.write(dbConfigPath, DEFAULT_DB_PATH.getBytes());
            }
            String db_path = Files.readString(dbConfigPath);
            Path dbFilePath = currentWorkingDirectory.resolve(db_path);
            // Check if the file exists
            if (!Files.exists(dbFilePath)) {
                Files.createFile(dbFilePath);
            }
            Files.write(dbFilePath, rawString.getBytes());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the contents of the "db.txt" file.
     * If the file doesn't exist, it returns an empty string.
     *
     * @return The contents of the "db.txt" file as a string, or an empty string if the file doesn't exist or an error occurs.
     */
    public String read() {
        try {
            Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
            Path dbConfigPath = currentWorkingDirectory.resolve("config.txt");
            if (!Files.exists(dbConfigPath)) {
                Files.createFile(dbConfigPath);
                Files.write(dbConfigPath, DEFAULT_DB_PATH.getBytes());
            }
            String db_path = Files.readString(dbConfigPath);
            Path dbFilePath = currentWorkingDirectory.resolve(db_path);
            if (!Files.exists(dbFilePath)) {
                return "";
            }
            return Files.readString(dbFilePath);
        } catch (IOException e) {
            return "";
        }
    }
}
