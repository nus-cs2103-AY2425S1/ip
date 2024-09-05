package lib;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

import static java.lang.System.exit;

public class FileDbDriver implements DbDriverInterface {

    public String init() {
        try {
            Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
            Path parentDirectory = currentWorkingDirectory.getParent();
            Path dbFilePath = parentDirectory.resolve("db.txt");
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

    public void save(String rawString) {
        try {
            Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
            Path dbFilePath = currentWorkingDirectory.resolve("db.txt");
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

    public String read() {
        try {
            Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
            Path dbFilePath = currentWorkingDirectory.resolve("db.txt");
            if (!Files.exists(dbFilePath)) {
                return "";
            }
            return Files.readString(dbFilePath);
        } catch (IOException e) {
            return "";
        }
    }

}
