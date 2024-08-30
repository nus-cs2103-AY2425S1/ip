package gopher.storage;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.FileCorruptedException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;
import gopher.task.Task;

import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskManager {
    private static final Path taskFile = Paths.get("./task/task.txt");

    private static String readTaskList(ArrayList<Task> tasks) {
        StringBuilder taskString = new StringBuilder();
        for (Task task : tasks) {
            taskString.append(task.getSaveMessage());
            taskString.append("\n");
        }
        return taskString.toString();
    }

    private static ArrayList<Task> convertToTaskList(String taskString)
            throws FileCorruptedException {
        ArrayList<Task> tasks = new ArrayList<>();
        String[] taskRows = taskString.split("\n");
        String[] tokens;
        for (String row : taskRows) {
            if (row.isEmpty()) continue;

            tokens = row.split(" \\| ");
            String taskType = tokens[0];
            String taskStatus = tokens[1];
            String taskName = tokens[2];

            try {
                Task newTask = null;
                switch (taskType) {
                    case "T":
                        newTask = Task.of("todo " + taskName);
                        break;
                    case "D":
                        newTask = Task.of(String.format("deadline %s /by %s",
                                taskName,
                                tokens[3]));
                        break;
                    case "E":
                        newTask = Task.of(String.format("event %s /from %s /to %s",
                                taskName,
                                tokens[3],
                                tokens[4]));
                        break;
                }
                if (newTask != null && taskStatus.equals("X")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            } catch (UnknownCommandException
                     | EmptyTaskDescriptionException
                     | MissingTokenException e) {
                throw new FileCorruptedException("Task File is corrupted...");
            }
        }
        return tasks;
    }

    public static void initialize() {
        try {
            Files.createDirectories(taskFile.getParent());
            if (!Files.exists(taskFile)) {
                Files.createFile(taskFile);
            }
        } catch (IOException e) {
            System.out.println("Error when creating task file...");
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            String taskString = readTaskList(tasks);
            Files.writeString(taskFile, taskString);
        } catch (IOException e) {
            System.out.println("Error when saving tasks...");
        }
    }

    public static ArrayList<Task> loadTasks() {
        try {
            String taskString = Files.readString(taskFile);
            return convertToTaskList(taskString);
        } catch (IOException e) {
            System.out.println("Error when loading tasks...");
        } catch (FileCorruptedException e) {
            System.out.println("Load File Failed: Task file corrupted...");
        }
        return new ArrayList<>();
    }
}