package main;

import exception.CorruptedFileException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the loading and saving of tasks to and from a file.
 */
public class Storage {
    private final File file;
    private final DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            // Load file if it exists
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\\|");
                    char type = line.charAt(0);

                    switch (type) {
                        case 'T':
                            Todo todo = new Todo(parts[2]);
                            if (parts[1].equals("X")) {
                                todo.markTaskAsDone();
                            }
                            list.add(todo);
                            break;
                        case 'D':
                            Deadline deadline = new Deadline(parts[2], LocalDateTime.parse(parts[3], fileFormatter));
                            if (parts[1].equals("X")) {
                                deadline.markTaskAsDone();
                            }
                            list.add(deadline);
                            break;
                        case 'E':
                            Event event = new Event(parts[2], LocalDateTime.parse(parts[3], fileFormatter),
                                    LocalDateTime.parse(parts[4], fileFormatter));
                            if (parts[1].equals("X")) {
                                event.markTaskAsDone();
                            }
                            list.add(event);
                            break;
                        default:
                            throw new CorruptedFileException("File is corrupted.");
                    }
                }
                System.out.println("Existing data file found. Data has been loaded.");
            } else {
                if (file.getParentFile().exists()) {
                    file.createNewFile();
                } else {
                    file.getParentFile().mkdir();
                    file.createNewFile();
                }
                System.out.println("No existing data file found. New data file \"dash.txt\" has been created.");
            }
        } catch (IOException e) {
            System.out.println("I/O error has occurred when creating new file: " + e.getMessage());
        } catch (CorruptedFileException e) {
            System.out.println("Please erase data in file: " + e.getMessage());
        } finally {
            Ui.insertLine();
        }
        return list;
    }

    /**
     * Appends the given text to the file.
     *
     * @param textToAppend The text to be added to the file.
     */
    public void appendToFile(String textToAppend) {
        try (FileWriter fw = new FileWriter(this.file.getPath(), true)) {
            fw.write(textToAppend + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Append to file failed: " + e.getMessage());
        }
    }

    /**
     * Writes the given text to the file, overwriting any existing content.
     *
     * @param textToAdd The text to be written to the file.
     */
    public void writeToFile(String textToAdd) {
        try (FileWriter fw = new FileWriter(this.file.getPath())) {
            fw.write(textToAdd + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Write to file failed: " + e.getMessage());
        }
    }

    /**
     * Marks a task as done and updates the file.
     *
     * @param markNum The index of the task to be marked as done.
     * @param tasks The TaskList containing the tasks.
     */
    public String markTask(int markNum, TaskList tasks) {
        int num = tasks.size();
        Task task = tasks.get(markNum - 1);
        task.markTaskAsDone();
        writeToFile(tasks.get(0).toFile());
        for (int i = 1; i < num; i++) {
            appendToFile(tasks.get(i).toFile());
        }
        return Ui.showMarkTask(task);
    }

    /**
     * Unmarks a task and updates the file.
     *
     * @param unmarkNum The index of the task to be unmarked.
     * @param tasks The TaskList containing the tasks.
     */
    public String unmarkTask(int unmarkNum, TaskList tasks) {
        int num = tasks.size();
        Task task = tasks.get(unmarkNum - 1);
        task.unmarkTask();
        writeToFile(tasks.get(0).toFile());
        for (int i = 1; i < num; i++) {
            appendToFile(tasks.get(i).toFile());
        }
        return Ui.showUnmarkTask(task);
    }

    /**
     * Deletes a task and updates the file.
     *
     * @param deleteNum The index of the task to be deleted.
     * @param tasks The TaskList containing the tasks.
     */
    public String deleteTask(int deleteNum, TaskList tasks) {
        int num = tasks.size();
        Task task = tasks.removeTask(deleteNum - 1);
        if (!tasks.isEmpty()) {
            writeToFile(tasks.get(0).toFile());
            for (int i = 1; i < num - 1; i++) {
                appendToFile(tasks.get(i).toFile());
            }
        } else {
            writeToFile("");
        }
        return Ui.showDeleteTask(task, num);
    }
}
