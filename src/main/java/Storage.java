import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/monobot.txt";

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Task task : tasks) {
                bufferedWriter.write(taskToFileFormat(task));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = parseTaskFromFileLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    private static String taskToFileFormat(Task task) {
        String taskType = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
        String isDone = task.getIsDone() ? "1" : "0";
        String details = task.toString().split(" ", 3)[2];

        if (task instanceof Deadline) {
            String[] parts = details.split(" \\(by: ");
            return String.format("%s | %s | %s | %s", taskType, isDone, parts[0], parts[1].replace(")", ""));
        } else if (task instanceof Event) {
            String[] parts = details.split(" \\(from: ");
            String[] timeParts = parts[1].split(" to: ");
            return String.format("%s | %s | %s | %s %s", taskType, isDone, parts[0], timeParts[0], timeParts[1].replace(")", ""));
        } else {
            return String.format("%s | %s | %s", taskType, isDone, details);
        }
    }

    private static Task parseTaskFromFileLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.out.println("Invalid task format in file: " + line);
            return null;
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    System.out.println("Invalid deadline format in file: " + line);
                    return null;
                }
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                if (parts.length < 4) {
                    System.out.println("Invalid event format in file: " + line);
                    return null;
                }
                String[] timeParts = parts[3].split(" ", 2);
                task = new Event(description, timeParts[0], timeParts[1]);
                break;
            default:
                System.out.println("Unknown task type in file: " + line);
                return null;
        }

        if (isDone) {
            task.markTask();
        }

        return task;
    }
}

