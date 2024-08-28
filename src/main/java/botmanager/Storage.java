package botmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

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
    private final String TASK_FILE_PATH = "data/tasks.txt";
    private final String DIRECTORY_PATH = "data";

    /**
     * Reads tasks from data file and adds them to the given <code>taskList</code>.
     *
     * @param taskList Task list to add tasks to.
     * @throws FileNotFoundException If data file is not found.
     */
    public void loadTaskList(TaskList taskList) throws FileNotFoundException {
        File f = new File(TASK_FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String data = s.nextLine();
            taskList.addTask(fromData(data));
        }
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
     * If the directory/file cannot be initialised, the program exits.
     */
    public void initFile() {
        try {
            File dir = new File(DIRECTORY_PATH);
            dir.mkdir();
            File f = new File(TASK_FILE_PATH);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error initialising file, BotManager will now exit");
            System.exit(0);
        }
    }

    /**
     * Saves the tasks in a <code>taskList</code> to the data file.
     *
     * @param taskList Task list to save.
     * @throws IOException If file cannot be saved.
     */
    public void saveTaskList(TaskList taskList) throws IOException {
       FileWriter fw = new FileWriter(TASK_FILE_PATH);
       fw.write(taskList.toData());
       fw.close();
    }
}
