package jackson.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jackson.exceptions.InvalidArgumentException;
import jackson.tasks.Deadline;
import jackson.tasks.Event;
import jackson.tasks.Task;
import jackson.tasks.Todo;

/**
 * Class to handle reading and writing to save file.
 */
public class Storage {
    // Regex to read tasks from text file
    private static final Pattern TASK_FORMAT = Pattern.compile("\\[([TED])]\\[([X ])] "
            + "([\\w ]+\\w)(?: \\((?:from|by): ([\\w\\d, :]+)\\)*)?(?: \\| to: ([\\w\\d, :]+)\\))?");

    // String path to save file
    private String path;

    /**
     * Constructs Storage instance.
     * @param path save file path String to save and read from.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Saves task list to specified save file.
     * if IOException raised, will print error statement.
     * @param taskList {@code TaskList} object that the tasks are written from.
     */
    public String save(TaskList taskList) throws IOException {
        File f = new File(this.path); // new file from path
        String output = "";

        assert f.exists() : "Error, file does not exist!";

        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < taskList.getSize(); i++) {
            fw.write(taskList.getTask(i).toString()); // write each task
            fw.write("\n"); // add newline if not last task
        }
        fw.close();
        output += "Task list saved successfully!\n";

        // confirm file has been created either way
        assert f.exists() : "Error processing file! Please check your directory and debug from there!\n";
        return output.strip();
    }

    /**
     * Loads task list from save file.
     * Will skip tasks if task format is wrong, will not load the full list if save file cannot be opened.
     * @param taskList {@code TaskList} object to load tasks into.
     * @return String representing success or failure of loading.
     */
    public String load(TaskList taskList) {
        File f = new File(this.path); // new file object
        Task t;
        String output;

        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
                return "Making new save file!";
            }
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                t = loadIndividual(sc.nextLine());
                taskList.addTask(t);
            }
            sc.close(); // close scanner
            output = "Tasks loaded successfully!\n";
        } catch (IOException e) {
            // file cannot be access or read from
            // skip loading completely
            output = String.format("IOException! %s! Aborting loading...\n", e);
        } catch (Exception e) {
            // some other exception besides IOException
            output = String.format("Unknown Error! %s! Aborting loading...\n", e);
        }
        return output;
    }

    /**
     * Handles loading for each individual line.
     * @return String response.
     */
    public Task loadIndividual(String line) throws InvalidArgumentException {
        Task t;
        Matcher m = TASK_FORMAT.matcher(line); // take in next task, match to regex
        if (!m.matches()) {
            // no matches, we do not load the task and skip
            return null;
        }

        // means task is recognised as valid format
        switch (m.group(1)) {
        case "T": // todo
            t = new Todo(m.group(3));
            break;
        case "D": // deadline
            t = new Deadline(m.group(3), m.group(4));
            break;
        case "E": // event
            t = new Event(m.group(3), m.group(4), m.group(5)); // can throw error if dates are invalid
            break;
        default:
            // task type not recognized, skip task
            return null;
        }

        if (m.group(2).equals("X")) {
            t.mark(); // mark if completed
        }
        return t;
    }
}
