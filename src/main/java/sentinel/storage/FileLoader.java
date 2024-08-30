package sentinel.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Properties;
import sentinel.utils.SentinelList;
import sentinel.task.Task;
import sentinel.task.ToDo;
import sentinel.task.Event;
import sentinel.task.Deadline;

/**
 * The FileLoader class is responsible for loading tasks from a properties file into a SentinelList.
 * It reads tasks from a file named "tasks.properties", parses the properties for each task, and creates corresponding Task objects.
 */
public class FileLoader {

    /**
     * Loads tasks from the "tasks.properties" file into a SentinelList.
     *
     * @return A SentinelList containing the tasks read from the file, or null if an error occurs.
     */
    public SentinelList loadTasks() {
        SentinelList tasks = new SentinelList();
        Properties masterFile = new Properties();

        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.properties"))) {
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
                case "D" -> new Deadline(properties.getProperty("description"), LocalDateTime.parse(properties.getProperty("endDate")));
                case "E" -> new Event(properties.getProperty("description"), LocalDateTime.parse(properties.getProperty("endDate")), LocalDateTime.parse(properties.getProperty("startDate")));
                default -> throw new IllegalArgumentException("Unknown task type: " + taskType);
            };
            if (properties.getProperty("isDone").equals("T")) task.setDone();
            tasks.add(task);
            index++;
        }

        return tasks;
    }
}
