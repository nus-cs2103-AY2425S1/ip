package darkpool.storage;

import java.io.IOException;

import darkpool.DarkpoolException;
import darkpool.tasklist.TaskList;

/**
 * SaveData class is responsible for saving the task list to the file.
 */
class SaveData {
    static void save(TaskList taskList, String filePath) throws DarkpoolException {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(filePath);
            fileWriter.write(taskList.toFileString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DarkpoolException(e.getMessage());
        }
    }
}
