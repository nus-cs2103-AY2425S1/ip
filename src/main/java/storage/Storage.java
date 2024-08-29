package storage;

import task.ToDo;
import task.Deadline;
import task.Event;
import task.TaskList;
import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Opens the file, storing of the task into the
 * file if added, and loading of the file when Tako just starts up.
 */
public class Storage {

    private File textFile;
    private File directoryFile;

    /**
     * Checks if a directory exists and create one if it does not and
     * checks if a text file exists and create one if it does not
     *
     * @param filePath destination of the file to load
     * @throws IOException if there is issue in opening/finding a file
     */
    public Storage(String filePath) {
        this.directoryFile = new File("data");
        this.textFile = new File(filePath);
        try {
            if (!directoryFile.exists()) {
                //created new data path
                directoryFile.mkdirs();
            }

            if (textFile.createNewFile()) {
                //created new Tako.txt file
            }
        } catch (IOException e) {
            System.out.println("Error in finding a file");
        }
    }

    /**
     * Helps to load the tasks into the chatbot when it starts.
     *
     * @throws FileNotFoundException if there is no file to load from.
     */
    public void load() {
        try {
            Scanner scanner = new Scanner(textFile);
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                char typeOfTask = task.charAt(1);
                char isTaskDone = task.charAt(4);
                if (typeOfTask == 'T') {
                    loadTodoTask(task, isTaskDone);
                }
                if (typeOfTask == 'D') {
                    loadDeadlineTask(task, isTaskDone);
                }
                if (typeOfTask == 'E') {
                    loadEventTask(task, isTaskDone);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * Writes the lists of tasks into the text file and store it in the hard drive.
     *
     * @param tasks tasks to be stored into the file.
     * @throws IOException if the file to be stored to does not exist.
     */
    public static void store(String tasks) {
        try {
            FileWriter fileWriter = new FileWriter("./data/Tako.txt");
            fileWriter.write(tasks);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File does not exists");
        }
    }

    /**
     * Helps to load ToDo Task into the list of tasks when the chatbot starts.
     *
     * @param task ToDo Task to be loaded.
     * @param isTaskDone Whether the ToDo task has already been completed.
     */
    public void loadTodoTask(String task, char isTaskDone) {
        String description = task.substring(7);
        TaskList.addTaskLoad(new ToDo(description));
        if (isTaskDone == 'X') {
            TaskList.markTaskLoad(TaskList.length() - 1);
        }
    }

    /**
     * Helps to load Deadline Task into the list of tasks when the chatbot starts.
     *
     * @param task Deadline Task to be loaded.
     * @param isTaskDone Whether the Deadline task has already been completed.
     */
    public void loadDeadlineTask(String task, char isTaskDone) {
        int byPosition = task.indexOf("(by:");
        String description = task.substring(7, byPosition);
        String by = task.substring(byPosition + 5, task.length() - 1);
        TaskList.addTaskLoad(new Deadline(description, Parser.changeDateToLocalDate(by)));
        if (isTaskDone == 'X') {
            TaskList.markTaskLoad(TaskList.length() - 1);
        }
    }

    /**
     * Helps to load Event Task into the list of tasks when the chatbot starts.
     *
     * @param task Event Task to be loaded
     * @param isTaskDone Whether the Event task has already been completed
     */
    public void loadEventTask(String task, char isTaskDone) {
        int fromPosition = task.indexOf("(from:");
        int toPosition = task.indexOf("to:");
        String description = task.substring(7, fromPosition);
        String from = task.substring(fromPosition + 7, toPosition - 1);
        String to = task.substring(toPosition + 4, task.length() - 1);
        TaskList.addTaskLoad(new Event(description, Parser.changeDateToLocalDate(from),
                                       Parser.changeDateToLocalDate(to)));
        if (isTaskDone == 'X') {
            TaskList.markTaskLoad(TaskList.length() - 1);
        }
    }
}
