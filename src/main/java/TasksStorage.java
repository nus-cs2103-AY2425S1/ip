import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

// Singleton class to manage reading and writing tasks to a file
public class TasksStorage {
  private static final Path DATA_DIRECTORY = Paths.get("data");
  private final Path TASKS_FILE_PATH = Paths.get(DATA_DIRECTORY.toString(), "tasks.txt");
  private static TasksStorage instance;

  private TasksStorage() {
    try {
      Files.createDirectories(DATA_DIRECTORY);
      if (!Files.exists(TASKS_FILE_PATH)) {
        Files.createFile(TASKS_FILE_PATH);
      }
    } catch (IOException e) {
      System.out.println("Error creating data directory");
    }
  }

  public static TasksStorage getInstance() {
    if (instance == null) {
      instance = new TasksStorage();
    }
    return instance;
  }

  public TaskList readTasks() {
    try {
      String tasksStore = Files.readString(TASKS_FILE_PATH);
      return TaskList.deserialize(tasksStore);
    } catch (IOException e) {
      System.out.println("Error reading tasks from file");
      return new TaskList();
    }
  }

  public void storeTasks(TaskList list) {
    try {
      Files.writeString(TASKS_FILE_PATH, list.serialize());
    } catch (IOException e) {
      System.out.println("Error writing tasks to file");
    }
  }
}
