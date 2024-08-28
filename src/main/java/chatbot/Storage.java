package main.java.chatbot;

import main.java.chatbot.exceptions.FileCorruptedException;
import main.java.chatbot.exceptions.FilePermissionException;
import main.java.chatbot.exceptions.InvalidTaskStringException;
import main.java.chatbot.exceptions.LocalFileException;
import main.java.chatbot.tasks.DeadlineTask;
import main.java.chatbot.tasks.EventTask;
import main.java.chatbot.tasks.Task;
import main.java.chatbot.tasks.TodoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String directoryPath;
    private String filePath;

    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws LocalFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File dir = new File(directoryPath);
            if (!dir.exists()) {
                dir.mkdirs(); // Create directory if it doesn't exist
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Create file if it doesn't exist
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    tasks.add(parseTask(line));
                }
                scanner.close();
            }
        } catch (IOException | InvalidTaskStringException e) {
            throw new FileCorruptedException(filePath);
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws LocalFileException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(formatTaskForSaving(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new FilePermissionException(filePath);
        }
    }

    private Task parseTask(String taskString) throws InvalidTaskStringException {
        String[] parts = taskString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                return new TodoTask(description, isDone);
            case "D":
                String by = parts[3];
                return new DeadlineTask(description, by, isDone);
            case "E":
                String from = parts[3];
                String to = parts[4];
                return new EventTask(description, from, to, isDone);
            default:
                throw new InvalidTaskStringException();
        }
    }

    private String formatTaskForSaving(Task task) {
        if (task instanceof TodoTask) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return "D | " + (deadlineTask.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() + " | " + deadlineTask.getBy();
        } else if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return "E | " + (eventTask.isDone() ? "1" : "0") + " | " + eventTask.getDescription() + " | " + eventTask.getFrom() + " | " + eventTask.getTo();
        }
        return "";
    }
}

