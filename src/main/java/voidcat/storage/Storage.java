package voidcat.storage;

import voidcat.task.*;
import voidcat.exception.VoidException;
import voidcat.ui.Ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException, VoidException, SecurityException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                if (tasks.isEmpty()) {
                    Ui.showMessageAndLines("No saved tasks found yet! voidcat.task.Task list is empty.\n\tStart adding tasks and track them!");
                }
            }
        } else {
            ensureFileAndDirectoryExist(file);
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException, VoidException {
        ensureFileAndDirectoryExist(new File(filePath));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            tasks.saveTasks(bw);
        }
    }

    private void ensureFileAndDirectoryExist(File file) throws VoidException, SecurityException {
        try {
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    throw new VoidException("Error in creating directory!");
                }
            }
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new VoidException("Error in creating file!");
                } else {
                    Ui.showMessageAndLines("No saved tasks found yet! voidcat.task.Task list is empty.\n\tStart adding tasks and track them!");
                }
            }
        } catch (IOException e) {
            Ui.showMessageAndLines("IO error in creating file: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            return new ToDo(parts[2], Integer.parseInt(parts[1]));
        case "D":
            return new Deadline(parts[2], parts[3], Integer.parseInt(parts[1]));
        case "E":
            return new Event(parts[2], parts[3], parts[4], Integer.parseInt(parts[1]));
        default:
            return null;
        }
    }
}