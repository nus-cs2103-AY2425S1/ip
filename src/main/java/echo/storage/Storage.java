package echo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import echo.parser.Parser;
import echo.task.Task;
import echo.tasklist.TaskList;

/**
 * Represents a storage space to store user's task
 *
 * @author Ernest Lim
 */
public class Storage {
    private String filePath;
    /**
     * Constructor to create a Storage object
     */
    public Storage() {
        this.filePath = "./data/echo.txt";
    }

    /**
     * Saves all the task in task list into the provided text file
     *
     * @param taskList TaskList containing all the tasks
     * @throws IOException if there is an invalid output or input
     */
    public void saveTaskList(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < taskList.sizeOfTaskList(); i++) {
            writer.write(taskList.getTask(i).toFancyString() + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Adds all the task in the saved file into the taskList
     *
     * @param parser Parser to interpret the text provided in the saved file
     * @param taskList taskList for the tasks to be added inside
     * @throws FileNotFoundException if there is no file present in the file path
     */
    public void loadStorage(Parser parser, TaskList taskList) throws FileNotFoundException {
        fetchFile();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String taskStored = scanner.nextLine();
            Task task = parser.parseInputFromTextFile(taskStored);
            taskList.addTask(task);
        }
    }

    /**
     * Creates file if file do not exist in filepath
     */
    public void fetchFile() {
        try {
            File file = new File(filePath);
            File directory = new File(file.getParent());

            //Check if directory exists
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //Check if file exists
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
