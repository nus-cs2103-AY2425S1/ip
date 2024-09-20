package johncena.storage;

import java.io.*;
        import java.util.ArrayList;
import johncena.exceptions.CenaException;
import johncena.tasks.*;

public class Storage {
    public static final String SEPARATOR = " | ";
    private static String filePath = "./data/CenaTaskList.txt"; // Default file path

    public static void setFilePath(String path) {
        assert path != null : "File path should not be null";
        filePath = path;
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(toSaveString(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            createFileIfNotExists(file);
            readTasksFromFile(tasks, file);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tasks;
    }

    private static void createFileIfNotExists(File file) throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private static void readTasksFromFile(ArrayList<Task> tasks, File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(parseTask(line));
                } catch (CenaException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static Task parseTask(String line) throws CenaException {
        assert line != null : "Line to parse should not be null";
        Task task = null;
        String[] taskDescription = line.trim().split("\\s*\\|\\s*");
        String taskType = taskDescription[0];

        switch (taskType) {
            case "T":
                task = new Todo(taskDescription[2]);
                break;
            case "D":
                task = new Deadline(taskDescription[2], taskDescription[3]);
                break;
            case "E":
                String[] eventTimes = taskDescription[3].split(" ~ ");
                task = new Event(taskDescription[2], eventTimes[0], eventTimes[1]);
                break;
            default:
                System.out.println("Unknown task type: " + taskType);
                break;
        }

        if (task != null && taskDescription[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    private static String toSaveString(Task task) {
        assert task != null : "Task to save should not be null";
        String taskStatus = task.isTaskDone() ? "1" : "0";
        String taskDescription = "";

        if (task instanceof Todo todo) {
            taskDescription = "T" + SEPARATOR + taskStatus + SEPARATOR + todo.getDescription();
        } else if (task instanceof Deadline deadline) {
            taskDescription = "D" + SEPARATOR + taskStatus + SEPARATOR + deadline.getDescription() + SEPARATOR + deadline.getBy();
        } else if (task instanceof Event event) {
            taskDescription = "E" + SEPARATOR + taskStatus + SEPARATOR + event.getDescription() + SEPARATOR + event.getFrom() + " ~ " + event.getTo();
        }

        return taskDescription;
    }
}