package sentinel.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Properties;

import sentinel.task.Deadline;
import sentinel.task.Event;
import sentinel.task.Task;
import sentinel.task.ToDo;
import sentinel.utils.SentinelList;

/**
 * The FileLoader class is responsible for loading tasks from a properties file into a SentinelList.
 * It reads tasks from a file named "tasks.properties" in the "sentinel-saves" directory,
 * parses the properties for each task, and creates corresponding Task objects.
 */
public class FileLoader {

    private static final Path DIRECTORY_PATH = Paths.get("sentinel-saves");
    private static final Path FILE_PATH = DIRECTORY_PATH.resolve("tasks.properties");

    static {
        try {
            // Ensure the directory exists
            if (!Files.exists(DIRECTORY_PATH)) {
                Files.createDirectories(DIRECTORY_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the "tasks.properties" file into a SentinelList.
     *
     * @return A SentinelList containing the tasks read from the file, or null if an error occurs.
     */
    public SentinelList loadTasks() {
        SentinelList tasks = new SentinelList();
        Properties masterFile = new Properties();

        // Check if the file exists before attempting to read
        if (!Files.exists(FILE_PATH)) {
            return tasks; // Return an empty list if the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH.toFile()))) {
            masterFile.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        int index = 0;
        while (masterFile.containsKey("task" + index)) {
            String propertiesString = masterFile.getProperty("task" + index);
            Properties properties = new Properties();

            try (StringReader sr = new StringReader(propertiesString)) {
                properties.load(sr);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            String taskType = properties.getProperty("taskType");
            Task task = switch (taskType) {
            case "T" -> new ToDo(properties.getProperty("description"));
            case "D" -> new Deadline(properties.getProperty("description"),
                    LocalDateTime.parse(properties.getProperty("endDate")));
            case "E" -> new Event(properties.getProperty("description"),
                    LocalDateTime.parse(properties.getProperty("endDate")),
                    LocalDateTime.parse(properties.getProperty("startDate")));
            default -> throw new IllegalArgumentException("Unknown task type: " + taskType);
            };
            if (properties.getProperty("isDone").equals("T")) {
                task.setDone();
            }
            tasks.add(task);
            index++;
        }

        return tasks;
    }
}
