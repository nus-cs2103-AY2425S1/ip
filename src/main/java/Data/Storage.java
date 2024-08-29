package Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Tasks.*;
import Main.FileUI;

public class Storage {
    protected static ArrayList<Task> items = new ArrayList<>();

    /**
     * loads tasks from file and adds to list
     * Catch all the relevant exceptions when trying to add invalid tasks
     *
     */
    public static ArrayList<Task> loadTasks() {
        FileUI.createFileIfNotPresent();

        try {
            FileReader fr = new FileReader(FileUI.FILE_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            // loop runs till file is empty
            while (line != null) {
                String[] parts = line.split("\\|");
                //extract the diff parts of a task
                String taskType = parts[0];
                String taskStatus = parts[1];
                String taskDesc = parts[2];

                Task task = null;
                switch (taskType) {
                    case "T":
                        task = new ToDos(taskDesc);
                        break;

                    case "D":
                        task = new Deadlines(taskDesc);
                        break;

                    case "E":
                        task = new Events(taskDesc);
                        break;

                    default:
                        System.out.println("Unknown taskType");
                        continue;
                }
                if (task != null) {
                    if (taskStatus.equals("X")) {
                        task.mark();
                    }
                    items.add(task);
                }
                line = br.readLine();
            }
            br.close();
            return items;
        } catch (IOException e) {
            System.out.println("Error saving tasks" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown error occurred" + e.getMessage());
        }
        // return empty list in case of failure
        return new ArrayList<>();
    }


    /**
     * Saves appropriate task to file
     * Catch all the relevant exceptions when trying to save invalid tasks
     *
     * @param updatedTasks changed list which needs to be overwritten into file
     *
     */
    public static void saveTasksToFile(ArrayList<Task> updatedTasks) {
        FileUI.createFileIfNotPresent();

        try {
            FileWriter fw = new FileWriter(FileUI.FILE_PATH);

            for (Task task : updatedTasks) {
                String taskType = task instanceof ToDos ? "T"
                        : task instanceof Deadlines ? "D" : "E";
                String taskStatus = task.getStatusIcon();
                String taskDesc = task.getTaskDesc();
                String taskInfo = taskType + "|" + taskStatus + "|" + taskDesc + "\n";

                fw.write(taskInfo);
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks" + e.getMessage());
        }
    }

}
