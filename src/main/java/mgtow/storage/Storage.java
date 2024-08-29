package mgtow.storage;

import mgtow.task.Task;
import mgtow.util.InvalidTaskException;
import mgtow.util.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        createDirectoryIfNotExists();
    }

    private void createDirectoryIfNotExists() {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("\tCreated directory: " + directory.getAbsolutePath());
            } else {
                System.out.println("\tFailed to create directory: " + directory.getAbsolutePath());
            }
        }
    }

    public ArrayList<Task> load() throws InvalidTaskException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(Parser.parseTaskFromStorage(line));
            }
        } catch (FileNotFoundException e) {
            throw new InvalidTaskException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(Parser.taskToStorageString(task));
            }
        } catch (IOException e) {
            System.out.println("\tError saving tasks: " + e.getMessage());
        }
    }
}