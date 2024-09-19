package darkpool.storage;

import java.util.ArrayList;

import darkpool.task.Task;
import darkpool.tasklist.TaskList;
import darkpool.DarkpoolException;

/**
 * Storage class is responsible for loading and saving data from and to the file.
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public ArrayList<Task> loadData() throws DarkpoolException {
        return LoadData.load(filePath);
    }

    public void saveData(TaskList taskList) throws DarkpoolException {
        SaveData.save(taskList, filePath);
    }

}
