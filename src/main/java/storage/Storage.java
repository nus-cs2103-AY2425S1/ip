package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Storage class encapsulates loading and saving tasks into the OrangeCat.txt
 */
public class Storage {

    private static final String FILE_PATH = "./data/OrangeCat.txt";

    /**
     * Saves tasks after each time the program runs
     * @param items
     */
    public static void saveTasks(List<Task> items) {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent()); // Create directories if they don't exist
            // BufferedWriter is simply more efficient than FileWriter - speeds up the writing process
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : items) {
                writer.write(task.toDataString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads task from the OrangeCat.txt
     * @param items
     */
    public static void loadTasks(List<Task> items) {
        try {
            // Similar to BufferedWriter, BufferedReader allows reading of files to be more efficient
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line; // To hold each line read from the file
            while ((line = reader.readLine()) != null) {
                // Gets a string array separate into different parts marked by '|'
                // Note: parts can be of different length depending on the length of the input being read
                String[] parts = line.split(" \\| ");
                String taskType = parts[0]; // Either "T"/"D"/"E"
                // Arrangement is as follows
                // <TaskType> <isDone> <TaskDescription> <fromDate> <toDate>
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;
                switch (taskType) {
                case "T":
                    int priorityForToDo = Integer.valueOf(parts[3]);
                    task = new ToDo(description, priorityForToDo);
                    //task = new ToDo(description);
                    break; // Continues with reading the next task in the next line
                case "D":
                    String by = parts[3];
                    int priorityForDeadline = Integer.valueOf(parts[4]);
                    task = new Deadline(description, LocalDate.parse(by), priorityForDeadline);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    int priorityForEvent = Integer.valueOf(parts[5]);
                    task = new Event(description, LocalDate.parse(from), LocalDate.parse(to), priorityForEvent);
                    break;
                default:
                    System.out.println("Unknown task type: " + taskType);
                    // Handle the unexpected case, e.g., log an error or skip the line
                    continue; // Skips the current iteration if taskType is not recognized
                }
                if (task != null) {
                    task.setDone(isDone);
                    //task.isDone = isDone;
                    items.add(task);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) { // If FILE_PATH does not exist
            System.out.println("Data file not found, starting with an empty task list.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("The data file is corrupted, starting with an empty task list.");
            items.clear();
        }
    }
}
