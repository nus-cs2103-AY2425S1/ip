package pebble;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasksList = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromString(line);
                tasksList.add(task);
            }
            scanner.close();
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return tasksList;
    }

    public void saveTasks(ArrayList<Task> tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasksList) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }
}
