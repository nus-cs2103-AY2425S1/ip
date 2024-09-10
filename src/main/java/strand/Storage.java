package strand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import strand.exception.StrandException;
import strand.exception.StrandFileNotFoundException;
import strand.exception.StrandWrongCommandException;
import strand.task.Deadline;
import strand.task.Event;
import strand.task.Task;
import strand.task.Todo;

/**
 * The {@code Storage} class handles the loading and saving of tasks to and from
 * a file. It is responsible for reading tasks from the file during initialization
 * and updating the file whenever the task list is modified.
 * <p>
 * This class may throw exceptions if the file is not found or if there is an
 * error in processing the data.
 * </p>
 */
public class Storage {
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private final String filepath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filepath The path to the file where tasks are stored.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Converts a line of text from the file into a corresponding {@code Task} object.
     *
     * @param line A line from the file representing a task.
     * @return A {@code Task} object created from the line.
     * @throws StrandException If the task type is unrecognized or the format is incorrect.
     */
    private static Task parseTaskFromLine(String line) throws StrandException {
        String[] split = line.split(" \\| ");
        Task newTask;
        switch (split[0]) {
        case TODO_TYPE -> newTask = new Todo(split[2]);
        case DEADLINE_TYPE -> newTask = new Deadline(split[2], split[4]);
        case EVENT_TYPE -> newTask = new Event(split[2], split[4], split[5]);
        default -> throw new StrandWrongCommandException();
        }
        if (split[1].equals("1")) {
            newTask.markAsDone();
        }
        if (!split[3].equals(Task.PriorityEnum.NONE.toString())) {
            newTask.markPriority(Task.PriorityEnum.valueOf(split[3]));
        }
        return newTask;
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return An {@code ArrayList} of {@code Task} objects loaded from the file.
     * @throws StrandException If the file is not found or there is an error reading the file.
     */
    public ArrayList<Task> load() throws StrandException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.filepath);
            if (file.exists()) {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    Task newTask = parseTaskFromLine(line);
                    tasks.add(newTask);
                }
            }
        } catch (IOException e) {
            throw new StrandFileNotFoundException();
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param listOfTasks A string representing the list of tasks to be saved.
     * @throws StrandException If there is an error creating or writing to the file.
     */
    public void save(String listOfTasks) throws StrandException {
        assert listOfTasks != null : "Cannot save null list of tasks";
        try {
            File file = new File(this.filepath);
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    if (!parentFile.mkdir()) {
                        throw new IOException("Error creating parent file");
                    }
                }
                if (!file.createNewFile()) {
                    throw new IOException("Error creating data file");
                }
            }
            FileWriter fileWriter = new FileWriter(this.filepath);
            fileWriter.write(listOfTasks);
            fileWriter.close();
        } catch (IOException e) {
            throw new StrandFileNotFoundException();
        }
    }
}
