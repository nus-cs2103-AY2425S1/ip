package MichaelScott.Storage;

import MichaelScott.Exception.MichaelScottException;
import MichaelScott.Task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
        this.filePath = "./";
    }

    // MichaelScott.task.Todo: Can add loadTask functionality later

    /**
     * Saves the list of tasks to a file.
     *
     * @param todo An ArrayList of tasks to be saved.
     * @throws MichaelScottException If an error occurs while saving data.
     */
    public void saveTasks(ArrayList<Task> todo) throws MichaelScottException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < todo.toArray().length; i++) {
                fw.write(todo.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new MichaelScottException("Error saving data: " + e.getMessage());
        }
    }
}
