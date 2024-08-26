import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskFileHandler {
    private String filePath;

    public TaskFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void createFileIfDoesNotExist() {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Hey man, something went wrong when I tried creating the file or directory.");
        }
    }

    private static void writeToFile(String filePath, String textToAdd, boolean append) throws IOException {
        FileWriter fw = new FileWriter(filePath, append);
        fw.write(textToAdd);
        fw.close();
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            writeToFile(filePath, "", false);
            for (Task task : tasks) {
                // String format will be Type | isDone | Description | Time Constraint
                String taskString = task.getTaskType() + " | "
                                    + task.getIsDoneString() + " | "
                                    + task.getTaskDescription() + " | "
                                    + task.getTimeConstraint();
                writeToFile(filePath, taskString + "\n", true);
            }
        } catch (IOException e) {
            System.out.println("Hey man, something went wrong as I tried saving the tasks.");
        }
    }

    public ArrayList<Task> loadTasks(ArrayList<Task> tasks) {

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                String type = parts[0].trim();
                String isDoneString = parts[1].trim();
                String description = parts[2].trim();
                String timeConstraint = parts[3].trim();

                Task task;
                switch (type) {
                    case "Deadline":
                        String by = timeConstraint.replace("by:", "").trim();
                        task = new Deadline(description, by);
                        break;
                    case "Event":
                        String[] timeParts = timeConstraint.split(" ");
                        String start = timeParts[1].trim();
                        String end = timeParts[3].trim();
                        task = new Event(description, start, end);
                        break;
                    case "Todo":
                        task = new Todo(description);
                        break;
                    default:
                        throw new IllegalArgumentException("Hey man what type of task is this?" + line);
                }
                if (isDoneString.equals("X")) {
                    task.setIsDone(true);
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Hey man, I can't seem to find the file!");
        }
        return tasks;
    }
}
