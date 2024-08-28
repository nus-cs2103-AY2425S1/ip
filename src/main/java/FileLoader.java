import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Properties;

public class FileLoader {

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
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
            Task task;
            switch (taskType) {
                case "T":
                    task = new ToDo(properties.getProperty("description"));
                    break;
                case "D":
                    task = new Deadline(properties.getProperty("description"), properties.getProperty("endDate"));
                    break;
                case "E":
                    task = new Event(properties.getProperty("description"), properties.getProperty("endDate"), properties.getProperty("startDate"));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown task type: " + taskType);
            }
            tasks.add(task);
            index++;
        }

        return tasks;
    }
}
