package henry.command;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import henry.Ui;
import henry.TaskList;
import henry.Storage;
import henry.HenryException;
import henry.task.Task;

/**
 * Deals with executing the task given by user input
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException;

    public boolean isExit(){
        return false;
    }

    /**
     * Writes text into file
     *
     * @param filePath path of the file where it is saved
     * @param textToAdd text to be added in the file
     * @param index number of tasks recorded
     */
    private static void writeToFile(String filePath, String textToAdd, int index)
            throws IOException {
        FileWriter fw = new FileWriter(filePath, index != 0);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Updates the file after every input
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param storage instance of a storage that contains tasks
     *                recorded previously
     */
    public void save(TaskList taskList, Storage storage) throws HenryException {
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            for (int i = 0; i < tasks.size(); i++){
                writeToFile(storage.getFilePath(), tasks.get(i).summary()
                        + System.lineSeparator(), i);
            }
        } catch (IOException e) {
            throw new HenryException("Failed to save tasks to file");
        }
    }

}
