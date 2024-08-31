import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    protected final Path filePath;
    
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }
    
    public void save(TaskList taskList) throws IOException {
        try {
            ArrayList<String> encodedTasks = new ArrayList<>();
            for (Task task : taskList.tasks) {
                String taskEncoding = "";
                if (task instanceof Todo) {
                    taskEncoding = String.format("T|%s|%s",
                            task.isDone ? "1" : "0",
                            task.description);
                } else if (task instanceof Deadline) {
                    taskEncoding = String.format("D|%s|%s|%s",
                            task.isDone ? "1" : "0",
                            task.description,
                            ((Deadline) task).by);
                } else if (task instanceof Event) {
                    taskEncoding = String.format("E|%s|%s|%s|%s",
                            task.isDone ? "1" : "0",
                            task.description,
                            ((Event) task).from,
                            ((Event) task).to);
                }
                encodedTasks.add(taskEncoding);
            }
            Files.write(this.filePath, encodedTasks, java.nio.file.StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println(e);
            throw e;
        }
    }
    
    public TaskList load() {
        return new TaskList();
    }
}
