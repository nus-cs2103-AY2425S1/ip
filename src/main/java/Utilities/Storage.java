package Utilities;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * A class responsible for loading and saving tasks to and from a file.
 */
public class Storage {

    private static final String filePath = "./data/db.txt";

    /**
     * Loads tasks from the file into the provided task list.
     *
     * @param tasks The list of tasks to load data into.
     */
    public void load(ArrayList<Task> tasks) {

        new FileChecker(filePath);

        Parser dateTimeParser = new Parser();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            try{
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String taskType = parts[0].trim();
                    boolean isDone = parts[1].trim().equals("1");
                    String desc = parts[2].trim();

                    switch (taskType) {
                        case "T":
                            ToDo todo = new ToDo(desc);
                            if (isDone) todo.markDone();
                            tasks.add(todo);
                            break;

                        case "D":
                            Deadline deadline = new Deadline(desc, dateTimeParser.parseDateTime(parts[3].trim()));
                            if (isDone) deadline.markDone();
                            tasks.add(deadline);
                            break;

                        case "E":
                            Event event = new Event(desc, dateTimeParser.parseDateTime(parts[3].trim()), dateTimeParser.parseDateTime(parts[4].trim()));
                            if (isDone) event.markDone();
                            tasks.add(event);
                            break;

                        default:
                            System.out.println("Invalid task type in db.txt: " + taskType);
                            break;
                    }
                }
            }catch (ArrayIndexOutOfBoundsException ignored) {}
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    /**
     * @param tasks
     */
    public void save(ArrayList<Task> tasks){
        try (FileWriter writer = new FileWriter(filePath, false)) {  // 'false' to overwrite the file
            for (Task task : tasks) {
                String line = getString(task);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the provided task list to the file.
     *
     * @param task The list of tasks to save.
     */
    public String getString(Task task) {
        String taskType = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.getDone() ? "1" : "0";
        String line = taskType + " | " + status + " | " + task.getDesc();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        if (task instanceof Deadline deadline) {
            line += " | " + deadline.getBy().format(formatter);
        } else if (task instanceof Event event) {
            line += " | " + event.getFrom().format(formatter) + " | " + event.getTo().format(formatter);
        }
        return line;
    }

}
