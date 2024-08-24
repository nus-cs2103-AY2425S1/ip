package jackson.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jackson.tasks.Deadline;
import jackson.tasks.Event;
import jackson.tasks.Task;
import jackson.tasks.Todo;

/**
 * Class to handle reading and writing to save file.
 */
public class Storage {
    /* Regex to read tasks from text file */
    private static final Pattern TASK_FORMAT = Pattern.compile("\\[([TED])]\\[([X ])] "
            + "([\\w ]+\\w)(?: \\((?:from|by): ([\\w\\d, :]+)\\)*)?(?: \\| to: ([\\w\\d, :]+)\\))?");

    /* String path to save file */
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Saves task list to specified save file.
     * if IOException raised, will print error statement.
     * @param taskList {@code TaskList} object that the tasks are written from
     */
    public void save(TaskList taskList) {
        File f = new File(this.path); // new file from path
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < taskList.getSize(); i++) {
                fw.write(taskList.getTask(i).toString()); // write each task
                if (i < taskList.getSize() - 1) {
                    fw.write("\n"); // add newline if not last task
                }
            }
            fw.close();
        } catch (IOException e) {
            // when file cannot be accessed/written to
            System.out.println("Error! Please check your file permissions!");
        }
    }

    /**
     * Loads task list from save file.
     * Will skip tasks if task format is wrong, will not load the full list if save file cannot be opened.
     * @param taskList {@code TaskList} object to load tasks into
     */
    public void load(TaskList taskList) {
        File f = new File(this.path); // new file object
        Matcher m;
        Task t;
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                m = TASK_FORMAT.matcher(sc.nextLine()); // take in next task, match to regex
                if (m.matches()) {
                    // means task is recognised as valid format
                    switch (m.group(1)) {
                    case "T": // todo
                        t = new Todo(m.group(3));
                        if (m.group(2).equals("X")) {
                            t.mark(); // mark if completed
                        }
                        taskList.addTask(t);
                        break;
                    case "D": // deadline
                        t = new Deadline(m.group(3), m.group(4));
                        if (m.group(2).equals("X")) {
                            t.mark(); // mark if completed
                        }
                        taskList.addTask(t);
                        break;
                    case "E": // event
                        t = new Event(m.group(3), m.group(4), m.group(5));
                        if (m.group(2).equals("X")) {
                            t.mark(); // mark if completed
                        }
                        taskList.addTask(t);
                        break;
                    default:
                        break;
                    }
                } else {
                    // no matches, we do not load the task and skip
                    System.out.println("Error with loading task, skipping to next one...");
                }
            }
            sc.close(); // close scanner
            System.out.println("Tasks loaded successfully!");
        } catch (IOException e) {
            // file cannot be access or read from
            // skip loading completely
            System.out.printf("IOException! %s! Aborting loading...\n", e);
        } catch (Exception e) {
            // some other exception besides IOException
            System.out.printf("Unknown Error! %s! Aborting loading...\n", e);
        }
    }
}
