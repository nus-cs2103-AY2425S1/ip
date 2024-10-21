package timo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage that loads data from list text file, and stores data into the file
 */
public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from a file specified by the `filepath` and returns them as a list of `Task` objects.
     * <p>
     * The tasks in the file are expected to be in a specific format, indicating the type of task
     * (Todo, Deadline, or Event) and its completion status.
     * </p>
     *
     * @return A `List` of `Task` objects representing the tasks stored in the file.
     *
     * @throws FileNotFoundException if the file at the specified `filepath` does not exist.
     *
     * @see Task
     * @see Todo
     * @see Deadline
     * @see Event
     */
    public List<Task> load() throws FileNotFoundException {
        File f = new File(this.filepath);

        //initialise taskList to store the values
        List<Task> taskList = new ArrayList<Task>();

        //check if the file exists
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String storedLine = s.nextLine();
                addTaskToArray(storedLine, taskList);
            }
            return taskList;
        } else {
            throw new FileNotFoundException("file not found!");
        }

    }

    /**
     * adds a todo to taskList
     * @param storedLine
     * @param taskList
     */
    public void addTodoToArray(String storedLine, List<Task> taskList) {
        String[] splitStoredLine = storedLine.split("] ", 2);
        if (Character.compare(storedLine.charAt(4), 'X') == 0) {
            taskList.add(new Todo(true, splitStoredLine[1]));
        } else {
            taskList.add(new Todo(false, splitStoredLine[1]));
        }
    }

    /**
     * adds a deadline to taskList
     * @param storedLine
     * @param taskList
     */
    public void addDeadlineToArray(String storedLine, List<Task> taskList) {
        //remove the [D][?] from the line
        String splitStoredLine = storedLine.split("] ")[1];

        //get the important values to create the Deadline
        String[] deadlineDetails = splitStoredLine.split(" \\(by: |\\)");

        LocalDateTime datetime = LocalDateTime.parse(deadlineDetails[1],
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

        //see if the task has been done or not
        if (Character.compare(storedLine.charAt(4), 'X') == 0) {
            taskList.add(new Deadline(true, deadlineDetails[0], datetime));
        } else {
            taskList.add(new Deadline(false, deadlineDetails[0], datetime));
        }
    }

    /**
     * adds an Event to taskList
     * @param storedLine
     * @param taskList
     */
    public void addEventToArray(String storedLine, List<Task> taskList) {
        //removing the [E][?] from the line
        String splitStoredLine = storedLine.split("] ", 2)[1];
        //getting important values to create the Event
        String[] eventDetails = splitStoredLine.split(" \\(from: | to: |\\)");

        LocalDateTime fromDatetime = LocalDateTime.parse(eventDetails[1],
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        LocalDateTime toDatetime = LocalDateTime.parse(eventDetails[1],
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        //see if the task has been done or not
        if (Character.compare(storedLine.charAt(4), 'X') == 0) {
            taskList.add(new Event(true, eventDetails[0], fromDatetime, toDatetime));
        } else {
            taskList.add(new Event(false, eventDetails[0], fromDatetime, toDatetime));
        }
    }

    /**
     * create the relevant Task and add it to the taskList
     * @param storedLine the Task read from one line in text file
     * @param taskList the taskList that will store all tasks
     */
    public void addTaskToArray(String storedLine, List<Task> taskList) {
        if (storedLine.startsWith("[T]")) {
            addTodoToArray(storedLine, taskList);
        } else if (storedLine.startsWith("[D]")) {
            addDeadlineToArray(storedLine, taskList);
        } else {
            addEventToArray(storedLine, taskList);
        }
    }

    /**
     * Stores a list of tasks to a file specified by the `filepath`.
     * <p>
     * If the file does not exist, it will be created. The method overwrites any existing content
     * in the file and writes each task in the provided list to the file, with each task on a new line.
     * </p>
     *
     * @param taskList A `List` of `Task` objects to be stored in the file.
     *
     * @see Task
     */
    public void store(List<Task> taskList) {
        //create new file if file does not exist
        File file = new File(this.filepath);

        try {
            boolean isParentDirectoryCreated = file.getParentFile().mkdirs();
            boolean isFilecreated = file.createNewFile();

            //delete all contents in the file
            FileWriter fil = new FileWriter(this.filepath);
            fil.write("");
            fil.close();


            //create FileWriter to append to file
            FileWriter fw = new FileWriter(this.filepath, true);
            for (Task i: taskList) {
                fw.write(i + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
