package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import enums.StatusMessage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Class to handle reading and writing from data file.
 *
 * @author dwsc37
 */
public class Storage {
    private static final String TASK_FILE_PATH = "data/tasks.txt";
    private static final String DIRECTORY_PATH = "data";

    /**
     * Reads tasks from data file and adds them to the given <code>taskList</code>.
     *
     * @param taskList Task list to add tasks to.
     * @return <code>Response</code> describing the file loaded.
     * @throws FileNotFoundException If data file is not found.
     */
    public Response loadTaskList(TaskList taskList) throws FileNotFoundException {
        File f = new File(TASK_FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String data = s.nextLine();
            taskList.addTask(fromData(data));
        }
        return new Response(StatusMessage.FILE_LOAD_SUCCESS.getMessage(), false);
    }

    private Task fromData(String data) {
        String[] args = data.split(" \\| ");
        return switch (args[0]) {
        case "T" -> new Todo(args[1], Boolean.parseBoolean(args[2]));
        case "D" -> new Deadline(args[1], Boolean.parseBoolean(args[2]), LocalDate.parse(args[3]));
        case "E" -> new Event(args[1],
                Boolean.parseBoolean(args[2]),
                LocalDate.parse(args[3]),
                LocalDate.parse(args[4]));
        default -> null;
        };
    }

    /**
     * Initialises data directory and an empty data file.
     *
     * @return <code>Response</code> describing the action taken.
     */
    public Response initFile() {
        try {
            File dir = new File(DIRECTORY_PATH);
            dir.mkdir();
            File f = new File(TASK_FILE_PATH);
            f.createNewFile();
            return new Response(StatusMessage.FILE_INIT_SUCCESS.getMessage(), false);
        } catch (IOException e) {
            return new Response(StatusMessage.FILE_INIT_FAILURE.getMessage(), true);
        }
    }

    /**
     * Saves the tasks in a <code>taskList</code> to the data file.
     *
     * @param taskList Task list to save.
     * @return <code>Response</code> describing the action taken.
     */
    public Response saveTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(TASK_FILE_PATH);
            fw.write(taskList.toData());
            fw.close();
            return null;
        } catch (IOException e) {
            return new Response(StatusMessage.DATA_SAVE_FAILURE.getMessage(), false);
        }
    }
}
