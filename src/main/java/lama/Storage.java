package lama;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import lama.task.Deadline;
import lama.task.Event;
import lama.task.Task;
import lama.task.Todo;


/**
 * Represent the storage where the data being saved.
 * Handles loading and saving tasks to and from a file.
 */
public class Storage {
    private static final String DATE_FORMAT = "yyyy-MM-dd HHmm";
    private static final String DONE = "1";

    private final String path;


    /**
     * Construct a Storage object with the specified file path given.
     *
     * @param path String of file path where tasks will be saved and loaded from.
     */
    public Storage(String path) {
        assert path != null : "File path should not be null";
        assert !path.trim().isEmpty() : "File path should not be empty";
        this.path = path;
    }

    /**
     * Load tasks from the file specified by the file path.
     * Create a new file if it doesn't exist.
     * Convert the data in the file to task list.
     *
     * @return ArrayList of task representing the tasks loaded from the list of tasks being saved.
     * @throws LamaException Thrown if an error occurs while reading the file or the file contains invalid data.
     */
    public ArrayList<Task> loadTask() throws LamaException {

        File file = new File(path);
        if (!file.exists()) {
            createNewFile(file);
        }

        ArrayList<Task> list = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(parseTask(line));
            }
        } catch (IOException e) {
            throw new LamaException("Error reading file: " + e.getMessage());
        }

        return list;
    }

    private void createNewFile(File file) throws LamaException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new LamaException("Error creating new file: " + e.getMessage());
        }
    }

    private Task parseTask(String line) throws LamaException {
        String[] words = line.split(" \\| ");
        switch (words[0]) {
        case "T":
            return parseTodoTask(words);
        case "D":
            return parseDeadlineTask(words);
        case "E":
            return parseEventTask(words);
        default:
            throw new LamaException("Invalid data format");
        }
    }

    private Todo parseTodoTask(String[] words) {
        Todo todo = new Todo(words[2]);
        if (words[1].equals(DONE)) {
            todo.markAsDone();
        }
        return todo;
    }

    private Deadline parseDeadlineTask(String[] words) throws LamaException {
        try {
            LocalDate date = LocalDate.parse(words[3]);
            Deadline deadline = new Deadline(words[2], date);
            if (words[1].equals(DONE)) {
                deadline.markAsDone();
            }
            return deadline;
        } catch (DateTimeException e) {
            throw new LamaException("Date format should follow yyyy-MM-dd");
        }
    }

    private Event parseEventTask(String[] words) throws LamaException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDateTime from = LocalDateTime.parse(words[3], formatter);
            LocalDateTime to = LocalDateTime.parse(words[4], formatter);
            Event event = new Event(words[2], from, to);
            if (words[1].equals(DONE)) {
                event.markAsDone();
            }
            return event;
        } catch (DateTimeException e) {
            throw new LamaException("Date time format should follow yyyy-MM-dd HHmm");
        }
    }


    /**
     * Saves the current task list into the file specified by file path.
     * Overwrites the existing file content.
     *
     * @param taskList Task list containing tasks to be saved.
     * @throws LamaException Thrown if an error occurs while writing the file.
     */
    public void saveTasks(TaskList taskList) throws LamaException {
        assert taskList != null : "Task list should not be null";
        try {
            FileWriter fileWriter = new FileWriter(path);

            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                fileWriter.write(task.toFileFormat() + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new LamaException("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Append a new task to the file specified by file path.
     *
     * @param task Task to be added to the file.
     * @throws LamaException Thrown if an error occurs while writing the file.
     */
    public void addTask(Task task) throws LamaException {
        assert task != null : "Task should not be null";
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write(task.toFileFormat() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new LamaException("Error writing file: " + e.getMessage());
        }
    }
}
