package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TasksFileManager {
    private final String fileName;
    public TasksFileManager(String fileName) {
        this.fileName = fileName;
    }

    public void load(TaskList tasks) {
        // Scanner with file inspired by https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
        try {
            Scanner scanner = new Scanner(new File(this.fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(TaskList.deserialize(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing file found. Continuing...");
        }
    }

    public void save(TaskList tasks) {
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
