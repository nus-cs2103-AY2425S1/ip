package sentinel.storage;

import sentinel.task.Deadline;
import sentinel.task.Event;
import sentinel.task.Task;
import sentinel.utils.SentinelList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

/**
 * The FileWriter class is responsible for saving tasks from a SentinelList to a properties file.
 * It writes the tasks to a file named "tasks.properties" in a format that can be read by the FileLoader.
 */
public class FileWriter {
    private final SentinelList arrayList;

    /**
     * Constructs a FileWriter with the specified SentinelList.
     *
     * @param arrayList The SentinelList containing tasks to be saved.
     */
    public FileWriter(SentinelList arrayList) {
        this.arrayList = arrayList;
    }

    /**
     * Saves tasks from the SentinelList to the "tasks.properties" file.
     * Each task is saved with its type, description, and status, and additional fields for Deadline and Event tasks.
     */
    public void saveTasks() {
        Properties masterFile = new Properties();

        for (int i = 0; i < arrayList.size(); i++) {
            Task task = arrayList.get(i);
            Properties properties = new Properties();
            String taskId = "task" + i;  // Use the index as part of the key

            properties.setProperty("taskType", String.valueOf(task.getTaskType()));
            properties.setProperty("description", task.getDescription());
            properties.setProperty("isDone", task.isDone() ? "T" : "F");
            if (task instanceof Deadline) {
                properties.setProperty("endDate", ((Deadline) task).getEndDate().toString());
            }
            if (task instanceof Event) {
                properties.setProperty("endDate", ((Event) task).getEndDate().toString());
                properties.setProperty("startDate", ((Event) task).getStartDate().toString());
            }

            try (StringWriter sw = new StringWriter()) {
                properties.store(sw, null);
                masterFile.setProperty(taskId, sw.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileOutputStream out = new FileOutputStream("tasks.properties")) {
            masterFile.store(out, "Task Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
