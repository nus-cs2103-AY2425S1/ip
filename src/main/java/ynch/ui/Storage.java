/**
 * Loads and saves tasks into a file
 *
 */
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Storage {
    String filename;
    File file;
    Path filePath;
    
    Storage(String filePath, String filename) {
        File file = new File(filename);
        this.filePath = Paths.get(filePath, filename);;
    }

    TaskList load() {
        ArrayList<Task> taskHistory = new ArrayList<Task>();

        if (Files.exists(this.filePath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    taskHistory.add(this.stringToTask(line));
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }

        return new TaskList(taskHistory);
    }

    void save(TaskList taskHistory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath.toFile()))) {
            writer.write(taskHistory.toSaveAsString());
            // System.out.println("Task history saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

    Task stringToTask(String string) {
        // string will be in the format "D1/task description/[deadline or from]/[to]"
        String taskType = String.valueOf(string.charAt(0));
        int status = Integer.parseInt(String.valueOf(string.charAt(1)));
        // System.out.println(status);
        // System.out.println(status == 1);
        String[] task = string.split("/");
        switch (taskType) {
        case "T":
            return new Todo(status, task[1]);
        case "D":
            return new Deadline(status, task[1], task[2]);
        case "E":
            return new Event(status, task[1], task[2], task[3]);
        }
        return new Task(string);
    }
   
}
