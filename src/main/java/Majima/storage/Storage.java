package Majima.storage;

import Majima.MajimaException;
import Majima.task.Deadline;
import Majima.task.Event;
import Majima.task.Task;
import Majima.task.Todo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks saved in Majima.txt.
     * Tasks not formatted properly will be skipped, and will return an error. This
     * does not terminate the program.
     *
     * @return A list of tasks from the saved Majima.txt file
     */
    public List<Task> load() throws MajimaException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new MajimaException("There was an error loading tasks from file: "
                    + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks as Majima.txt
     * Overwrites old 'Majima.txt's.
     *
     * @param tasks A List<Task> to be saved as Majima.txt
     */
    public void save(List<Task> tasks) throws MajimaException {
        try (PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Task task : tasks) {
                wr.println(task.toFileString());
            }
        } catch (IOException e) {
            throw new MajimaException("There was an error saving tasks to file: "
                    + e.getMessage());
        }
    }

    /**
     * Helper function to load tasks from Majima.txt.
     * As data is saved in a different format in Majima.txt, this function
     * helps to parse data in that different format, and print them back
     * when Majima.java is run to give back the updated list of tasks which
     * were saved. Handles errors and corrupted data.
     *
     * @param line A line from Majima.txt which contains info of a saved task
     * @return A Task of its corresponding type.
     */
    private Task parseTask(String line) throws MajimaException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {

            throw new MajimaException("Invalid task format: " + line
                    + "This may be a sign of corrupted or invalid data.");
        }

        String type = parts[0].trim();
        String statusIcon = parts[1].trim();
        String description = parts[2].trim();

        Task task;

        switch (type) {
            case "[T]":
                task = new Todo(description);
                break;
            case "[D]":
                if (parts.length < 4) {
                    throw new MajimaException("Invalid task format: " + line
                            + "This may be a sign of corrupted or invalid data.");
                }
                String deadline = parts[3].trim();
                deadline = convertDateFormat(deadline);
                task = new Deadline(description, deadline);
                break;
            case "[E]":
                if (parts.length < 5) {
                    throw new MajimaException("Invalid task format: " + line
                            + "This may be a sign of corrupted or invalid data.");
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, from, to);
                break;
            default:
                throw new MajimaException("Invalid task format: " + line
                        + "This may be a sign of corrupted or invalid data.");
        }

        if (statusIcon.equals("[X]")) {
            task.markAsDone();
        } else if (statusIcon.equals("[ ]")) {
            task.markAsUndone();
        } else {
            throw new MajimaException("Invalid task format: " + line
                    + "This may be a sign of corrupted or invalid data.");
        }
        return task;
    }

    /**
     * A helper method to convert the format of deadlines in Deadline tasks.
     *
     * @param dateString a String in the format of INPUT_FORMAT (see static final value above)
     * @return a String in the format of OUTPUT_FORMAT, as needed by Deadline Tasks.
     */
    public static String convertDateFormat(String dateString) throws DateTimeParseException {
        LocalDateTime dateTime = LocalDateTime.parse(dateString, INPUT_FORMAT);
        return dateTime.format(OUTPUT_FORMAT);
    }
}