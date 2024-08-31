package storage;
import prince.Prince;


import exception.InvalidDeadlineException;
import task.DeadlinesTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;
import task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles storage of user data and user tasks in the file
 * Creates the necessary files and directories
 */

public class Storage {

    private static String dirPath;
    private static String filePath;

    /**
     * Constructs a storage object with a directory and file path
     * @param dirPath
     * @param filePath
     */

    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    /**
     * creates file and directory if it doesn't exist
     */

    public static void createListFile() {
        try {
            //create a directory called data and if it doesnt exist then create it using the function .mkdir()
            File dir = new File(dirPath);
            if(!dir.exists()) {
                dir.mkdir();
            }

            //create the file and if the file doesnt exist then create using .createNewFile()
            File file = new File(filePath);
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) { //unchecked exception
            System.out.println("Error creating file. Please try again later.");
            // to have a clearer idea of what the exception is and where the error is, use the printStackTrace()
            e.printStackTrace();
        }
    }

    /**
     * saves the tasks using fileWriter to the file
     * tasks saved which can be unloaded later
     *
     * @param list
     */

    public static void pushTasksToFile(ArrayList<Task> list) {
        //use inbuilt functions like FileWriter - that opens file at filepath, if doesnt exist, creates one
        //PrintWriter - wraps filewriter with printwriter so that can use inbuilt functions for formatting

        try(PrintWriter printwriter = new PrintWriter(new FileWriter(filePath))) {
            int length = list.size();
            for(int i = 0; i < length; i++) {
                printwriter.println(list.get(i).printFileFormat());
            }

        } catch (IOException e) {
            System.out.println("There was an error saving tasks, please try again later.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks when the application is started
     *
     * @return ArrayList<Task> returns a list of tasks
     */
    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("No file found, list is empty so far.");
                return tasks;
            } else {
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()) {
                    String taskString = sc.nextLine();
                    Task task = getTaskFromFile(taskString);
                    if(task != null) {
                        tasks.add(task);
                    }
                }

                sc.close();
            }
        } catch (IOException e) {
            System.out.println("There was an error loading tasks, please try again later.");
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Creates the relevant tasks according to the types
     *
     * @param taskString
     * @return Task (created from its string description and details)
     */
    public static Task getTaskFromFile(String taskString) {
        // need to split and add accordingly
        // first is the tasktype, then the progress, then the description, then the deadlines
        // but splitting according to " | " does not work as it has a special meaning
        // to take it as literal character, u need to put \\ in front
        String[] splits = taskString.split(" \\| ");
        String taskType = splits[0];
        String progress = splits[1];
        String taskdes = splits[2];
        boolean isDone = splits[1].equals("1");

        if (taskType.equals("T")) {
            ToDoTask task = new ToDoTask(taskdes);
            if(isDone) {
                task.markDone();
            }
            return task;

        } else if (taskType.equals("D")) {
            try {
                String deadline = splits[3];
                DeadlinesTask task = new DeadlinesTask(taskdes, deadline);
                if (isDone) {
                    task.markDone();
                }
                return task;
            } catch (InvalidDeadlineException e) {
                System.out.println(e.getMessage());
                return null;
            }

        } else if (taskType.equals("E")){
            String from = splits[3];
            String to = splits[4];
            EventTask task = new EventTask(taskdes, from, to);
            if (isDone) {
                task.markDone();
            }
            return task;
        } else {
            System.out.println("Unknown task description included. Use only existing task types - T, D, E");
            return null;
        }
    }
}
