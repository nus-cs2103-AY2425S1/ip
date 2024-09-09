package sigma.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import sigma.exception.SigmaException;
import sigma.task.DeadlineTask;
import sigma.task.EventTask;
import sigma.task.Task;
import sigma.task.TodoTask;


/**
 * Represents the storage of tasks.
 * Handles the reading and writing of tasks to a file.
 * The file is stored in the file path specified by the user.
 * The file is created if it does not exist.
 * The file is read and written to in the format:
 * [task type] | [status] | [description] | [date]
 * where task type is T for ToDoTask, D for DeadlineTask, and E for EventTask.
 * where status is X if the task is done, and a space if the task is not done.
 * where date is the date of the task in the format "MMM d yyyy, HH:mm".
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for Storage.
     * Initializes the file path.
     *
     * @param filePath File path to store data.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
        load(filePath);
    }

    /**
     * Writes the tasks to the file.
     * Uses inheritance to get Date string.
     *
     * @param items List of tasks to write to the file.
     */
    public void write(ArrayList<Task> items) {
        File data = new File(filePath);
        assert data != null : "File cannot be null";
        try {
            FileWriter writer = new FileWriter(data);
            assert writer != null : "Writer cannot be null";
            for (Task item : items) {
                assert item != null : "Item cannot be null";
                String date = item.getDate();
                writer.write(String.format("%s | %s | %s | %s \n",
                        item.getTaskType(), item.getStatusString(), item.getDescription(), date));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the file to store data.
     * Creates a file to store data if none exists.
     *
     * @param filePath File path to store data.
     * @return Success message
     */
    private String load(String filePath) {
        // Creates a file to store data if none exists
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                return "What the sigma? Error creating file!";
            }
        } else {
            return "SLAY! Loading up your saved tasks!";
        }
        return "SLAY! File created!";
    }

    /**
     * Reads the tasks from the file.
     * Creates a list of tasks from the file.
     * Handles the reading of tasks from the file.
     * Handles the case where the file is not found.
     * Handles the case where the file is empty.
     *
     * @param data File to read from.
     * @return List of tasks read from the file.
     */
    public ArrayList<Task> read(File data) {
        ArrayList<Task> items = null;
        try {
            items = new ArrayList<>();
            Scanner fileInput = new Scanner(data);
            while (fileInput.hasNextLine()) {
                // Loading from file
                String line = fileInput.nextLine();
                assert line != null : "Line cannot be null";
                String[] split = line.split(" \\| ");
                String type = split[0];
                boolean status = split[1].equals("X");
                String desc = split[2];
                String date = split[3];
                Task item = createTaskFromFile(type, desc, date);
                item.setCompleted(status);
                items.add(item);
            }
            fileInput.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading file.");
            e.printStackTrace();
        } catch (SigmaException e) {
            System.out.println(e.getMessage());
        }
        assert items != null : "Items cannot be null. If it is, an error occurred while reading file.";
        return items;
    }

    /**
     * Creates a task from the file.
     *
     * @param type Task type.
     * @param desc Task description.
     * @param date Task date.
     * @return Task created from the file.
     * @throws SigmaException If the task type is invalid, or contains invalid date format.
     */
    private Task createTaskFromFile(String type, String desc, String date) throws SigmaException {
        Task item;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        switch (type) {
        case "T":
            item = new TodoTask(desc);
            break;
        case "D":
            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(date.strip(), dateFormat);
            } catch (DateTimeParseException e) {
                throw new SigmaException("What the sigma? File contains invalid date format!");
            }
            item = new DeadlineTask(desc, dateTime);
            break;
        case "E":
            String[] dates = date.split("-");
            assert dates != null : "Dates cannot be null";
            assert dates.length > 0 : "Dates length cannot be 0";
            LocalDateTime from;
            LocalDateTime to;
            try {
                from = LocalDateTime.parse(dates[0].strip(), dateFormat);
                to = LocalDateTime.parse(dates[1].strip(), dateFormat);
            } catch (DateTimeParseException e) {
                throw new SigmaException("What the sigma? File not saved correctly, invalid date format!");
            }
            item = new EventTask(desc, from, to);
            break;
        default:
            throw new SigmaException("What the sigma? Invalid task type!");
        }
        return item;
    }
}
