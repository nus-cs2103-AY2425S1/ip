package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TasksFileManager {
    private final String fileName;
    public TasksFileManager(String fileName) {
        this.fileName = fileName;
    }

    public void load(List<Task> tasks) {
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

    public void save(List<Task> tasks) {
        try (FileWriter fileWriter = new FileWriter(this.fileName)) {
            for (Task task : tasks) {
                fileWriter.write(task.serialize());
                fileWriter.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void append(Task task) {
        // Writer inspired by https://www.baeldung.com/java-write-to-file
        try (FileWriter fileWriter = new FileWriter(this.fileName, true)) {
            fileWriter.write(task.serialize());
            fileWriter.write('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
