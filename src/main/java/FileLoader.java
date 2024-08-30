import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class FileLoader {

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
