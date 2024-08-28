package blitz;

/* My import */
import exception.BlitzException;
import exception.BlitzIOException;
import task.Task;

/* System import */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path;

    /**
     * Constructs a new Storage object with specified file path.
     *
     * @param path File path to read and store the tasks.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Writes a single task to the file (by appending).
     *
     * @param task Task object to be written to the file.
     * @throws BlitzException If I/O error occurs.
     */
    public void writeOneToFile(Task task) throws BlitzException {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(task.getType().equals("Empty") ? "" : task.taskToString());
            fw.close();
        } catch (IOException e) {
            throw new BlitzIOException("Failed to write to database");
        }
    }

    /**
     * Writes all tasks from the provided TaskList to the file (overwrite)
     *
     * @param list TaskList that contains all the Task objects to be written to the file.
     * @throws BlitzException If I/O error occurs.
     */
    public void writeAllToFile(TaskList list) throws BlitzException {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            throw new BlitzIOException("Failed to write to database");
        }

        if (!list.isEmpty()) {
            ArrayList<Task> temp = list.getAllTask();
            for (Task task : temp) {
                writeOneToFile(task);
            }
        }
    }

    /**
     * Reads the file and returns the file content as a TaskList object.
     *
     * @param ui Ui object that is used for printing errors if any.
     * @return TaskList object that containing the tasks read from the file.
     * @throws BlitzException If I/O error or corrupted file content format.
     */
    public TaskList readFromFile(Ui ui) {
        File f = new File(path);
        TaskList list = new TaskList(new ArrayList<>());

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                list.addTask(Task.stringToTask(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            try {
                writeAllToFile(new TaskList(new ArrayList<>()));
            } catch (BlitzException e2) {
                ui.printError(e2);
            }
        } catch (BlitzException e) {
            ui.printError(e);
        }

        return list;
    }
}
