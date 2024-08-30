import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A class to manage task data stored in a file.
 * Provides functionality to read from and update the task data file.
 */
public class DataManager {
    /**
     * Path to the file where task data is stored.
     */
    private static final Path DATA_FILE_PATH = Path.of("data/rapgod.txt");

    /**
     * Regular expression pattern to validate the format of task entries.
     * The format includes an index, task type, completion status, and optional details.
     */
    private static final Pattern TASK_PATTERN = Pattern.compile(
            "^\\d+\\. \\[[TDE]\\] \\[[ X]\\] .*(\\(from: .+?\\)|\\(to: .+?\\)|\\(by: .+?\\))?$"
    );

    /**
     * Reads task data from the file and returns it as a list of {@link Task} objects.
     * If the file does not exist, it creates an empty file.
     * Validates each line against the expected format and adds valid tasks to the list.
     *
     * @return A list of {@link Task} objects read from the file.
     */
    public static List<Task> readMemory() {
        List<Task> list = new ArrayList<>();
        try {
            if (Files.exists(DATA_FILE_PATH)) {
                List<String> lines = Files.readAllLines(DATA_FILE_PATH);
                for (String line : lines) {
                    if (TASK_PATTERN.matcher(line).matches()) {
                        Task task = Task.fromString(line);
                        list.add(task);
                    } else {
                        System.err.println("Corrupted line found ---> " + line);
                    }
                }
            } else {
                // Create an empty file if it does not exist
                Files.createDirectories(DATA_FILE_PATH.getParent()); // Ensure the parent directories exist
                Files.createFile(DATA_FILE_PATH); // Create the empty file
                System.out.println("No existing memory found. New data file created.\n");
            }

            System.out.println("-----------------------------------------------");
            System.out.println("Current Task List: ");
            System.out.println("Displaying List:");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, list.get(i));
            }
            System.out.println("-----------------------------------------------");

        } catch (IOException e) {
            System.err.println("Error fetching data ---> " + e.getMessage());
        }
        return list;
    }

    /**
     * Updates the task data file with the provided list of {@link Task} objects.
     * The file is overwritten with the new task data.
     *
     * @param list A list of {@link Task} objects to be written to the file.
     */
    public static void updateMemory(List<Task> list) {
        StringBuilder updatedList = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            updatedList.append(String.format("%d. %s\n", i + 1, list.get(i).toString()));
        }

        try {
            Files.writeString(DATA_FILE_PATH, updatedList.toString());
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
