package bmo;
import bmo.task.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private FileWriter writer = null;

    public Storage(String filePath) throws IOException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new IOException("File not found");
        }
        this.writer = new FileWriter(filePath, true);
    }

    public void closeWriter() throws IOException {
        this.writer.close();
    }

    /**
     * Updates the storage file with the tasks in the task list
     * 
     * @param TaskList object containing all current tasks
     * @throws IOException if unable to write to file
     */
    public void updateStorageFile(TaskList tasks) throws IOException {
        this.writer.close();
        FileWriter writer = new FileWriter("ip/data/BMO.txt");
        this.writer = writer;
        for (Task task : tasks.getTasks()) {
            this.writer.write(task.getSavedFormat());
        }
    }

    /**
     * Reads the storage file and loads the saved tasks into the task list
     * 
     * @param TaskList object to store the tasks
     * @param filePath the path of the storage file
     * @throws IOException if unable to read the file
     */
    public void readStorageFile(TaskList tasks, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
             String line;
             while ((line = reader.readLine()) != null) {
                String[] taskDetails = line.split(" \\| ");
                switch (taskDetails[0]) {
                case "T":
                    tasks.addTodo(taskDetails[2]);
                    break;
                case "D":
                    tasks.addDeadline(taskDetails[2], taskDetails[3]);
                    break;
                case "E":
                    tasks.addEvent(taskDetails[2], taskDetails[3], taskDetails[4]);
                    break;
                default:
                    break;
                }
           
                if (taskDetails[1].equals("1")) {
                    tasks.getTask(tasks.getSize() - 1).mark();
                }
                this.updateStorageFile(tasks);
             }
        } catch (IOException e) {
            throw new IOException("File not found");
        }
    }

    /**
     * Saves a task to the storage file
     * 
     * @param Task object to be saved
     * @throws IOException if unable to write to file
     */
    public void saveTask(Task task) throws IOException {
        this.writer.write(task.getSavedFormat());
    }
}