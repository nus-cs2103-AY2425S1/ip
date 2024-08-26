package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import tasks.Task;

public class Storage {
    private final String txtFileName;
    private final String datFileName;

    public Storage(String txtFileName, String datFileName) {
        this.txtFileName = txtFileName;
        this.datFileName = datFileName;
    }

    public void saveTasksToFile(ArrayList<Task> taskList) {
        // Assisted by Copilot
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }   
        try (
            FileWriter writer = new FileWriter(txtFileName);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(datFileName))
        ) {
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            oos.writeObject(taskList);
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasksFromFile() {
        // Assisted by Copilot
        ArrayList<Task> taskList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(datFileName))) {
            taskList.addAll((ArrayList<Task>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while loading tasks from file: " + e.getMessage());
        }
        return taskList;
    }
}
