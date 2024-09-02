package utilities;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import exception.DukeException;
import task.Task;
import task.TaskList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws DukeException {
        // create a new file if the file does not exist
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file at " + filePath);
            }
        }
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.of(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (tasks.isEmpty()) {
            return new TaskList();
        }
        return new TaskList(tasks);
    }

    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data to " + filePath);
        }
    }
}