import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskManager {
    private static final Path taskFile = Paths.get("./task/task.txt");

    public static void initialize() {
        try {
            Files.createDirectories(taskFile.getParent());
            if (!Files.exists(taskFile)) {
                Files.createFile(taskFile);
            }
        } catch (IOException e) {
            System.out.println("Error when creating task file...");
        }
    }
}