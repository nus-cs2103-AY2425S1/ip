import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Task.Task;
import Task.ToDo;
import Task.Deadline;
import Task.Event;

public class Storage {
    private static final String TASK_FILE_DIRECTORY = "./data/taskTable.csv";
    private static final File TASK_FILE = new File(TASK_FILE_DIRECTORY);

    public static void saveTasksToLocalDisk(ArrayList<Task> tasks) {
        try {
            writeToTaskFile(formatTasks(tasks));
        } catch (IOException e) {
            System.out.println("Failed to save the list of tasks to local disk.");
        }
    }

    private static String formatTasks(ArrayList<Task> tasks) {
        StringBuilder res = new StringBuilder();
        for (Task t: tasks) {
            res.append(t.formatToCSV());
            res.append("\n");
        }
        return res.toString();
    }

    private static void createTaskFile() {
        try {
            File parentDir = TASK_FILE.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (TASK_FILE.createNewFile()) {
                System.out.println("A task file has been created locally.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the task file.");
        }
    }

    private static void writeToTaskFile(String formattedTasks) throws IOException {
        if (TASK_FILE.isFile()) {
            try (FileWriter writer = new FileWriter(TASK_FILE_DIRECTORY)) {
                writer.write(formattedTasks);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the task file.");
            }
        } else {
            createTaskFile();
            writeToTaskFile(formattedTasks);
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!TASK_FILE.isFile()) {
            System.out.println("Task file not found. Starting with an empty task list.");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(TASK_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file.");
        }

        return tasks;
    }

    private static Task parseTask(String line) {
        // Regex to match quoted strings and handle | as a delimiter outside of quotes
        Pattern pattern = Pattern.compile("\"([^\"]*)\"\\s*\\|?");
        Matcher matcher = pattern.matcher(line);

        ArrayList<String> parts = new ArrayList<>();

        while (matcher.find()) {
            parts.add(matcher.group(1).trim());
        }

        if (parts.size() < 3) {
            System.out.println("Invalid task format: " + line);
            return null; // Invalid format, do not add to list
        }

        String statusIcon = parts.get(0);
        boolean isDone = statusIcon.equals("X");
        String taskType = parts.get(1);
        String description = parts.get(2);

        switch (taskType) {
            case "ToDo":
                return new ToDo(description, isDone);
            case "Deadline":
                if (parts.size() < 4) {
                    System.out.println("Invalid Deadline task format: " + line);
                    return null;
                }
                String by = parts.get(3);
                return new Deadline(description, isDone, by);
            case "Event":
                if (parts.size() < 5) {
                    System.out.println("Invalid Event task format: " + line);
                    return null;
                }
                String start = parts.get(3);
                String end = parts.get(4);
                return new Event(description, isDone, start, end);
            default:
                System.out.println("Unknown task type: " + taskType);
                return null; // Unknown task type, do not add to list
        }
    }

}
