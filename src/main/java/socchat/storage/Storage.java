package socchat.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Parser.Parser;
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
    public static ArrayList<Task> processStorageLine() throws SocchatException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            assert filePath != null;
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] strToken = line.split("\\|");

                assert strToken.length == 6;
                String type = strToken[0].trim();
                String done = strToken[1].trim();
                String desc = strToken[2].trim();
                String from = strToken[3].trim();
                String to = strToken[4].trim();
                String tag = strToken[5].trim();

                Task t;
                boolean isDone;
                isDone = done.equals("Done");

                if (type.equals("T")) {
                    t = createTodo(desc, isDone, tag);
                } else if (type.equals("E")) {

                    t = loadEvent(desc, isDone, from, to, tag);
                } else if (type.equals("D")) {

                    t = createDeadline(desc, isDone, from, tag);
                } else {
                    throw new SocchatException("Unknown task type: " + type);
                }
                tasks.add(t);
            }
        } catch (FileNotFoundException e) {
            throw new SocchatException("Storage file not found!");
        }

        return tasks;

    }

    public static Task createTodo(String desc, boolean isDone, String tag) {
        if (!(tag.isEmpty())) {
            return new Todo(desc, isDone);
        }
        return new Todo(desc, isDone, tag);
    }

    public static Task loadEvent(String desc, boolean isDone, String from, String to, String tag) {
        try {
//            String[] dateToken = date.split("to");
            LocalDateTime formatted_from = Parser.parseDate(from.trim());
            LocalDateTime formatted_to = Parser.parseDate(to.trim());
            if (!(tag.isEmpty())) {
                return new Event(desc, formatted_from, formatted_to, isDone, tag);
            }
            return new Event(desc, formatted_from, formatted_to, isDone);
        } catch (SocchatException e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    public static Task createDeadline(String desc, boolean isDone, String date, String tag) {
        try {
            LocalDateTime by = Parser.parseDate(date);
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
