package meep.task;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

import meep.ui.Ui;
import meep.MeepException;

public class Storage {
    private final String dataPath;

    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    private static void processTaskString(String taskString, TaskList taskList) {
        try {
            String[] taskParts = taskString.split("\\|");
            String taskType = taskParts[0];
            boolean isDone = taskParts[1].equals("1");
            String taskDescription = taskParts[2];
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(taskDescription);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            case "D":
                task = new Deadline(taskDescription, taskParts[3]);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            case "E":
                task = new Event(taskDescription, taskParts[3], taskParts[4]);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            Ui ui = new Ui();
            ui.errorLoadingTask();
        }
    }

    private void createFileIfNotExists() throws MeepException {
        // This method is created with use of ChatGPT

        // Create a File object for the file
        File file = new File(this.dataPath);
        try {
            // Check if the file exists, if not, create it
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create the file");
                }
            }
        } catch (IOException e) {
            throw new MeepException("An error occurred." + e.getMessage());
        }
    }

    public TaskList loadTasks() throws MeepException {
        // Load tasks from file
        createFileIfNotExists();
        TaskList taskList = new TaskList();
        try {
            File f = new File(this.dataPath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                processTaskString(s.nextLine(), taskList);
            }
        } catch (FileNotFoundException e) {
            throw new MeepException("File not found: " + e.getMessage());
        }
        return taskList;
    }

    public void saveTasks(TaskList taskList) throws MeepException {
        // Save tasks to file
        try {
            FileWriter fw = new FileWriter(this.dataPath);
            fw.write(taskList.getSaveFormatList());
            fw.close();
        } catch (IOException e) {
            throw new MeepException("An error occurred: " + e.getMessage());
        }
    }
}
