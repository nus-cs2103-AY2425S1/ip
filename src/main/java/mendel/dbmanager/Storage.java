package mendel.dbmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import mendel.discretetask.Deadline;
import mendel.discretetask.Event;
import mendel.discretetask.Task;
import mendel.discretetask.Todo;
import mendel.mendelexception.ServerError;
import mendel.metacognition.TaskList;

/**
 * The Storage class handles the interaction with the file system to manage storage
 * and retrieval of data from a database file. It manages the creation, loading,
 * and updating of tasks within a given file path.
 */
public class Storage {
    private final String filePath;
    private final File db;

    /**
     * Constructs a Storage object associated with a specific file path. If the file does not exist,
     * it attempts to create the file and its directories.
     *
     * @param filePath the path of the file to store the tasks.
     * @throws ServerError if the file cannot be created due to an IO error.
     */
    public Storage(String filePath) throws ServerError {
        this.filePath = filePath;
        File db = new File(filePath);
        this.db = db;
        if (!db.exists()) {
            try {
                String[] dirLst = filePath.split("/");
                new File(dirLst[0]).mkdir();
                db.createNewFile();
            } catch (IOException e) {
                throw new ServerError("File cannot be created. Check data directory");
            }
        }
    }

    /**
     * Copies data from the database file into the TaskList.
     * Each line is parsed into the appropriate Task object (Todo, Deadline, or Event).
     *
     * @param taskStorage the TaskList object to load tasks into.
     * @throws ServerError if the file is not found or if the task type in the file is unidentifiable.
     */
    public void loadInto(TaskList taskStorage) throws ServerError {
        try {
            Scanner s = new Scanner(this.db);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] lineSegments = line.split(" \\| ");
                if (lineSegments[0].equals("T")) {
                    taskStorage.silencedAdd(Todo.loadOf(lineSegments[1].equals("1"), lineSegments[2]));
                } else if (lineSegments[0].equals("D")) {
                    taskStorage.silencedAdd(Deadline.loadOf(lineSegments[1].equals("1"),
                            lineSegments[2], lineSegments[3]));
                } else if (lineSegments[0].equals("E")) {
                    taskStorage.silencedAdd(Event.loadOf(lineSegments[1].equals("1"), lineSegments[2], lineSegments[3],
                            lineSegments[4]));
                } else {
                    throw new ServerError(String.format("Unidentifiable task type %s. Ensure correct task type",
                            lineSegments[0]));
                }

            }
        } catch (FileNotFoundException e) {
            throw new ServerError("File cannot be found. Check database is not removed");
        }
    }

    /**
     * Appends a new entry for a task in the database file.
     *
     * @param task the Task object to be added to the file.
     * @param isNew a boolean indicating whether the database is new created to avoid formatting error.
     * @throws ServerError if the file cannot be found or an IO error occurs during writing.
     */
    public void create(Task task, boolean isNew) throws ServerError {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            if (isNew) {
                fw.write(task.parseDetailsForDB());
            } else {
                fw.write("\n" + task.parseDetailsForDB());
            }
            fw.close();
        } catch (IOException e) {
            throw new ServerError("File cannot be found. Check database is not removed");
        }
    }
    /**
     * Updates the database file with the current state of the TaskList. The method replaces
     * the file with the tasks stored in the provided TaskList.
     *
     * @param taskStorage the TaskList containing the tasks to be written to the file.
     * @throws ServerError if the file cannot be found or an IO error occurs during writing.
     */
    public void update(TaskList taskStorage) {
        try {
            FileWriter fwRedo = new FileWriter(this.filePath);
            fwRedo.close();
            FileWriter fw = new FileWriter(this.filePath, true);
            int counter = 0;
            while (taskStorage.hasTask(counter)) {
                Task task = taskStorage.getTask(counter);
                if (counter == 0) {
                    fw.write(task.parseDetailsForDB());
                } else {
                    fw.write("\n" + task.parseDetailsForDB());
                }
                counter++;
            }
            fw.close();
        } catch (IOException e) {
            throw new ServerError("File cannot be found. Check database is not removed");
        }
    }
}
