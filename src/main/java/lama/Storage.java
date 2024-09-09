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
        try {

            ArrayList<Task> list = new ArrayList<>();
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split(" \\| ");
                    switch (words[0]) {
                    case "T":
                        Todo todo = new Todo(words[2]);
                        if (words[1].equals("1")) {
                            todo.markAsDone();
                        }
                        list.add(todo);
                        break;

                    case "D":
                        try {
                            LocalDate date = LocalDate.parse(words[3]);
                            Deadline deadline = new Deadline(words[2], date);
                            if (words[1].equals("1")) {
                                deadline.markAsDone();
                            }
                            list.add(deadline);
                        } catch (DateTimeException e) {
                            throw new LamaException("Date format should follow yyyy-mm-dd");
                        }
                        break;

                    case "E":
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            LocalDateTime from = LocalDateTime.parse(words[3], formatter);
                            LocalDateTime to = LocalDateTime.parse(words[4], formatter);
                            Event event = new Event(words[2], from, to);
                            if (words[1].equals("1")) {
                                event.markAsDone();
                            }
                            list.add(event);
                        } catch (DateTimeException e) {
                            throw new LamaException("Date time format should follow yyyy-MM-dd HHmm");
                        }
                        break;

                    default:
                        throw new LamaException("Invalid data format");
                    }
                }
                scanner.close();
            }

            return list;
        } catch (IOException e) {
            throw new LamaException("Error reading file: " + e.getMessage());
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
