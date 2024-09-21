package main;

import exception.DukeException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Storage class is used to load tasks from text file and write task from TaskList to
 * text file as storage
 */
public class Storage {
    private final String path;
    private FileWriter fw;
    private File f;

    /**
     * Constructor for Storage class
     * @param path The path of the text file to be read from and written to
     */
    public Storage(String path) {
        this.path = path;
        f = new File(this.path);
        File directory = f.getParentFile();
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads text file from path
     * @return ArrayList of type Task to be passed to class TaskList
     */
    public ArrayList<Task> readStorage() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String response = s.nextLine();
                String[] splitResponse = response.split(" ", 3);
                if (splitResponse[1].equals("todo") || splitResponse[1].equals("event")
                        || splitResponse[1].equals("deadline")) {
                    handleTaskLoad(splitResponse, taskList);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    private static void handleTaskLoad(String[] splitResponse, ArrayList<Task> taskList) {
        Task currentTask;
        try {
            switch (splitResponse[1]) {
            case "todo":
                currentTask = new Todo(splitResponse[2]);
                break;
            case "event":
                currentTask = getEventTask(splitResponse);
                break;
            case "deadline":
                currentTask = getDeadlineTask(splitResponse);
                break;
            default:
                throw new DukeException("Something went wrong with the file!");
            }
            if ("1".equals(splitResponse[0])) {
                currentTask.mark();
            }
            taskList.add(currentTask);
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "________________________________");
        }
    }

    private static Task getDeadlineTask(String[] splitResponse) {
        String[] taskDetails = splitResponse[2].split(" /by ");
        return new Deadline(taskDetails[0], taskDetails[1]);
    }

    private static Task getEventTask(String[] splitResponse) {
        String[] taskDetails = splitResponse[2].split(" /from ");
        String[] taskTimings = taskDetails[1].split(" /to ");
        return new Event(taskDetails[0], taskTimings[0], taskTimings[1]);
    }

    /**
     * Takes in an ArrayList of task and writes data into a text file for storage
     * @param taskList The ArrayList of task to be read from to store
     */
    public void writeStorage(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(this.path, false);
            for (Task curr : taskList) {
                fw.write(curr.getStorageFormat());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
