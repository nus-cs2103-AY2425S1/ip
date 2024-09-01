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
    
    public TaskList load() throws IOException {
        if (!Files.exists(this.filePath)) {
            Files.createFile(this.filePath);
            return new TaskList();
        }
        
        try {
            ArrayList<String> encodedTasks = new ArrayList<>(Files.readAllLines(this.filePath));
            TaskList taskList = new TaskList();
            for (String encodedTask : encodedTasks) {
                String[] taskComponents = encodedTask.split("\\|");
                Task task = null;
                switch (taskComponents[0]) {
                    case "T":
                        task = new Todo(taskComponents[2]);
                        break;
                    case "D":
                        task = new Deadline(taskComponents[2], taskComponents[3]);
                        break;
                    case "E":
                        task = new Event(taskComponents[2], taskComponents[3], taskComponents[4]);
                        break;
                    default:
                        break;
                }
                if (taskComponents[1].equals("1")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
            }
            return taskList;
        } catch (IOException e) {
            System.out.println(e);
            throw e;
        }
    }
}
