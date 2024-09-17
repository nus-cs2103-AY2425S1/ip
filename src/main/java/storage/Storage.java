package storage;

import java.io.*;
import java.util.ArrayList;
import task.Task;
import todo.ToDo;
import deadline.Deadline;
import event.Event;

/**
 * Handles the reading and writing of data from/to a specified file path.
 * <p>
 * Provides methods to load a task list from a file, save a task list to a file and to convert a task to a specified string format.
 * </p>
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage class that initializes the filePath.
     *
     * @param filePath The initial file path to be used.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a specified file path into an array list of tasks.
     *
     * @return Returns an array list of tasks that has been read from the filePath.
     */
    public ArrayList<Task> loadTaskListFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                File dir = new File(file.getParent());
                if (!dir.exists()) {
                    dir.mkdir();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
                return null;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                case "ToDo":
                    ToDo todo = new ToDo(description);
                    if (isDone) todo.markAsDone();
                    taskList.add(todo);
                    break;
                case "Deadline":
                    String by = parts[3];
                    Deadline deadline = new Deadline(description, by);
                    if (isDone) deadline.markAsDone();
                    taskList.add(deadline);
                    break;
                case "Event":
                    String from = parts[3];
                    String to = parts[4];
                    Event event = new Event(description, from, to);
                    if (isDone) event.markAsDone();
                    taskList.add(event);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the task list: " + e.getMessage());
            return null;
        }
        return taskList;
    }

    /**
     * Saves files from an array list of tasks to the specified file path.
     *
     * @param taskList The array list of tasks to be saved in the specified file path.
     */
    public void saveTaskListToFile(ArrayList<Task> taskList) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                File dir = new File(file.getParent());
                if (!dir.exists()) {
                    dir.mkdir();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list: " + e.getMessage());
        }
    }

    /**
     * Converts a task to its respective String format.
     *
     * @param task The task to be converted.
     * @return Returns a String corresponding to the specified Task.
     */
    public static String taskToString(Task task) {
        String type = "";
        String status = task.isDone() ? "1" : "0";
        String description = task.getName();

        if (task instanceof ToDo) {
            type = "ToDo";
            return type + " | " + status + " | " + description;
        } else if (task instanceof Deadline) {
            type = "Deadline";
            String by = ((Deadline) task).getBy();
            return type + " | " + status + " | " + description + " | " + by;
        } else if (task instanceof Event) {
            type = "Event";
            String from = ((Event) task).getFrom();
            String to = ((Event) task).getTo();
            return type + " | " + status + " | " + description + " | " + from + " | " + to;
        }
        return "";
    }
}