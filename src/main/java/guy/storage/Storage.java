package guy.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import guy.tasks.Task;
import guy.tasks.TaskManager;

/**
 * A storage class, handling reads and writes to the file system.
 * Only one instance exists at a time.
 */
public class Storage {
    private static Storage storage;

    /**
     * Retrieves the instance of the Storage class.
     *
     * @return the instance of the class
     */
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    /**
     * Reads data from the file system and loads it into the TaskManager.
     * Creates necessary directories and files if not present.
     */
    public void readData() {
        try {
            File dir = new File("data/");
            if (!dir.exists()) {
                boolean created = dir.mkdir();
            }

            File f = new File("data/guy.txt");
            if (!f.exists()) {
                boolean created = f.createNewFile();
            }

            Scanner read = new Scanner(f);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            TaskManager tm = TaskManager.getInstance();
            while (read.hasNext()) {
                tm.loadData(read.nextLine());
            }

            read.close();
        } catch (IOException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    /**
     * Writes data from the TaskManager to the file system.
     */
    public void writeData() {
        try {
            FileWriter writer = new FileWriter("data/guy.txt");
            ArrayList<Task> tasks = TaskManager.getInstance().getTasks();
            for (Task task : tasks) {
                writer.write(task.saveFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    /**
     * Saves data to the Storage instance.
     */
    public static void saveData() {
        Storage f = Storage.getInstance();
        f.writeData();
    }


}
