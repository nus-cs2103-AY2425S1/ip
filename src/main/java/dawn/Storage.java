package dawn;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static dawn.TaskList.getTask;
import static dawn.TaskList.numOfTasks;

public class Storage {
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

        this.filePath = filePath;
    }

    /**
     * Loads the previously saved tasks into a new ArrayList
     *
     * @return a list of tasks
     * @throws DawnException
     */
    public TaskList load(TaskList taskList) throws DawnException {
        //todo change to use one addtask method
        File f;
        try {
            f = new File(this.filePath);
            // handles case file-already-exists, and
            // case file-does-not-exist-yet by creating a new text file
            if (!f.createNewFile()) {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String[] taskContent = s.nextLine().split(" ");
                    assert taskContent.length >= 1;
                    String detail = String.join(" ",
                            Arrays.copyOfRange(taskContent, 2, taskContent.length));
                    TaskList.addTask(taskContent[0], detail, taskContent[1]);
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new DawnException("Unable to load previously saved tasks: " + e.getMessage());
        }
    }

    protected static void clearSavedTask(String filePath) throws IOException {
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
            clearSavedTask(filePath);
            for (int i = 0; i < numOfTasks(); i++) {
                writeToFile(filePath, getTask(i));
            }
        } catch (IOException e) {
            throw new DawnException("Something went wrong: " + e.getMessage());
        }
    }

    protected static void writeToFile(String filePath, Task task) throws IOException {
        //todo change format to follow the actual input format from user so can reuse the addtask method
        FileWriter fw = new FileWriter(filePath, true);
        String separator = "/";
        String isDone = task.isDone() ? " 1 " : " 0 ";
        String desc = task.getDesc();

        String textToAdd;

        if (task instanceof ToDo) {
            textToAdd = "TODO" + isDone + desc;
        } else if (task instanceof Deadline) {
            // We can safely typecast here since we already checked that task is an instance of Deadline
            Deadline t = (Deadline) task;
            String deadline = "by " + t.getDate() + " " + (t).getDeadline();
            textToAdd = "DEADLINE" + isDone + desc + separator + deadline;
        } else { // task instanceof Event
            Event t = (Event) task;
            String from = "from " + t.getDate() + " " + t.getFromTime();
            String to = "to " + t.getToTime();
            textToAdd = "EVENT" + isDone + desc + separator + from + separator + to;
        }
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }
}
