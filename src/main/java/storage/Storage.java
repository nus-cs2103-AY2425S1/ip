package storage;

import exception.FileNotFoundKukiShinobuException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DELIMITER = " \\| ";

    private final String filePath;

    // TODO: Constructor that instantiates the storage.Storage class while also ensuring that the filepath is valid and a file exists
    // If the file doesn't exist, create the file and any intermediate directories inside it
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // TODO: load method that reads from the specified file path and returns a task list
    public ArrayList<Task> load() throws FileNotFoundKukiShinobuException {
        // Step 1: Read the file, and throw an error is the file can't be found
        File file = new File(this.filePath);
        // Ensure parent directory exists
        file.getParentFile().mkdirs();
        ArrayList<String> input = new ArrayList<>();
        try {
            System.out.println(file.exists());
            // Check if the file exists
            if (!file.exists()) {
                // Create an empty file
                file.createNewFile();
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    input.add(scanner.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
            throw new FileNotFoundKukiShinobuException();
//            return new ArrayList<task.Task>();
        } catch (IOException e) {
            System.err.println("Error creating or accessing the file: " + filePath);
            e.printStackTrace();
            throw new FileNotFoundKukiShinobuException();
//            return new ArrayList<task.Task>();
        }
//        System.out.println(input);

        // Step 2: Parse the tasks
        ArrayList<Task> existingTasks = new ArrayList<>();
        for (String taskString : input) {
            String[] taskConstituents = taskString.split(DELIMITER);
            if (taskConstituents.length < 2) {
                System.err.println("Invalid task format: " + taskString);
                continue; // Skip to the next line
            }

            String taskType = taskConstituents[0];
            boolean isCompleted = taskConstituents[1].equals("1");

            switch (taskType) {
                case "T":
                    if (taskConstituents.length >= 3) {
                        existingTasks.add(new Todo(taskConstituents[2], isCompleted));
                    } else {
                        System.err.println("Invalid task.Todo task format: " + taskString);
                    }
                    break;
                case "D":
                    if (taskConstituents.length >= 4) {
                        existingTasks.add(new Deadline(taskConstituents[2], taskConstituents[3], isCompleted));
                    } else {
                        System.err.println("Invalid task.Deadline task format: " + taskString);
                    }
                    break;
                case "E":
                    if (taskConstituents.length >= 5) {
                        existingTasks.add(new Event(taskConstituents[2], taskConstituents[3], taskConstituents[4], isCompleted));
                    } else {
                        System.err.println("Invalid task.Event task format: " + taskString);
                    }
                    break;
                default:
                    System.err.println("Unknown task type: " + taskType);
                    break;
            }
        }
        return existingTasks;
    }


    // TODO: write method that takes in ArrayList<task.Task>, formats it and writes it to the file
    public void write(ArrayList<Task> tasks) {
        // TODO: Takes this.tasks and write it to the database file
        StringBuilder stringToWrite = new StringBuilder();
        // Step 1: Parse the file into a single string
        for (Task task: tasks) {
            stringToWrite.append(task.getDatabaseString()).append(System.lineSeparator());
        }

        // Step 2: Insert the entire text string into the file
        FileWriter fw = null;
        try {
            fw = new FileWriter(this.filePath);
            fw.write(stringToWrite.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
