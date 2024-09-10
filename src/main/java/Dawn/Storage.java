package Dawn;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Dawn.TaskList.getTask;
import static Dawn.TaskList.numOfTasks;

public class Storage {
    private ArrayList<Task> savedTasks;
    private String filePath;

    /**
     * Creates a new instance of a storage which will load previously saved tasks and store current tasks
     *
     * @param filePath File in which the current tasks will be saved and previously saved tasks loaded from
     */
    public Storage(String filePath) {
        // Handles folder-does-not-exist-yet case to save tasks
        String currentDir = System.getProperty("user.dir");
        String dirPath = currentDir + File.separator + "data";
        File file = new File(dirPath);
        file.mkdir();

        this.savedTasks = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * Loads the previously saved tasks into a new ArrayList
     *
     * @return a list of tasks
     * @throws DawnException
     */
    public ArrayList<Task> load() throws DawnException {
        File f;
        try {
            f = new File(this.filePath);
            // handles case file-already-exists, and
            // case file-does-not-exist-yet by creating a new text file
            // returns true if the file is created and false if the file already exists
            if (!f.createNewFile()) {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String[] taskContent = s.nextLine().split(" \\| ");
                    TaskType type = TaskType.valueOf(taskContent[0]);
                    Task task = generateSavedTask(taskContent, type);
                    savedTasks.add(task);
                }
                clearSavedTask(this.filePath); // clear the previously saved tasks
            }
            return this.savedTasks;
        } catch (IOException e) {
            throw new DawnException("Unable to load previously saved tasks: " + e.getMessage());
        }
    }

    enum TaskType {
        T,
        D,
        E
    }

    protected Task generateSavedTask(String[] taskContent, TaskType type) throws DawnException {
        boolean isDone = taskContent[1].equals("1"); // checks if the status of the task is 0 or 1
        Task task;
        switch (type) {
            case T:
                task = new ToDo(taskContent[2]);
                break;
            case D:
                task = new Deadline(taskContent[2], taskContent[3]);
                break;
            case E:
                task = new Event(taskContent[2], taskContent[3], taskContent[4]);
                break;
            default:
                // corrupted data file case, i.e. content not in the expected format
                // todo handle case where the content is not in the expected format
                throw new DawnException("Invalid task type!");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    protected void clearSavedTask(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        writer.write(""); // to clear the content of the files
        writer.close();
    }

    /**
     * Saves the current task list
     *
     * @param filePath File in which the current tasks will be saved to
     * @throws DawnException
     */
    public static void saveTasks(String filePath) throws DawnException {
        try {
            for (int i = 0; i < numOfTasks(); i++) {
                writeToFile(filePath, getTask(i));
            }
        } catch (IOException e) {
            throw new DawnException("Something went wrong: " + e.getMessage());
        }
    }

    protected static void writeToFile(String filePath, Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String separator = " | ";
        String isDone = task.isDone() ? "1" : "0";
        String desc = task.getDesc();
        String deadline;
        String from;
        String to;

        String textToAdd;

        if (task instanceof ToDo) {
            textToAdd = "T" + separator + isDone + separator + desc;
        } else if (task instanceof Deadline) {
            // We can safely typecast here since we already checked that task is an instance of Deadline
            Deadline t = (Deadline) task;
            deadline = "by " + t.getDate() + " " + (t).getDeadline();
            textToAdd = "D" + separator + isDone + separator + desc + separator + deadline;
        } else { // task instanceof Event
            Event t = (Event) task;
            from = "from " + t.getDate() + " " + t.getFromTime();
            to = "to " + t.getToTime();
            textToAdd = "E" + separator + isDone + separator + desc + separator + from + separator + to;
        }
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }
}
