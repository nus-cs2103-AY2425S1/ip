package elon;

import elon.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/** Handles the loading and saving of tasks to and from a file. */
public class Storage {
  private final String path;

  private File file;

  /**
   * Constructs a Storage object with the specified file path. If the file does not exist at the
   * given path, it attempts to create a new file.
   *
   * @param path the path to the file where tasks are stored
   * @throws IOException if an I/O error occurs when creating the file
   */
  public Storage(String path) throws IOException {
    this.path = path;
    this.file = new File(this.path);
    if (!file.exists()) {
      if (file.createNewFile()) {
        System.out.println("File created");
      } else {
        System.out.println("Failed to create file");
      }
    } else {
      System.out.println("File exists");
    }
  }

  /**
   * Parses a line from the file into a Task object then returns it. The line format should be "type
   * | status | description | [additional details]".
   *
   * @param line the line from the file to be parsed
   * @return the Task object represented by the line
   * @throws ElonException if the line format is invalid or cannot be parsed
   */
  private Task parseTask(String line) throws ElonException {
    String[] elements = line.split(" \\| ");
    String type = elements[0];
    boolean isDone = elements[1].equals("1");
    String description = elements[2];

    switch (type) {
      case "T":
        return new ToDo(description, isDone);
      case "D":
        LocalDateTime by = LocalDateTime.parse(elements[3]);
        return new Deadline(description, isDone, by);
      case "E":
        LocalDateTime start = LocalDateTime.parse(elements[3]);
        LocalDateTime end = LocalDateTime.parse(elements[4]);
        return new Event(description, isDone, start, end);
      default:
        throw new ElonException("Invalid task format.");
    }
  }

  /**
   * Loads tasks from the file, each line in the file is parsed into a Task object. Returns the
   * Tasks as an ArrayList of Task objects.
   *
   * @return an ArrayList of Task objects loaded from the file
   */
  public ArrayList<Task> loadFile() {
    ArrayList<Task> taskList = new ArrayList<>();
    assert this.file.exists() : "File needs to exist before loading tasks";
    if (this.file.exists()) {
      try {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          try {
            Task task = parseTask(line);
            taskList.add(task);
          } catch (ElonException e) {
            System.out.println(e);
          }
        }
      } catch (FileNotFoundException e) {
        System.out.println(e);
      }
    }
    return taskList;
  }

  /**
   * Saves the tasks from the TaskList to the file. Each task is written to the file in a format
   * suitable for later loading.
   *
   * @param list the TaskList containing the tasks to be saved
   * @throws IOException if an I/O error occurs while writing to the file
   */
  public void saveFile(TaskList list) throws IOException {
    try (FileWriter fileWriter = new FileWriter(this.file)) {
      int i = 0;
      while (i < list.listSize()) {
        Task task = list.getTask(i);
        fileWriter.write(task.toFileString() + "\n");
        i++;
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
