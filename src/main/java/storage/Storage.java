package storage;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Storage class handles reading from and writing to the file system.
 * It follows the Singleton pattern to ensure only one instance exists.
 */
public class Storage {

    private static Storage storage;

    /**
     * Gets the singleton instance of the Storage class.
     *
     * @return The Storage instance
     */
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    /**
     * Reads data from the file system and loads it into the TaskList.
     * Creates necessary directories and files if they don't exist.
     */
    public void readData() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            File filedir = new File("data/");
            if (!filedir.exists()) {
                boolean success = filedir.mkdir();
            }
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                boolean success = file.createNewFile();
            }

            Scanner scan = new Scanner(file);
            TaskList taskList = TaskList.getInstance();
            while (scan.hasNext()) {
                taskList.loadData(scan.nextLine());
            }
            scan.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes the current TaskList data to the file system.
     */
    public void writeData() {
        try {
            FileWriter filewriter = new FileWriter("data/duke.txt");
            ArrayList<Task> storage = TaskList.getInstance().getTaskList();
            for (Task t : storage) {
                filewriter.write(t.saveFormat() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Static method to save data to the file system using the singleton instance.
     */
    public static void saveData() {
        Storage file = Storage.getInstance();
        file.writeData();
    }
}
