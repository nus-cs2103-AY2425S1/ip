import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * The {@code Storage} class represents a storage class for a user's task list
 */
public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Ensures the directory and file exist, creating them if necessary.
     */
    public void initialise() throws IOException {
        Path directoryPath = filePath.getParent();
        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public ArrayList<Task> readTaskList() throws SnipeException, IOException {
        if (Files.exists(filePath)) {
            ArrayList<Task> taskList = new ArrayList<Task>();
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTaskFromString(line);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
            }
            return taskList;
        } else {
            throw new SnipeException("Storage not initialised");
        }
    }

    private Task parseTaskFromString(String line) {
        try {
            String[] split = line.split(" \\| ");
            String taskType = split[0];
            String taskDescription = split[2];
            Task task = null;
            switch (taskType) {
            case "T":
                task = new ToDo(taskDescription);
                break;
            case "D":
                task = new Deadline(taskDescription, split[3]);
                break;
            case "E":
                task = new Event(taskDescription, split[3], split[4]);
                break;
            }
            if (task == null) {
                throw new SnipeException("Task type must be T, D or E");
            }
            if (split[1].equals("1")) {
                task.changeStatus();
            }
            return task;
        } catch (Exception e) {
            System.out.print("String parsed is invalid: " + e.getMessage());
            return null;
        }
    }

    public void saveTaskList(ArrayList<Task> taskList) throws IOException {
        initialise();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)){
            for (Task task : taskList) {
                String item = parseTaskToString(task);
                writer.write(item);
                writer.newLine();
            }
        } catch (SnipeException e) {
            System.out.println(e.getMessage());
        }
    }

    private String parseTaskToString(Task task) throws SnipeException{
        String status = task.getStatus() ? "1" : "0";
        if (task instanceof ToDo) {
            return "T | " + status + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + status + " | " + task.getDescription() + " | " + ((Deadline) task).getDeadline();
        } else if (task instanceof Event) {
            String[] startAndEnd = ((Event) task).getStartAndEnd();
            return "E | " + status + " | " + task.getDescription() + " | " + startAndEnd[0] + " | " + startAndEnd[1];
        }
        throw new SnipeException("Task is not a valid task");
    }
}
