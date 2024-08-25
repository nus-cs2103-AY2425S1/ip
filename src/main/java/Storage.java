import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
            File folder = new File(folderPath);
            folder.mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("I/O error occurred.");
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(String text) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("I/O error occurred.");
            e.printStackTrace();
        }
    }

    // Save tasks to file using "," as delimiter
    public void saveTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            // Append task type, status, and description, separated by ","
            char taskType = task instanceof ToDo ? 'T' : task instanceof Deadline ? 'D' : 'E';
            int taskStatus = task.getStatus() ? 1 : 0;
            sb.append(taskType).append(",").append(taskStatus).append(",").append(task.description);

            // Append task-specific details
            if (taskType == 'D') {
                sb.append(",").append(((Deadline) task).by);
            } else if (taskType == 'E') {
                sb.append(",").append(((Event) task).from).append(",").append(((Event) task).to);
            }
            sb.append("\n");
        }

        writeToFile(sb.toString());
    }
}
