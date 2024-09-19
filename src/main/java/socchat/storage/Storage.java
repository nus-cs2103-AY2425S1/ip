package socchat.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import parser.DateParser;
import socchat.SocchatException;
import socchat.task.Task;
import socchat.task.deadline.Deadline;
import socchat.task.event.Event;
import socchat.task.todo.Todo;

/**
 * Handles the loading and saving of tasks from/to a file.
 * Provides methods to read tasks from a file and write tasks to a file.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     * Initializes the file path for storing and loading tasks.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * Parses the file content and creates Task objects based on the data.
     *
     * @return a list of tasks loaded from the file
     * @throws SocchatException if the file is not found or if there is an error parsing the file
     */
    public static ArrayList<Task> loadTask() throws SocchatException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = parseTaskFromFile(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new SocchatException("Storage file not found!");
        }
        return tasks;
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line the line read from the storage file
     * @return the corresponding Task object
     * @throws SocchatException if the task type is unknown or if the format is invalid
     */
    private static Task parseTaskFromFile(String line) throws SocchatException {
        String[] strToken = line.split("\\|");

        assert strToken.length == 6;

        String type = strToken[0].trim();
        boolean isDone = "Done".equals(strToken[1].trim());
        String desc = strToken[2].trim();
        String from = strToken[3].trim();
        String to = strToken[4].trim();
        String tag = strToken[5].trim();

        switch (type) {
        case "T":
            return createTodo(desc, isDone, tag);
        case "E":
            return createEvent(desc, isDone, from, to, tag);
        case "D":
            return createDeadline(desc, isDone, from, tag);
        default:
            throw new SocchatException("Unknown task type: " + type);
        }
    }


    /**
     * Creates a Todo task from the given parameters.
     *
     * @param desc   the task description
     * @param isDone the completion status
     * @param tag    the task tag
     * @return the created {@link Todo} task
     */
    private static Task createTodo(String desc, boolean isDone, String tag) {
        if (!(tag.isEmpty())) {
            return new Todo(desc, isDone, tag);
        }
        return new Todo(desc, isDone);
    }

    /**
     * Creates an Event task from the given parameters.
     *
     * @param desc   the task description
     * @param isDone the completion status
     * @param from   the start date of the event
     * @param to     the end date of the event
     * @param tag    the task tag
     * @return the created {@link Event} task
     * @throws SocchatException if there is an error parsing the dates
     */
    private static Task createEvent(String desc, boolean isDone, String from, String to, String tag) {
        try {
            LocalDate formatted_from = DateParser.parseDate(from.trim());
            LocalDate formatted_to = DateParser.parseDate(to.trim());
            assert !formatted_from.isAfter(formatted_to);

            if (!(tag.isEmpty())) {
                return new Event(desc, formatted_from, formatted_to, isDone, tag);
            }
            return new Event(desc, formatted_from, formatted_to, isDone);
        } catch (SocchatException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Creates a Deadline task from the given parameters.
     *
     * @param desc   the task description
     * @param isDone the completion status
     * @param date   the deadline date
     * @param tag    the task tag
     * @return the created {@link Deadline} task
     * @throws SocchatException if there is an error parsing the date
     */
    private static Task createDeadline(String desc, boolean isDone, String date, String tag) {
        try {
            LocalDate by = DateParser.parseDate(date);
            if (!(tag.isEmpty())) {
                return new Deadline(desc, by, isDone, tag);
            }
            return new Deadline(desc, by, isDone);
        } catch (SocchatException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Updates the storage file with the specified list of tasks.
     * Writes tasks to the file, either appending to the existing content or rewriting the file.
     *
     * @param tasks the list of tasks to be written to the file
     * @param needAppend if true, appends the last task to the file; otherwise, overwrites the file with all tasks
     */
    public static void update(ArrayList<Task>tasks, Boolean needAppend) {
        ArrayList<Task> content = new ArrayList<>();
        if (needAppend) {
            content.add(tasks.get(tasks.size() - 1)); // Append only the last added task
        } else {
            content = tasks; // Rewrite the tasks
        }

        try (FileWriter writer = new FileWriter(filePath, needAppend)) {
            for (Task t : content) {
                writer.write(t.toSave());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
