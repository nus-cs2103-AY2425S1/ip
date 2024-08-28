package guy.storage;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import guy.tasks.Task;
import guy.tasks.TaskManager;
import tasks.*;

public class Storage {
    private static Storage storage;

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

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

    public static void saveData() {
        Storage f = storage.getInstance();
        f.writeData();
    }


}
