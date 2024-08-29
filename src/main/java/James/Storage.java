package james;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the loading and saving of tasks to and from a file.
 * <p>
 * The Storage class handles reading tasks from a specified file and writing tasks back to the file.
 * It ensures the file and its directories exist, and can handle errors related to file operations.
 * </p>
 */
class Storage {
    private String filepath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filepath The path to the file where tasks will be loaded from and saved to
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads saved tasks from the file into the provided task list.
     * <p>
     * Reads each line from the file, parses the task data, and adds the tasks to the provided TaskList.
     * Handles different task types (Todo, Deadline, Event) and checks for missing information.
     * </p>
     *
     * @param taskList The list to add the loaded tasks to and keep track of them
     */
    public void loadSavedData(ArrayList<Task> taskList) {
        File savedData = new File(filepath);
        File dataDirectory = savedData.getParentFile();

        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        if (!savedData.exists()) {
            try {
                savedData.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
                return;
            }
        }

        try (Scanner s = new Scanner(savedData)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isMarked = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                case "T":
                    taskList.add(new Todo(description, isMarked));
                    break;

                case "D":
                    if (parts.length < 4) {
                        throw new MissingDescriptionException("Missing deadline information in line: " + line);
                    }
                    String deadline = parts[3];
                    taskList.add(new Deadline(description, isMarked, LocalDateTime.parse(deadline)));
                    break;

                case "E":
                    if (parts.length < 5) {
                        throw new MissingDescriptionException("Missing event time information in line: " + line);
                    }
                    String start = parts[3];
                    String end = parts[4];
                    taskList.add(new Event(description, isMarked, LocalDateTime.parse(start),
                            LocalDateTime.parse(end)));
                    break;

                default:
                    System.out.println("Unknown task type found: " + type);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file could not be found after attempting to create it: " + e.getMessage());
        } catch (MissingDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the provided tasks to the file.
     * <p>
     * Writes each task's formatted string representation to the file.
     * </p>
     *
     * @param tasks The list of Task objects to be saved to the file
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filepath)) {
            for (Task task : tasks) {
                writer.write(task.convertToFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file: " + e.getMessage());
        }
    }
}
