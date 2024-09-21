package eli.storage;

import eli.task.*;
import java.util.ArrayList;
import eli.exception.EliException;
import eli.parser.Parser;

import java.io.*;

/**
 * Handles loading tasks from the file and saving tasks to the file.
 */
public class Storage {
  private String filePath;

  /**
   * Constructor for Storage.
   *
   * @param filePath The file path where tasks are saved and loaded.
   */
  public Storage(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Loads tasks from the file.
   *
   * @return The list of tasks loaded from the file.
   * @throws EliException If an error occurs while loading tasks.
   */
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

  /**
   * Saves tasks to the file.
   *
   * @param tasks The list of tasks to be saved.
   * @throws EliException If an error occurs while saving tasks.
   */
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
