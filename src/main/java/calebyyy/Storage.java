package calebyyy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import calebyyy.Tasks.Deadline;
import calebyyy.Tasks.Event;
import calebyyy.Tasks.Task;
import calebyyy.Tasks.Todo;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

    }

    void loadTasks(TaskList tasklist) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;

                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        String by = parts[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        task = new Event(description, from, to);
                        break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasklist.addTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public void saveTasks(TaskList taskList) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
