import exceptions.AhmadException;
import processor.Processor;
import response.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ahmad {
  private static final String FILE_PATH = "../../../data/tasks.txt";

  private static void loadFile() {
    try {
      final File file = new File(FILE_PATH);
      final Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        try {
          final String prompt = scanner.nextLine();
          final Processor inst = MessageParser.parse(prompt);
          inst.process(prompt);
        } catch (AhmadException e) {
          IO.print("File reading error: " + e.getMessage());
        }
      }

      IO.print("File read, ready to accept new messages.");
    } catch (FileNotFoundException e) {
      IO.print("File not found, skipping file reading...");
    }
  }


  private static void saveFile(String prompt) {
    try {
      final File file = new File(FILE_PATH);
      if (!file.exists()) {
        try {
          file.getParentFile().mkdirs();
          file.createNewFile();
        } catch (IOException e) {
          IO.print("File creation error: " + e.getMessage());
        }
      }

      FileWriter writer = new FileWriter(FILE_PATH);
      writer.write(prompt);
      writer.close();
    } catch (IOException e) {
      IO.print("File write error: " + e.getMessage());
    }
  }

  private static void startInteraction() {
    final Scanner scanner = new Scanner(System.in);
    while (true) {
      try {
        final String prompt = scanner.nextLine();

        final Processor inst = MessageParser.parse(prompt);

        final Response response = inst.process(prompt);

        response.getResponse().forEach(IO::print);

        if (response.shouldExit()) {
          break;
        }

        if (response.checkShouldRecord()) {
          saveFile(prompt);
        }
      } catch (AhmadException e) {
        IO.print(e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    final String welcomeMsg = "Hello! I'm Ahmad\nWhat can I do for you?\n\nEnjoy!";
    IO.print(welcomeMsg);

    Ahmad.loadFile();

    startInteraction();
  }
}
