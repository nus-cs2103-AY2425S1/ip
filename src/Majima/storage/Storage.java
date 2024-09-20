package Majima.storage;

import Majima.MajimaException;
import Majima.task.Deadline;
import Majima.task.Event;
import Majima.task.Task;
import Majima.task.Todo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;


public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws MajimaException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new MajimaException("There was an error loading tasks from file: "
                    + e.getMessage());
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws MajimaException {
        try (PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Task task : tasks) {
                wr.println(task.toFileString());
            }
        } catch (IOException e) {
            throw new MajimaException("There was an error saving tasks to file: "
                    + e.getMessage());
        }
    }

    /**
     Test TODO: write javadoc
     */
    private Task parseTask(String line) throws MajimaException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {

            throw new MajimaException("Invalid task format: " + line
                    + "This may be a sign of corrupted or invalid data.");
        }

        String type = parts[0].trim();
        String statusIcon = parts[1].trim();
        String description = parts[2].trim();

        Task task;

        switch (type) {
            case "[T]":
                task = new Todo(description);
                break;
            case "[D]":
                if (parts.length < 4) {
                    throw new MajimaException("Invalid task format: " + line
                            + "This may be a sign of corrupted or invalid data.");
                }
                String deadline = parts[3].trim();
                task = new Deadline(description, deadline);
                break;
            case "[E]":
                if (parts.length < 5) {
                    throw new MajimaException("Invalid task format: " + line
                            + "This may be a sign of corrupted or invalid data.");
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, from, to);
                break;
            default:
                throw new MajimaException("Invalid task format: " + line
                        + "This may be a sign of corrupted or invalid data.");
        }

        if (statusIcon.equals("[X]")) {
            task.markAsDone();
        } else if (statusIcon.equals("[ ]")) {
            task.markAsUndone();
        } else {
            throw new MajimaException("Invalid task format: " + line
                    + "This may be a sign of corrupted or invalid data.");
        }
        return task;
    }
}