package david.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import david.exceptions.DavidCacheException;
import david.exceptions.DavidInvalidDateTimeException;
import david.parser.DateParser;
import david.task.DeadlineTask;
import david.task.EventTask;
import david.task.Task;
import david.task.TaskList;
import david.task.TodoTask;


/*
Format of cached data
TaskType | completed | eventName | (optional) by/from | (optional) to
   0          1           2               3                 4
 */

/**
 * Defines a Storage class to store Data
 */
public class Storage {
    private static final String TASK_COMPLETED = "1";
    private static final String CACHE_PATH = "./database.txt";


    /**
     * Constructor to instantiate a new Storage object
     */
    public Storage() {
    }

    /**
     * Loads the cached list of tasks if it exists. If it does not exist, a new cache will be created
     *
     * @return arraylist of tasks.
     */
    public TaskList loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File f = new File(this.CACHE_PATH);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Task t = parseTask(sc.nextLine());

                if (t == null) {
                    break;
                }

                tasks.add(t);
            }
        } catch (FileNotFoundException e) {
            //file does not exist
            this.createNewCache();
        }
        TaskList taskList = new TaskList(tasks);
        return taskList;
    }

    /**
     * Parses the input line and returns the task represented by the current line
     *
     * @param s event name and details of the string input.
     * @return Task corresponding to the input string.
     */
    private Task parseTask(String s) {
        String[] taskInformation = s.split("\\|");
        Task t = null;

        switch (taskInformation[0]) {
        case "T":
            //Details a "TODO" task
            t = new TodoTask(taskInformation[2], false);
            break;
        case "D":
            //Details a "Deadline task"
            try {
                LocalDateTime byDate = DateParser.getDate(taskInformation[3]);
                t = new DeadlineTask(taskInformation[2], byDate, false);
                break;
            } catch (DavidInvalidDateTimeException e) {
                System.out.println("error");
            }
            break;
        case "E":
            //Details a "Event" task
            try {
                LocalDateTime fromDate = DateParser.getDate(taskInformation[3]);
                LocalDateTime toDate = DateParser.getDate(taskInformation[4]);
                t = new EventTask(taskInformation[2], fromDate, toDate, false);
                break;
            } catch (DavidInvalidDateTimeException e) {
                System.out.println("error");
            }
            break;
        default:
            break;
        }

        if (t != null && taskInformation[1].equals(TASK_COMPLETED)) {
            t.markAsDone();
        }

        return t;
    }

    /**
     * Creates a new cache inside Data folder if it does not exist
     */
    private void createNewCache() {
        assert this.CACHE_PATH.length() != 0 : "No path specified";
        File newFile = new File(CACHE_PATH);

        // Creates a new file for caching data.
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("File cannot be created");
        }
    }

    /**
     * Overwrites the file with the list of current tasks
     *
     * @throws DavidCacheException if the named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void saveTask(TaskList tasks) throws DavidCacheException {
        try {
            FileWriter writer = new FileWriter(this.CACHE_PATH, false);

            String text = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                Task t = tasks.getTask(i);
                text += t.toCacheString() + "\n";

            }
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            //Named file is invalid/ unavailable
            throw new DavidCacheException();
        }
    }


}
