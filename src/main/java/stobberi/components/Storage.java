package stobberi.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import stobberi.stobberiexception.NotPossibleDurationStobberiException;
import stobberi.task.Deadline;
import stobberi.task.Event;
import stobberi.task.Task;
import stobberi.task.Todo;

/**
 * The {@code Storage} class handles reading and writing {@code Task} objects to and from a file.
 * It supports reading tasks of different types (Todo, Deadline, Event) from a file and saving tasks
 * back to the file. This class is responsible for the persistence of task data.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} instance with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from the file and returns them as an {@code ArrayList} of {@code Task} objects.
     *
     * <p>Tasks are read from the file in a specific format and are parsed into their respective types:
     * Todo, Deadline, or Event. The method handles potential IO errors and specific exceptions related
     * to task duration.
     *
     * @return An {@code ArrayList} containing the tasks read from the file.
     */
    public ArrayList<Task> getList() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    taskList.add(new Todo(parts[1], parts[2]));
                    break;
                case "D":
                    taskList.add(new Deadline(parts[1], parts[2], parts[3]));
                    break;
                case "E":
                    taskList.add(new Event(parts[1], parts[2], parts[3], parts[4]));
                    break;
                default:
                    // does nothing
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error found: " + e.getMessage());
        } catch (NotPossibleDurationStobberiException e) {
            System.out.println(e.getMessage());
        }

        return taskList;
    }

    /**
     * Saves the specified list of tasks to the file.
     *
     * <p>The tasks are written to the file in a format that allows them to be read back correctly. Each
     * task is written with its type identifier and associated details. The method also includes the
     * completion status of each task.
     *
     * @param taskList The {@code ArrayList} of {@code Task} objects to be saved to the file.
     */
    public void saveList(ArrayList<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                String taskLine = "";
                if (task instanceof Todo) {
                    taskLine += "T | ";
                    taskLine += task.getDescription();
                } else if (task instanceof Deadline deadline) {
                    taskLine += "D | ";
                    taskLine += task.getDescription();
                    taskLine += " | ";
                    taskLine += deadline.getDeadlineOfTask();
                } else if (task instanceof Event event) {
                    taskLine += "E | ";
                    taskLine += task.getDescription();
                    taskLine += " | ";
                    taskLine += event.getStartOfEvent();
                    taskLine += " | ";
                    taskLine += event.getEndOfEvent();
                }
                if (task.isDone()) {
                    taskLine += " | 1\n";
                } else {
                    taskLine += " | 0\n";
                }
                writer.write(taskLine);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error found: " + e.getMessage());
        }
    }
}
