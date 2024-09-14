package seedu.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Task;
import seedu.task.TaskList;
import seedu.task.ToDo;
import seedu.ui.Formatter;

/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file.
 * It interacts with the {@code TaskList} and {@code Ui} classes to manage the tasks and provide feedback to the user.
 */
public class Storage {

    // The file path where tasks are saved and loaded from
    static final String FILE_PATH = "data/bob.txt";
    private Formatter formatter = new Formatter();

    /**
     * Loads tasks from the specified file into the given {@code TaskList}.
     *
     * @param tl The {@code TaskList} where tasks will be loaded into.
     */
    public void loadTasks(TaskList tl) {
        assert tl != null;
        File f = new File(FILE_PATH);
        assert f != null;
        try {
            Scanner s1 = new Scanner(f);
            while (s1.hasNext()) {
                String line = s1.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tl.addTask(task);
                }
            }
        } catch (FileNotFoundException ignored) {
            // If the file is not found, simply do nothing
        }
    }

    private Task parseTask(String line) {
        String[] taskParts = line.trim().split(" \\| ");
        Task task;
        switch (taskParts[0]) {
        case "T":
            assert taskParts.length == 3;
            task = new ToDo(taskParts[2]);
            break;
        case "D":
            assert taskParts.length == 4;
            task = new Deadline(taskParts[2], taskParts[3]);
            break;
        case "E":
            assert taskParts.length == 5;
            task = new Event(taskParts[2], taskParts[3], taskParts[4]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskParts[0]);
        }

        if (taskParts[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Prepares the file for saving tasks by creating the necessary directories and clearing the file contents.
     * If an error occurs during this process, an error message is displayed to the user.
     */
    public void prepareSave() {
        try {
            Files.createDirectories(Path.of("data")); // Hard-coded
            FileWriter fw = new FileWriter(FILE_PATH);
            assert fw != null;
            fw.close(); // Clear the file contents by closing the newly created FileWriter
        } catch (IOException ignored) {
            this.formatter.savingErrorUi();
        }
    }

    /**
     * Appends a task's string representation to the file.
     * If an error occurs during this process, an error message is displayed to the user.
     *
     * @param s The string representation of the task to be saved.
     */
    public void saveTask(String s) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            assert fw != null;
            fw.write(s + System.lineSeparator());
            fw.close();
        } catch (IOException ignored) {
            this.formatter.savingErrorUi();
        }
    }
}
