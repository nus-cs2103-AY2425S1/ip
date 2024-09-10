package quack.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import quack.exception.InvalidDateTimeException;
import quack.exception.InvalidTaskTypeException;
import quack.tasks.Task;

/**
 * This class is responsible for handling any
 * file related actvities such as loading
 * and saving into a file.
 */
public class Storage {

    /** Default file path for the save file */
    private static String DEFAULT_PATH = "./data";
    /** Default save file name */
    private static String DEFAULT_FILE_NAME = "./data/savedData.csv";

    /**
     * Creates a storage object.
     * <p>
     * Upon creating a storage object, it will create the directory and save file
     * if it does not exist.
     * <p>
     * Afterwards it will load the data from the save file.
     * @param taskList A list that stores all the tasks tracked by Quack.
     */
    public Storage(TaskList taskList) {

        try {
            Path filePath = Paths.get(DEFAULT_PATH);
            Files.createDirectories(filePath);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }

        this.loadData(taskList);
    }

    /**
     * Reads task data from the csv save file and add it into the task list.
     * <p>
     * All tasks that were saved will be readded into the task list upon running Quack.
     * @param taskList A list that stores all the tasks tracked by Quack.
     */
    private void loadData(TaskList taskList) {

        // Get the file path to the save file
        File dataFile = new File(DEFAULT_FILE_NAME);

        try {
            // Create the save file if it does not exist
            dataFile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(dataFile));

            String dataLine = br.readLine();

            while (dataLine != null) {
                Task task = this.parseCsvToTask(dataLine);
                if (task != null) {
                    taskList.addTask(task);
                }
                dataLine = br.readLine();
            }

            br.close();

        } catch (IOException IoError) {
            // There is no data file to read from, then continue as per normal.
            System.out.println(IoError.getMessage());
        }
    }

    /**
     * Saves the task list into a .csv folder.
     * <p>
     * All tasks inside the task list will be saved into a .csv folder once Quack stops running.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public void saveData(TaskList taskList) throws IOException {

        // Create a csv file to save the tasks
        File dataFile = new File("data/savedData.csv");
        FileWriter fw = new FileWriter(dataFile);

        // Convert each task into a csv string format and write into the file
        ArrayList<Task> list = taskList.getToDoList();
        for (Task task : list) {
            String taskData = this.parseTaskToCsv(task);
            fw.write(taskData + "\n");
        }

        // Close the file writter
        fw.close();
    }

    /**
     * Converts the task information from a csv format to an array of strings.
     * <p>
     * @param dataLine A string of data of a task in csv format.
     * @return A task object based on the data from the save file.
     */
    private Task parseCsvToTask(String dataLine) {

        String[] data = dataLine.split(",");

        Task task = null;

        try {
            task = Task.createTask(data);
            if (Boolean.parseBoolean(data[data.length - 1])) {
                task.mark();
            }
        } catch (InvalidDateTimeException dateTimeError) {
            System.out.println(dateTimeError.getMessage());
        } catch (InvalidTaskTypeException taskTypeError) {
            System.out.println(taskTypeError.getMessage());
        }

        return task;
    }

    /**
     * Converts a task into a csv format to be saved into the save file.
     * @param task The task object to be converted.
     * @return A string representation of the task in a csv format.
     */
    private String parseTaskToCsv(Task task) {

        String taskData = task.toCsvFormat();
        return taskData;
    }
}
