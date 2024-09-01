import java.io.*;
import java.util.ArrayList;

public class Storage {
  // deals with loading tasks from the file and saving tasks in the file
  private String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public ArrayList<Task> load() throws EliException {
    ArrayList<Task> tasks = new ArrayList<>();
    File file = new File(filePath);
    if (!file.exists()) {
      return tasks;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Task task = Parser.parseTaskFromFile(line);
        if (task != null) {
          tasks.add(task);
        }
      }
    } catch (IOException e) {
      throw new EliException("An error occurred while loading tasks from file.");
    }
    return tasks;
  }

  public void save(TaskList tasks) {
    try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath))) {
      for (Task task : tasks.getTasks()) {
        printWriter.println(task.toFileFormat());
      }
    } catch (IOException e) {
      System.out.println("An error occurred while saving tasks to file.");
    }
  }
}
