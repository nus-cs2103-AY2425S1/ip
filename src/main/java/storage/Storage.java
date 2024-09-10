package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import exceptions.InvalidFileFormatException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

/**
 * The {@code Storage} class handles the storage and retrieval of tasks from a file.
 * It provides methods to read tasks from a file, write tasks to a file, and update task
 * status in the file. The class ensures that the storage file and its containing folder
 * are created if they do not already exist.
 */
public class Storage {

    private static final String FOLDER_NAME = "data";

    private final String filePath;

    /**
     * Constructs a new {@code Storage} object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves tasks from the storage file and returns them as a list. If the storage
     * file or folder does not exist, they are created. Each task is parsed based on its
     * type and status, and added to the task list.
     *
     * @return An {@code ArrayList} of tasks retrieved from the storage file.
     */
    public ArrayList<Task> retrieveTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        Path path = Paths.get(this.filePath);

        // Ensure the folder and file exist
        try {
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            Ui.showErrorMessage("Error creating file or folder: " + e.getMessage());
            return taskList; // Return an empty list if file creation fails
        }

        // Now proceed to read the file
        try (Scanner sc = new Scanner(path.toFile())) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                char taskType = line.charAt(0);
                String[] parts = line.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                String taskNumber = parts[1];
                String taskDescription = parts[2];
                switch (taskType) {
                case 'T' -> {
                    taskList.add(new Todo(taskDescription));
                    if (taskNumber.equals("1")) {
                        taskList.get(taskList.size() - 1).completeTask();
                    }
                }
                case 'D' -> {
                    LocalDateTime taskDeadline = LocalDateTime.parse(parts[3]);
                    taskList.add(new Deadline(taskDescription, taskDeadline));
                    if (taskNumber.equals("1")) {
                        taskList.get(taskList.size() - 1).completeTask();
                    }
                }
                case 'E' -> {
                    LocalDateTime taskStartTime = LocalDateTime.parse(parts[3]);
                    LocalDateTime taskEndTime = LocalDateTime.parse(parts[4]);
                    taskList.add(new Event(taskDescription, taskStartTime, taskEndTime));
                    if (taskNumber.equals("1")) {
                        taskList.get(taskList.size() - 1).completeTask();
                    }
                }
                default -> {
                    throw new InvalidFileFormatException("File not in appropriate format");
                }
                }
            }
        } catch (FileNotFoundException e) {
            Ui.showErrorMessage("File not found: " + path.toAbsolutePath());
        } catch (InvalidFileFormatException e) {
            Ui.showErrorMessage(e.getMessage());
        }

        return taskList;
    }

    /**
     * Marks a task as complete in the storage file by updating its status from incomplete
     * (0) to complete (1).
     *
     * @param t The task to be marked as complete.
     */
    public void markComplete(Task t) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            List<String> updatedLines = lines.stream().map(line -> {
                String[] parts = line.split("\\|");
                if (parts.length > 2 && parts[2].trim().equals(t.getName())) {
                    parts[1] = " 1 "; // Change the status from 0 to 1
                    return String.join("|", parts).trim();
                }
                return line;
            }).toList();

            // Write the updated lines back to the file
            try (FileWriter writer = new FileWriter(this.filePath, false)) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Marks a task as incomplete in the storage file by updating its status from complete
     * (1) to incomplete (0).
     *
     * @param t The task to be marked as incomplete.
     */
    public void markIncomplete(Task t) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            List<String> updatedLines = lines.stream().map(line -> {
                String[] parts = line.split("\\|");
                if (parts.length > 2 && parts[2].trim().equals(t.getName())) {
                    parts[1] = " 0 "; // Change the status from 1 to 0
                    return String.join("|", parts).trim();
                }
                return line;
            }).toList();

            // Write the updated lines back to the file
            try (FileWriter writer = new FileWriter(this.filePath, false)) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a task from the storage file by removing the corresponding line from the file.
     *
     * @param t The task to be deleted from the storage file.
     */
    public void deleteTask(Task t) {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(this.filePath));

            // Filter out the line corresponding to the task to be deleted
            List<String> updatedLines = lines.stream()
                    .filter(line -> {
                        String[] parts = line.split("\\|");
                        return !(parts.length > 2 && parts[2].trim().equals(t.getName()));
                    })
                    .collect(Collectors.toList());

            // Write the remaining lines back to the file
            try (FileWriter writer = new FileWriter(this.filePath, false)) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a new Todo task to the storage file.
     *
     * @param t The Todo task to be written to the storage file.
     */
    public void writeTodoToFile(Todo t) {
        try {
            File dataFolder = new File(FOLDER_NAME);

            if (!dataFolder.exists()) {
                dataFolder.mkdirs(); // Create the folder if it doesn't exist
            }
            try (FileWriter writer = new FileWriter(this.filePath, true)) {
                writer.write("T | 0 | " + t.getName() + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a new Deadline task to the storage file.
     *
     * @param t The Deadline task to be written to the storage file.
     */
    public void writeDeadlineToFile(Deadline t) {
        try {
            File dataFolder = new File(FOLDER_NAME);

            if (!dataFolder.exists()) {
                dataFolder.mkdirs(); // Create the folder if it doesn't exist
            }
            try (FileWriter writer = new FileWriter(this.filePath, true)) {
                writer.write("D | 0 | " + t.getName() + " | " + t.getTime() + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a new Event task to the storage file.
     *
     * @param t The Event task to be written to the storage file.
     */
    public void writeEventToFile(Event t) {
        try {
            File dataFolder = new File(FOLDER_NAME);

            if (!dataFolder.exists()) {
                dataFolder.mkdirs(); // Create the folder if it doesn't exist
            }

            try (FileWriter writer = new FileWriter(this.filePath, true)) {
                writer.write("E | 0 | " + t.getName() + " | " + t.getStartTime() + " | " + t.getEndTime()
                        + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
