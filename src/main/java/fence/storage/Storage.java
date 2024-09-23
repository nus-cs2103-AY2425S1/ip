package fence.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fence.parser.Parser;
import fence.task.Task;
import fence.tasklist.TaskList;

/**
 * Represents the storage of the chatbot Fence.
 */
public class Storage {

    private File taskFile = new File("./data/fence.txt");
    private File dataDir = new File("./data");
    private FileWriter fw;

    /**
     * Saves the added task by appending it to the back of the data file.
     * @param task Task that was most recently added.
     * @throws IOException If the storage was unable to be successfully updated.
     */
    public void saveAppend(Task task) throws IOException {
        fw = new FileWriter(taskFile, true);
        fw.write(task.toTxt() + System.lineSeparator());
        fw.close();
    }

    /**
     * Rewrites and saves the current state of the tasklist after a task has been marked, unmarked or deleted.
     * @param tasks Current list of tasks.
     * @throws IOException If the storage was unable to be successfully updated.
     */
    public void saveRewrite(TaskList tasks) throws IOException {
        taskFile.delete();
        taskFile.createNewFile();
        fw = new FileWriter(taskFile, true);
        for (int i = 0; i < tasks.getSize(); i++) {
            fw.write(tasks.getTask(i).toTxt() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns an arraylist of all the tasks stored in the storage.
     * @param parser Parser of the chatbot.
     * @return Arraylist containing all the tasks previously saved.
     * @throws IOException If the data file is unable to be successfully read.
     */
    public ArrayList<Task> read(Parser parser) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        dataDir.mkdirs();
        taskFile.createNewFile();
        Scanner scanner = new Scanner(taskFile);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            tasks.add(parser.parseTxt(command));
        }
        scanner.close();
        return tasks;
    }
}
