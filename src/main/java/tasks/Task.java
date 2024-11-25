package tasks;

import java.io.Serializable;
import java.util.regex.Pattern;

import exceptions.EchoException;

/**
 * Abstract base class representing a task.
 * Each task has a description and a completion status.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone = false;
    protected String tag = "";

    /**
     * Checks whether the user's input is a valid command.
     * Sequentially checks task description, start time(if needed)
     * and end time(if needed)
     *
     * @param taskInfoArray user's input split by " ".
     * @param type task type.
     * @throws EchoException if user input invalid command.
     */
    public static void checkValidCommand(String[] taskInfoArray, TaskType type) throws EchoException {
        // Return incorrect input message when the length of input less or equal to 1
        if (taskInfoArray.length <= 1 || taskInfoArray[1].isEmpty()) {
            String msg = "Oops! The description of a " + taskInfoArray[0] + " task cannot be empty.";
            throw new EchoException(msg);
        }

        // When the task type is deadline, check description and end time
        if (type == TaskType.DEADLINE) {
            validateDeadline(taskInfoArray);
        }

        // When the task type is event, check description, start time and end time
        if (type == TaskType.EVENT) {
            validateEvent(taskInfoArray);
        }
    }

    /**
     * Validates the user's input for a DEADLINE task.
     *
     * @param taskInfoArray the task description and time information provided by the user.
     * @throws EchoException if the deadline description or time format is invalid.
     */
    private static void validateDeadline(String[] taskInfoArray) throws EchoException {
        // Check whether user inputs the keyword '/by'
        if (!taskInfoArray[1].contains("/by")) {
            String msg = "Oops! The description of a deadline should contain a '/by' keywords";
            throw new EchoException(msg);
        }

        // Check whether user inputs a valid description of the task
        if (taskInfoArray[1].indexOf("/by") <= 1) {
            String msg = "Oops! Please give me more information about your task.";
            throw new EchoException(msg);
        }

        // Check whether user inputs a valid end time of a deadline task
        if (taskInfoArray[1].split("/by ").length <= 1) {
            String msg = "Oops! The end time of a deadline cannot be empty.";
            throw new EchoException(msg);
        }

        String deadline = taskInfoArray[1].split(" /by ")[1];
        String dateTimePattern = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$";

        // Check whether user input a valid end time format
        if (!Pattern.matches(dateTimePattern, deadline)) {
            String msg = "Please input a valid deadline format i.e. \"yyyy/MM/dd HH:mm\".";
            throw new EchoException(msg);
        }
    }

    /**
     * Validates the user's input for an EVENT task.
     *
     * @param taskInfoArray the task description and time information provided by the user.
     * @throws EchoException if the event description or time format is invalid.
     */
    private static void validateEvent(String[] taskInfoArray) throws EchoException {
        // Check whether user inputs keywords '/from' and '/to'
        if (!taskInfoArray[1].contains("/from") || !taskInfoArray[1].contains("/to")) {
            String msg = "Oops! The description of an event should"
                    + " contain a '/from' and '/to' keywords";
            throw new EchoException(msg);
        }

        // Check whether user inputs a valid description of the task
        if (taskInfoArray[1].indexOf("/from") <= 1) {
            String msg = "Please give me more information about your task.";
            throw new EchoException(msg);
        }

        // Get the time information by splitting the string
        String timeInfo = taskInfoArray[1].split("/from")[1];

        // Check whether user inputs a valid start time of an event task
        if (timeInfo.indexOf("/to") <= 1) {
            String msg = "Oops! The start time of an event cannot be empty.";
            throw new EchoException(msg);
        }

        // Check whether user inputs a valid end time of an event task
        if (timeInfo.split("/to ").length <= 1) {
            String msg = "Oops! The end time of an event cannot be empty.";
            throw new EchoException(msg);
        }
    }

    /**
     * Creates a task according to the user's input.
     *
     * @param taskInfo task information, including task type, specific tasks
     *                 information and important time information of the task.
     * @return task created according to the task information user entered.
     */
    public static Task createTask(String taskInfo) throws EchoException {
        // Split the user input and get the task type
        String[] taskInfoArray = taskInfo.split(" ", 2);
        String type = taskInfoArray[0].toUpperCase();

        // Create task object
        try {
            switch (TaskType.valueOf(type)) {
            case TODO:
                return createToDoTask(taskInfoArray);
            case DEADLINE:
                return createDeadlineTask(taskInfoArray);
            case EVENT:
                return createEventTask(taskInfoArray);
            default:
                throw new EchoException();
            }
        } catch (IllegalArgumentException e) {
            String msg = "Oops! It seems you enter a invalid type.";
            String guide = "Please enter a valid task type: todo/deadline/event";
            throw new EchoException(msg + "\n" + guide);
        }
    }

    /**
     * Creates a ToDo task from the task information array.
     *
     * @param taskInfoArray the array of task information.
     * @return a new ToDo task.
     * @throws EchoException if the task information is invalid.
     */
    private static Task createToDoTask(String[] taskInfoArray) throws EchoException {
        checkValidCommand(taskInfoArray, TaskType.TODO);
        String todoInfo = taskInfoArray[1];
        return new ToDo(todoInfo);
    }

    /**
     * Creates a Deadline task from the task information array.
     *
     * @param taskInfoArray the array of task information.
     * @return a new Deadline task.
     * @throws EchoException if the task information is invalid.
     */
    private static Task createDeadlineTask(String[] taskInfoArray) throws EchoException {
        checkValidCommand(taskInfoArray, TaskType.DEADLINE);
        String ddlInfo = taskInfoArray[1].split(" /by ")[0];
        String deadline = taskInfoArray[1].split(" /by ")[1];
        return new Deadline(ddlInfo, deadline);
    }

    /**
     * Creates an Event task from the task information array.
     *
     * @param taskInfoArray the array of task information.
     * @return a new Event task.
     * @throws EchoException if the task information is invalid.
     */
    private static Task createEventTask(String[] taskInfoArray) throws EchoException {
        checkValidCommand(taskInfoArray, TaskType.EVENT);
        String eventInfo = taskInfoArray[1].split(" /from ")[0];
        String[] timeInfo = taskInfoArray[1].split(" /from ")[1].split(" /to ");
        String startTime = timeInfo[0];
        String endTime = timeInfo[1];
        return new Event(eventInfo, startTime, endTime);
    }

    public void setMark() {
        this.isDone = true;
    }

    public void setUnmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns basic information of the task.
     *
     * @return task specific information and complete status.
     */
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String info = statusIcon + " " + this.description;
        if (this.tag != "") {
            info += " " + this.tag;
        }
        return info;
    }

}
