package yapbot.util;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Deadline;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> storedTasks;
    private enum Tasktype {TODO, DEADLINE, EVENT};

    public TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    /**
     * Loads tasks from file and creates the file if its does not exist.
     * If the file cannot be created, YapBot continues running but tasks will not be saved.
     * If load file is corrupted, previously loaded tasks are cleared from storedTasks and the file is overwritten.
     *
     * @return true if data is loaded successfully and false otherwise.
     */
    public TaskList(File file) throws FileNotFoundException {
        try {
            this.storedTasks = new ArrayList<>();
            Scanner s = new Scanner(file);

            while (s.hasNext()) {
                String[] taskData = s.nextLine().split("/");
                String taskType = taskData[0];

                switch (taskType) {
                case "T": {
                    int isDone = Integer.parseInt(taskData[1]);
                    String taskDetails = taskData[2];
                    Task task;

                    if (isDone == 1) {
                        task = new ToDo(taskDetails, true);
                    } else {
                        task = new ToDo(taskDetails);
                    }

                    storedTasks.add(task);
                    break;
                }

                case "D": {
                    int isDone = Integer.parseInt(taskData[1]);
                    String taskDetails = taskData[2];
                    LocalDateTime deadline = LocalDateTime.parse(taskData[3]);
                    Task task;

                    if (isDone == 1) {
                        task = new Deadline(taskDetails, deadline, true);
                    } else {
                        task = new Deadline(taskDetails, deadline, false);
                    }
                    storedTasks.add(task);
                    break;
                }

                case "E": {
                    int isDone = Integer.parseInt(taskData[1]);
                    String taskDetails = taskData[2];
                    LocalDateTime from = LocalDateTime.parse(taskData[3]);
                    LocalDateTime to = LocalDateTime.parse(taskData[4]);
                    Task task;

                    if (isDone == 1) {
                        task = new Event(taskDetails, from, to, true);
                    } else {
                        task = new Event(taskDetails, from, to, false);
                    }
                    storedTasks.add(task);
                    break;
                }

                default: {
                    file.delete();
                    storedTasks.clear();

                    // Abstract with storage
                    new File("./data").mkdir();
                    file.createNewFile();

                    throw new YapBotException("\nSave data detected...load failed.\nCorrupted data found."
                            + "\nYapBot will execute without prior data.");
                }
                }
            }

            s.close();
        } catch (YapBotException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a task instance and stores it in storedTasks field.
     *
     * @param tasktype Type of task to be created (eg. ToDo, Event, etc).
     * @param taskDetails Details of the task to be created.
     * @throws YapBotException If task details are missing for the specified task type.
     */
    private boolean addTask(Tasktype tasktype, String taskDetails) throws YapBotException {

        switch (tasktype) {
        case TODO: {
            Task task = new ToDo(taskDetails);

            storedTasks.add(task);
//            System.out.println(PREFIX_LINE + "\nAdding Task...\nSuccess\nTask added to database:\n" + "  "
//                    + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIX_LINE);
            return true;
        }

        case DEADLINE: {
            if (!taskDetails.contains("/by")) {
                throw new YapBotException("Error, Deadline Prediction module offline.\nSupply a deadline using "
                        + "\"/by\" (eg. /by Monday 1pm).");
            }

            String taskName = taskDetails.substring(0, taskDetails.indexOf("/by")).strip();
            String deadlineStr = taskDetails.substring(taskDetails.indexOf("/by") + 3).strip().toUpperCase();

            Task task = new Deadline(taskName, deadlineStr);
            storedTasks.add(task);

//            System.out.println(PREFIX_LINE + "\nAdding Task...\nSuccess"
//                    + "\nTask added to database:\n" + "  "
//                    + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIX_LINE);
            return true;
        }

        case EVENT: {
            if (!taskDetails.contains("/from") && !taskDetails.contains("/to")) {
                throw new YapBotException("Error, start and end times not detected.\nSupply start time using "
                        + "\"/from\" (eg. /from Monday 1pm).\nSupply end time using \"/to\" (eg. /to Tuesday 1pm).");
            }

            if (!taskDetails.contains("/from")) {
                throw new YapBotException("Error, start time not detected.\nSupply start time using \"/from\" (eg. "
                        + "/from Monday 1pm).");
            }

            if (!taskDetails.contains("/to")) {
                throw new YapBotException("Error, end time not detected.\nSupply end time using \"/to\" (eg. /to "
                        + "Tuesday 1pm).");
            }

            String taskName = taskDetails.substring(0, taskDetails.indexOf("/")).strip();
            String taskDeadlines = taskDetails.substring(taskDetails.indexOf("/"));
            int fromIndex = taskDeadlines.indexOf("/from");
            int toIndex = taskDeadlines.indexOf("/to");
            String fromStr;
            String toStr;

            // Checks order of /from and /to
            if (toIndex > fromIndex) {
                fromStr =
                        taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5, taskDeadlines.indexOf("/to")).strip()
                                .toUpperCase();
                toStr = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3).strip().toUpperCase();
            } else {
                toStr = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3, taskDeadlines.indexOf("/from")).strip()
                        .toUpperCase();
                fromStr = taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5).strip().toUpperCase();
            }

            Task task = new Event(taskName, fromStr, toStr);
            storedTasks.add(task);

//            System.out.println(PREFIX_LINE + "\nAdding Task...\nSuccess"
//                    + "\nTask added to database:\n" + "  "
//                    + task + "\n" + "Total tasks: " + storedTasks.size() + "\n" + POSTFIX_LINE);
            return true;
        }
        }

        return false;
    }

    /**
     * Prints the list of tasks currently in storedTasks.
     *
     * @throws YapBotException If storedTasks is empty.
     */
    public String listTasks() throws YapBotException {
        if (storedTasks.isEmpty()) {
            throw new YapBotException("Error, no Tasks found in database.");
        }
        StringBuilder result = new StringBuilder();
        Iterator<Task> iterateStored = storedTasks.iterator();
//        System.out.println(PREFIX_LINE + "\nRetrieving Tasks...Success\nCurrent Tasks:");

        int index = 1;
        while (iterateStored.hasNext()) {
            result.append("  ")
                    .append(index)
                    .append(".")
                    .append(iterateStored.next())
                    .append("\n");
            index++;
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
//        System.out.println(POSTFIX_LINE);
    }

    /**
     * Saves the tasks stored in storedTasks in a text file.
     *
     * @throws IOException If the file cannot be found.
     */
    public String saveTasks() throws IOException {

        if (storedTasks.isEmpty()) {
            return "";
        }

        Iterator<Task> iterateStored = storedTasks.iterator();
        StringBuilder result = new StringBuilder();

        while (iterateStored.hasNext()) {
            result.append(iterateStored.next().saveTask())
                    .append(System.lineSeparator());
        }

        return result.toString();
    }

    /**
     * Removes task in storedTasks.
     *
     * @param index Task number specifed by user. index = actual array index + 1.
     * @throws YapBotException If index > size of storedTasks array or index <= 0 (ie there is no task for the index).
     */
    public Task deleteTask(int index) throws YapBotException {

        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        return storedTasks.remove(index - 1);


//        System.out.println(PREFIX_LINE + "\nFinding Task...Success\nTask deleted from database:\n  "
//                + task + "\n" + POSTFIX_LINE);

    }

    /**
     * Sets a task to either completed or incomplete.
     *
     * @param index Index of task to be marked.
     * @throws YapBotException If index >= size of storedTasks array or index < 0 (ie there is no task for the index).
     */
    public Task markTask(int index) throws YapBotException {
        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        Task task = storedTasks.get(index);
        task.setDone(true);

//        System.out.println(PREFIX_LINE + "\nFinding Task...Success\nTask Completed:\n  "
//                + task
//                + "\n" + POSTFIX_LINE);

    }

    public Task unmarkTask(int index) throws YapBotException {

        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        Task task = storedTasks.get(index);
        task.setDone(false);

        System.out.println(PREFIX_LINE + "\nFinding Task...Success\nTask Incomplete:\n  "
                + task
                + "\n" + POSTFIX_LINE);
    }

}
