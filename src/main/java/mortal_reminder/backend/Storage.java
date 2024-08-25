package mortal_reminder.backend;

import mortal_reminder.io.FormattedPrinting;
import mortal_reminder.io.Parser;
import mortal_reminder.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// The javadocs were autogenerated using ChatGPT with minor edits
/**
 * Handles the manipulation of data to and from a specified storage file.
 * <p>
 * The {@code Storage} class provides methods to append tasks to a file, clear the file,
 * delete specific tasks, and load tasks from the file. It interacts with the file system
 * and ensures that tasks are stored and retrieved correctly.
 */
public class Storage {
    protected final static String storageListFilePath = "src/main/resources/listStorage.txt";

    /**
     * Appends a task to the storage file.
     * <p>
     * This method opens the storage file in append mode and writes the task in its file format
     * to the file. If an {@link IOException} occurs, an error message is printed.
     *
     * @param task the {@link Task} to append to the file.
     */
    public static void appendToListFile(Task task) {
        try {
            FileWriter fw = new FileWriter(storageListFilePath, true);
            String textToAdd = task.convertToFileFormat();
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            FormattedPrinting.taskUnableToBeStoredInFile();
        }
    }

    /**
     * Clears the storage file.
     * <p>
     * This method deletes all content from the storage file by opening it in write mode
     * and writing an empty string. If an {@link IOException} occurs, an error message is printed.
     */
    public static void clearListFile() {
        try {
            FileWriter fw = new FileWriter(storageListFilePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            FormattedPrinting.formatPrint("File does not exist");
        }
    }

    /**
     * Deletes all tasks from the storage file and re-appends tasks from the given {@link TaskList}.
     * <p>
     * This method first clears the storage file and then appends each task in the task list
     * back to the file. This effectively updates the file to reflect the current state of the task list.
     * The method was inspired from the
     * <a href="https://stackoverflow.com/questions/5800603/delete-specific-line-from-java-text-file">following post.</a>
     *
     * @param taskList the {@link TaskList} containing tasks to re-append to the file.
     */
    public static void deleteTaskFromFile(TaskList taskList) {
        clearListFile();
        for (int i = 0; i < taskList.getSize(); i++) {
            appendToListFile(taskList.getTask(i));
        }
    }

    /**
     * Loads tasks from the storage file into a {@link TaskList}.
     * <p>
     * This method reads each line from the storage file, converts it into a {@link Task},
     * and loads it into a new {@link TaskList} object. If the file does not exist or cannot be created,
     * an error message is printed, and a new, empty {@link TaskList} is returned.
     *
     * @return a {@link TaskList} containing tasks loaded from the file, or an empty {@link TaskList} if loading fails.
     */
    public static TaskList loadTaskListFromFile() {
        try {
            File f = new File(storageListFilePath);
            TaskList taskList = new TaskList();

            // Check if the file/folder already exists and create if it is not,
            // send warning if unable to create either if file still does not exist.
            if ((!f.getParentFile().mkdirs() || !f.createNewFile()) && !f.exists()) {
                FormattedPrinting.fileCorrupted();
            }
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String input = s.nextLine();
                Task task = Parser.parseInputFromFile(input);
                taskList.loadTask(task);
            }
            s.close();
            return taskList;
        } catch (RuntimeException | IOException e) {
            return new TaskList();
        }
    }
}
