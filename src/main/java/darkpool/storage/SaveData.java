package darkpool.storage;

import darkpool.tasklist.TaskList;
import darkpool.DarkpoolException;

import java.io.IOException;

class SaveData {
    static void save(TaskList taskList, String filePath) throws DarkpoolException{
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(filePath);
            fileWriter.write(taskList.toFileString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DarkpoolException(e.getMessage());
        }
    }
}
