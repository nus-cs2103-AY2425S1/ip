package yoda;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import yoda.exceptions.YodaException;
import yoda.tasks.Deadline;
import yoda.tasks.Event;
import yoda.tasks.Task;
import yoda.tasks.Todo;

/**
 * Handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The path of the file to save and load tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws YodaException If there is an error when writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws YodaException {
        assert tasks != null : "Tasks list should not be null";
        File file = new File(filePath);

        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                assert task != null : "Task should not be null";
                fileWriter.write(task.getData() + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            throw new YodaException("Error when writing file :" + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     * @throws YodaException If there is an error when reading the file or parsing the tasks.
     */
    public ArrayList<Task> loadTasks() throws YodaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] splitInput = input.split(" \\| ");
                String type = splitInput[0];
                boolean isDone = splitInput[1].equals("1");
                String description = splitInput[2];

                Task newTask = null;
                switch (type) {
                case "T":
                    newTask = new Todo(description);
                    break;
                case "D":
                    LocalDate by = LocalDate.parse(splitInput[3]);
                    newTask = new Deadline(description, by);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(splitInput[3]);
                    LocalDateTime to = LocalDateTime.parse(splitInput[4]);
                    newTask = new Event(description, from, to);
                    break;
                default:
                    throw new YodaException("Encountered error: Could not parse saved tasks");
                }

                if (isDone && newTask != null) {
                    newTask.markDone();
                }
                tasks.add(newTask);

            }
        } catch (Exception e) {
            throw new YodaException("Encountered error: " + e.getMessage());
        }
        return tasks;
    }
}
