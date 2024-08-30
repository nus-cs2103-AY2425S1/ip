import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;
public class FileWriter {
    private final SentinelList arrayList;
    public FileWriter(SentinelList arrayList){
        this.arrayList = arrayList;
    }
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
