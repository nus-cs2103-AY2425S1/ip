package velma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import velma.task.After;
import velma.task.Deadline;
import velma.task.Event;
import velma.task.Task;
import velma.task.Todo;



/**
 * Represents a storage object that is responsible for loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from a file located at "/Users/zeonchew04/ip/data/velma.txt" and returns them as
     * an ArrayList of Velma.task.Task objects.
     *
     * <p>If the file does not exist, an empty ArrayList is returned, and a message is printed to indicate
     * that no previous tasks were found. The tasks in the file are expected to be formatted in a specific way
     * to distinguish between different types of tasks (To-Do, Velma.task.Deadline, Velma.task.Event).
     * This method parses the file
     * accordingly and recreates the Velma.task.Task objects.</p>
     *
     * <p>Each task type is identified by the first character in the line:
     * <ul>
     *     <li>'T' for To-Do tasks</li>
     *     <li>'D' for Velma.task.Deadline tasks, which include a deadline date and time</li>
     *     <li>'E' for Velma.task.Event tasks, which include a start and end time</li>
     * </ul>
     *
     * If the task is marked as done (indicated by "[X]"), the task's status is updated accordingly.</p>
     *
     * @return An ArrayList of Velma.task.Task objects that were stored in the file.
     *      If the file is not found, an empty list is returned.
     *
     * @throws IllegalArgumentException if an unknown task type is encountered in the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filepath);
        if (!file.exists()) {
            System.out.println("No previous tasks found.");
            return list;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading tasks.");
        }
        System.out.println(list);
        return list;
    }

    /**
     * Parse method for different types of task
     * @param line
     * @return task after parsed
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" ", 3);
        Task task;
        switch (parts[0].charAt(1)) {
        case 'T':
            task = createTodoTask(parts);
            break;
        case 'A':
            task = createAfterTask(parts);
            break;
        case 'D':
            task = createDeadlineTask(parts);
            break;
        case 'E':
            task = createEventTask(parts);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        }
        if (parts[1].equals("[X]")) {
            task.changeIsDoneStatus();
        }
        return task;
    }

    /**
     * Create to-do task
     * @param parts
     * @return todo task
     */
    private Task createTodoTask(String[] parts) {
        return new Todo(parts[2]);
    }

    /**
     * Create after task
     * @param parts
     * @return after task
     */
    private Task createAfterTask(String[] parts) {
        String[] afterParts = parts[2].split(" \\(after: ", 2);
        String afterDescription = afterParts[0];
        String afterString = afterParts[1].substring(0, afterParts[1].length() - 1);
        return new After(afterDescription, afterString);
    }

    /**
     * Create deadline task
     * @param parts
     * @return deadline task
     */
    private Task createDeadlineTask(String[] parts) {
        String[] deadlineParts = parts[2].split(" \\(by: ", 2);
        String deadlineDescription = deadlineParts[0];
        String byString = deadlineParts[1].substring(0, deadlineParts[1].length() - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        LocalDateTime by = LocalDateTime.parse(byString, formatter);
        return new Deadline(deadlineDescription, by);
    }

    /**
     * Create event task
     * @param parts
     * @return event task
     */
    private Task createEventTask(String[] parts) {
        String[] eventParts = parts[2].split(" \\(from: | to: ", 3);
        String eventDescription = eventParts[0];
        String startTimeString = eventParts[1];
        String endTimeString = eventParts[2].substring(0, eventParts[2].length() - 1);
        return new Event(eventDescription, startTimeString, endTimeString);
    }


    /**
     * Saves the tasks in the list to a file located at "/Users/zeonchew04/ip/data/velma.txt".
     * The tasks are written to the file in the same format as they are read from the file.
     * @param list
     * @throws IOException if an error occurs while writing to the file.
     */
    public void save(ArrayList<Task> list) {

        File file = new File(filepath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(filepath)) {
            for (Task task : list) {
                writer.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }


}