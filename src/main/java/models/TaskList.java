package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();
    private final String fileName;

    public TaskList(String fileName) {
        this.fileName = fileName;
    }

    public void loadFromFile() {
        // Scanner with file inspired by https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
        try {
            Scanner scanner = new Scanner(new File(this.fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                switch (line.charAt(0)) {
                    case Event.TASK_TYPE -> tasks.add(Event.deserialize(line));
                    case Deadline.TASK_TYPE -> tasks.add(Deadline.deserialize(line));
                    case ToDo.TASK_TYPE -> tasks.add(ToDo.deserialize(line));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing file found. Continuing...");
        }
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean add(Task task) {
        boolean isAddSuccessful = tasks.add(task);
        if (isAddSuccessful) {
            // Writer inspired by https://www.baeldung.com/java-write-to-file
            try (FileWriter fileWriter = new FileWriter(this.fileName, true)) {
                fileWriter.write(task.serialize());
                fileWriter.write('\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return isAddSuccessful;
    }

    public void remove(int index) {
        Task removedTask = this.tasks.remove(index);

        // Rewrite all tasks
        try (FileWriter fileWriter = new FileWriter(this.fileName)) {
            for (Task task : this.tasks) {
                fileWriter.write(task.serialize());
                fileWriter.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
