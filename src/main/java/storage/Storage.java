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

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
        } catch (IOException e) {
            System.err.println("Error creating or accessing the file: " + filePath);
            e.printStackTrace();
            throw new FileNotFoundKukiShinobuException();
        }

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


    public void write(ArrayList<Task> tasks) {
        // TODO: Takes this.tasks and write it to the database file
        StringBuilder stringToBeWritten = new StringBuilder();
        // Step 1: Parse the file into a single string
        for (Task task: tasks) {
            stringToBeWritten.append(task.getDatabaseString()).append(System.lineSeparator());
        }

        // Step 2: Insert the entire text string into the file
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(this.filePath);
            fileWriter.write(stringToBeWritten.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
