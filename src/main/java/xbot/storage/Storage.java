package xbot.storage;

import xbot.task.Task;
import xbot.TaskList;
import xbot.parser.Parser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static final Path DATA_PATH = Paths.get("data", "XBot.txt");

    public void loadTask() throws IOException {
        TaskList list = new TaskList();
        if (Files.exists(DATA_PATH)) {
            //Add all xbot.task in data/XBot.txt to the list
            try (Scanner scanner = new Scanner(DATA_PATH.toFile())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTask(line);
                    if (task != null) {
                        list.add(task);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
                throw new IOException("File not found", e);
            }
        } else {
            addFile();
        }
    }

    public void saveTask(TaskList taskList) {
        try (FileWriter writer = new FileWriter(DATA_PATH.toFile())) {
            for (Task task : taskList.getAllTask()) {
                writer.write(Parser.taskToFileString(task) + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }


    public void addFile() {
        Path directoryPath = Paths.get("./data");
        Path filePath = directoryPath.resolve("XBot.txt");

        try {
            // Check if the directory exists, and create it if it doesn't
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }

            // Check if the file exists, and create it if it doesn't
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
