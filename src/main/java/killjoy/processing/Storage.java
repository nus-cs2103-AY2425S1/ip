package killjoy.processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import killjoy.task.Task;

/**
 * Represents the Storage class of the KillJoy application.
 * Contains methods to load and save tasks.
 */
public class Storage {

    private ProcessTasks processTasks;

    public Storage(ProcessTasks processTasks) {
        this.processTasks = processTasks;
    }

    /**
     * Loads tasks from the file.
     */
    public void loadTasks() {
        File file = new File("KillJoy.txt");
        try {
            readTasksFromFile(file);
        } catch (FileNotFoundException e) {
            try {
                createNewFile();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    private static void createNewFile() throws IOException {
        FileWriter fw = new FileWriter("KillJoy.txt");
        fw.write("");
        fw.close();
    }

    private void readTasksFromFile(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String data = scan.nextLine();
            processTasks.createTasks(data);
        }
        scan.close();
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            writeTasksOnFile(tasks);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void writeTasksOnFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("KillJoy.txt");
        String currentTasks = "";
        for (Task task : tasks) {
            currentTasks += task.getTaskInfo();
        }
        fw.write(currentTasks);
        fw.close();
    }

}
