package storage;

import tasks.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTaskList() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    Task task = Task.parse(line.split(" \\| "));
                    if (task != null) {
                        taskList.add(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("There are errors in your tasks.Task Data File. Contact the Admin for more info.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    public void saveTaskList(ArrayList<Task> taskList) {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(task.encode() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks: " + e.getMessage());
        }
    }
}
