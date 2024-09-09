package friendlybot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import friendlybot.task.Deadline;
import friendlybot.task.Event;
import friendlybot.task.Task;
import friendlybot.task.ToDo;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    private String filePathWithoutFile;

    /**
     * A constructor for Storage.
     *
     * @param filePath Path of the TaskList file stored locally.
     * @param filePathWithoutFile Path of the TaskList file stored locally, excluding the file.
     *                            (Used to create relevant directories if they do not exist.)
     */
    public Storage(String filePath, String filePathWithoutFile) {
        this.filePath = filePath;
        this.filePathWithoutFile = filePathWithoutFile;
    }

    /**
     * Loads the text file from the file path, and returns an ArrayList of Task corresponding to the text file.
     * If the path is not recognised, create the relevant directories along the path.
     * If the file does not exist, create the file and return an empty ArrayList.
     *
     * @return An ArrayList of Tasks in the specified file path.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            boolean directoriesCreated = new File(this.filePathWithoutFile).mkdirs();
            boolean newFileIsCreated = f.createNewFile();
            if (newFileIsCreated) {
                return tasks;
            }
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNext()) {
                // task is in format {type of task} | {0 if not completed, else 1} | {name of task}
                // | {other task attributes}
                String task = fileReader.nextLine();
                try {
                    Task newTask = Storage.getTask(task);
                    tasks.add(newTask);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Ui.print("friendlybot.task.Task is not in the expected format: " + task);
                    Ui.print("Removed from task list!");
                }
            }
            fileReader.close();
            return tasks;
        } catch (IOException e) {
            Ui.print("An error occurred: " + e.getMessage());
            Ui.print("Resetting to an empty task list for you!");
            return new ArrayList<>();
        }
    }

    /**
     * Creates and returns a Task that corresponds from the String input in the text file.
     *
     * @param task String representation of the Task saved in the text file.
     * @return Task object that corresponds to the given String representation.
     */
    private static Task getTask(String task) {
        String[] taskItems = task.split(" \\| ");
        String taskType = taskItems[0];
        String completed = taskItems[1];
        String taskDescription = taskItems[2];
        Task newTask;
        if (taskType.equals("T")) {
            newTask = new ToDo(taskDescription);
        } else if (taskType.equals("D")) {
            newTask = new Deadline(taskDescription, LocalDate.parse(taskItems[3]));
        } else {
            newTask = new Event(taskDescription, LocalDate.parse(taskItems[3]), LocalDate.parse(taskItems[4]));
        }
        if (completed.equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * Writes to the text file on the given file path.
     * Updates the text file with the new task list upon exit.
     *
     * @param formattedTasks A formatted String representation of the Tasks to be saved locally.
     */
    public void writeToFile(String formattedTasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(formattedTasks);
            fw.close();
        } catch (IOException e) {
            Ui.print("Something went wrong: " + e.getMessage());
        }
    }
}
