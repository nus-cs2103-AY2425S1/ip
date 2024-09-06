package skibidi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import skibidi.exceptions.SkibidiException;
import skibidi.task.Task;


/**
 * Represents a storage.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list to the file.
     *
     * @param taskList The task list to be saved.
     * @throws IOException If an error occurs while saving the task list.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.getTask(i).writeToFile() + "\n");
        }
        fw.close();
    }

    /**
     * Loads the task list from the file.
     *
     * @return The task list loaded from the file.
     * @throws SkibidiException If an error occurs while loading the task list.
     * @throws FileNotFoundException If the file is not found.
     */
    public TaskList load() throws SkibidiException, FileNotFoundException {

        TaskList taskList = new TaskList();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            Task task = Parser.parseTask(line);
            taskList.addTask(task);
        }
        Ui.printList(taskList);
        return taskList;


    }
}
