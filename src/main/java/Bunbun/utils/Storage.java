package Bunbun.utils;

import Bunbun.tasks.Deadline;
import Bunbun.tasks.Event;
import Bunbun.tasks.Task;
import Bunbun.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class handles flat file Storage for data persistence of Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Storage {

    private String filePathParent;
    private String filePath;

    /**
     * Instantiates a Storage object, storing the desired file path
     * to create the flat file storage.
     *
     * @param filePathParent String representing file path.
     */
    public Storage(String filePathParent) {
        this.filePathParent = filePathParent;
        this.filePath = filePathParent + "/Bunbun.txt";
    }

    /**
     * Creates data directory at desired file path and
     * Bunbun.txt file if they don't exist.
     */
    public void initializeTaskFile() {
        File newDir = new File(this.filePathParent);
        newDir.mkdir();
        try {
            File taskFile = new File(this.filePath);
            taskFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a Task object based on provided task description string.
     *
     * @param taskDescription String describing the task in the correct format.
     * @return Task based on provided task description string.
     */
    public Task parseTask(String taskDescription) {
        ArrayList<String> info = new ArrayList<>(Arrays.asList(taskDescription.split(";")));
        String type = info.get(0);
        Boolean isComplete = (info.get(1).equals("true")) ? true : false;
        Task task = null;
        if (type.equals("todo")) {
            task = new ToDo(info.get(2));
        } else if (type.equals("deadline")) {
            LocalDate date = DateTimeHandler.isValidLocalDate(info.get(3));
            task = new Deadline(info.get(2), date);
        } else {
            LocalDate startDate = DateTimeHandler.isValidLocalDate(info.get(3));
            LocalDate endDate = DateTimeHandler.isValidLocalDate(info.get(4));
            task = new Event(info.get(2), startDate, endDate);
        }
        if (isComplete) {
            task.complete();
        }
        return task;
    }

    /**
     * Returns an ArrayList of Tasks based on the information
     * within the storage file.
     *
     * @return ArrayList<Task> of tasks stored in the storage file.
     */
    public ArrayList<Task> toArrayList() {
        File taskFile = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNext()) {
                String taskDescription = s.nextLine();
                taskList.add(parseTask(taskDescription));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Writes the task description of a Task in the desired format
     * to the storage file.
     *
     * @param task Task to be written in.
     */
    public void writeTask(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            String taskDescription = task.genFileString();
            fw.write(taskDescription);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the task description of all the Tasks in the provided
     * TaskList to the storage file.
     *
     * @param taskList TaskList containing Tasks to be written in.
     */
    public void writeAllFromList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write("");
            fw.close();
            fw = new FileWriter(this.filePath, true);
            for (int i = 0; i < taskList.getNumOfTasks(); i++) {
                writeTask(taskList.getTaskByIndex(i));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the storage file, deleting old data.
     *
     */
    public void clearAll() {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
