import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList tasks) throws CharlotteException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            if (!directory.exists()) {
                boolean isCreated = directory.mkdirs();
                if (!isCreated) {
                    throw new CharlotteException("Failed to create directory: " + directory.getAbsolutePath());
                }
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new CharlotteException("An error occurred while saving the file.");
        }

    }

    public ArrayList<Task> loadTasks() throws CharlotteException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new CharlotteException("No existing data file found");
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String nextTask = scanner.nextLine();
                String[] taskData = nextTask.split(" \\| ");
                String taskType = taskData[0];
                boolean isDone = taskData[1].equals("1");
                String description = taskData[2];

                Task task;
                switch (taskType) {
                    case "T":
                        task = new ToDo(description);
                        break;
                    case "D":
                        if (taskData.length < 4) {
                            throw new CharlotteException("Invalid format for a deadline task");
                        }
                        String by = taskData[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        if (taskData.length < 5) {
                            throw new CharlotteException("Invalid format for an event task");
                        }
                        String from = taskData[3];
                        String to = taskData[4];
                        task = new Event(description, from, to);
                        break;
                    default:
                        throw new CharlotteException("Unknown task type in file: " + taskType);
                }

                if (isDone) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (CharlotteException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }
}
