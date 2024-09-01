package ahmad;

import ahmad.exceptions.AhmadException;
import ahmad.processor.Processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class in charge of reading and writing to file.
 */
public class Storage {
  private static final String FILE_PATH = "./data/tasks.txt";

  /**
   * Loads file contents into TaskList.
   */
  public static void load() {
    try {
      final File file = new File(FILE_PATH);
      final Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        try {
          final String prompt = scanner.nextLine();
          final Processor inst = Parser.parse(prompt);
          inst.process(prompt);
        } catch (AhmadException e) {
          Ui.print("File reading error: " + e.getMessage());
        }
      }

      Ui.print("File read, you now have: " + ahmad.processor.task.TaskList.getStringList().get(0));
    } catch (FileNotFoundException e) {
      Ui.print("File not found, skipping file reading...");
    }
  }

  /**
   * Saves prompt into file.
   *
   * @param prompt The prompt/command to be saved.
   */
  public static void save(String prompt) {
    try {
      final File file = new File(FILE_PATH);
      if (!file.exists()) {
        try {
          file.getParentFile().mkdirs();
          file.createNewFile();
        } catch (IOException e) {
          Ui.print("File creation error: " + e.getMessage());
        }
      }

      FileWriter writer = new FileWriter(FILE_PATH, true);
      writer.write("\n" + prompt);
      writer.close();
    } catch (IOException e) {
      Ui.print("File write error: " + e.getMessage());
    }
  }
}
