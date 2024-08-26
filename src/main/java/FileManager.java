import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String FILE_PATH = "./data/taskon.txt";
    public static final String SEPARATOR = "|";

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(taskToFileString(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
         try {
             File file = new File(FILE_PATH);

             // check if directory or file do not exist
             if (!file.getParentFile().exists()) {
                 file.getParentFile().mkdirs();
             }
             if (!file.exists()) {
                 file.createNewFile();
             }

             Scanner scanner = new Scanner(file);
             while (scanner.hasNextLine()) {
                 String line = scanner.nextLine();
                 tasks.add(parseTask(line));
             }
             scanner.close();

         } catch (FileNotFoundException | TaskonException e) {
             System.out.println("File not found!");
         } catch (IOException e) {
             System.out.println("Error: " + e.getMessage());
         }
        return tasks;
    }

    private static Task parseTask(String line) throws TaskonException {
        Task task = null;
        String[] taskDescription = line.trim().split("\\s*\\" + SEPARATOR + "\\s*");
        String taskType = taskDescription[0];

        switch (taskType) {
            case "T":
                task = new Todo(taskDescription[2]);
                break;

            case "D":
                task = new Deadline(taskDescription[2], taskDescription[3]);
                break;

            case "E":
                task = new Event(taskDescription[2], taskDescription[3], taskDescription[4]);
                break;

            default:
                System.out.println("Unknown task type: " + taskType);
                break;
        }

        boolean isDone = taskDescription[1].equals("1");
        if (task != null && isDone) {
            try {
                task.markAsDone();
            } catch (TaskonException e) {
                System.out.println(e.getMessage());
            }

        }
        
        return task;
    }

    private static String taskToFileString(Task task) {
        String taskStatus = task.isDone ? "1" : "0";

        String taskDescription = "";
        if (task instanceof Todo) {
            taskDescription = "T" + SEPARATOR + taskStatus + SEPARATOR + task.description;
        } else if (task instanceof Deadline) {
            taskDescription = "D" + SEPARATOR + taskStatus + SEPARATOR + task.description +
                    SEPARATOR + ((Deadline) task).date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            taskDescription = "E" + SEPARATOR + taskStatus + SEPARATOR + task.description +
                    SEPARATOR + ((Event) task).start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) +
                    SEPARATOR + ((Event) task).end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return taskDescription;
    }
}
