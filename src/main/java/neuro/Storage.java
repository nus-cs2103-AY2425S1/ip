package neuro;

import neuro.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws FileNotFoundException {
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                // Make folder if necessary
                f.getParentFile().mkdirs();

                // Create file if it doesn't exist
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e);
        }

        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] taskComponents = nextLine.split(" \\| ");
            String taskType = taskComponents[0];
            String taskIsDone = taskComponents[1];
            Task taskToAdd = getTaskToAdd(taskType, taskComponents, taskIsDone);

            if (taskToAdd != null) {
                taskList.add(taskToAdd);
            }
        }

        return new TaskList(taskList);
    }

    private Task getTaskToAdd(String taskType, String[] taskComponents, String taskIsDone) {
        Task taskToAdd = null;

        try {
            switch (taskType) {
                case ("T"):
                    taskToAdd = new Todo(taskComponents[2]);
                    break;
                case ("D"):
                    taskToAdd = new Deadline(taskComponents[2], LocalDateTime.parse(taskComponents[3]));
                    break;
                case ("E"):
                    taskToAdd = new Event(taskComponents[2], taskComponents[3], taskComponents[4]);
                    break;
                default:
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
            // Corrupted line in save file
        }


        if (taskIsDone.equals("1") && taskToAdd != null) {
            taskToAdd.markDone();
        }
        return taskToAdd;
    }

    public void updateTaskFile(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (Task task : tasks) {
                fileWriter.write(task.toSaveData() + System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error encountered: " + e);
        }

    }
}
