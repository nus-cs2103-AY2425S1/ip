package tasks;

import java.io.Serializable;
import java.util.regex.Pattern;

import exceptions.DukeException;

/**
 * Abstract base class representing a task.
 * Each task has a description and a completion status.
 */
public abstract class Task implements Serializable {
    protected String task;
    protected boolean isDone = false;

    /**
     * Checks whether the user's input is a valid command.
     * Sequentially checks task description, start time(if needed)
     * and end time(if needed)
     *
     * @param taskInfoArray user's input split by " ".
     * @param type task type.
     * @throws DukeException if user input invalid command.
     */
    public static void checkValidCommand(String[] taskInfoArray, TaskType type) throws DukeException {
        // Return incorrect input message when the length of input less or equal to 1
        if (taskInfoArray.length <= 1 || taskInfoArray[1].isEmpty()) {
            String msg = "Oops! The description of a " + type + " cannot be empty.";
            throw new DukeException(msg);
        }

        // If input length is greater than 1, check each part
        String description = taskInfoArray[1];

        // When the task type is deadline, check description and end time
        if (type == TaskType.DEADLINE) {
            // Check whether user inputs the keyword '/by'
            if (!description.contains("/by")) {
                String msg = "Oops! The description of a deadline should contain a '/by' keywords";
                throw new DukeException(msg);
            }

            // Check whether user inputs a valid description of the task
            if (taskInfoArray[1].indexOf("/by") <= 1) {
                String msg = "Oops! Please give me more information about your task.";
                throw new DukeException(msg);
            }

            // Check whether user inputs a valid end time of a deadline task
            if (taskInfoArray[1].split("/by ").length <= 1) {
                String msg = "Oops! The end time of a deadline cannot be empty.";
                throw new DukeException(msg);
            }

            String deadline = taskInfoArray[1].split(" /by ")[1];
            String dateTimePattern = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$";
            // Check whether user input a valid end time format
            if (!Pattern.matches(dateTimePattern, deadline)) {
                String msg = "Please input a valid deadline format i.e. \"yyyy/MM/dd HH:mm\".";
                throw new DukeException(msg);
            }
        }

        // When the task type is event, check description, start time and end time
        if (type == TaskType.EVENT) {
            // Check whether user inputs keywords '/from' and '/to'
            if (!taskInfoArray[1].contains("/from") || !taskInfoArray[1].contains("/to")) {
                String msg = "Oops! The description of an event should"
                        + " contain a '/from' and '/to' keywords";
                throw new DukeException(msg);
            }

            // Check whether user inputs a valid description of the task
            if (taskInfoArray[1].indexOf("/from") <= 1) {
                String msg = "Please give me more information about your task.";
                throw new DukeException(msg);
            }

            // Get the time information by splitting the string
            String timeInfo = taskInfoArray[1].split("/from")[1];

            // Check whether user inputs a valid start time of a event task
            if (timeInfo.indexOf("/to") <= 1) {
                String msg = "Oops! The start time of an event cannot be empty.";
                throw new DukeException(msg);
            }

            // Check whether user inputs a valid end time of a event task
            if (timeInfo.split("/to ").length <= 1) {
                String msg = "Oops! The end time of an event cannot be empty.";
                throw new DukeException(msg);
            }
        }
    }

    /**
     * Creates a task according to the user's input.
     *
     * @param taskInfo task information, including task type, specific tasks
     *                 information and important time information of the task.
     * @return task created according to the task information user entered.
     */
    public static Task createTask(String taskInfo) throws DukeException {
        // Split the user input and get the task type
        String[] taskInfoArray = taskInfo.split(" ", 2);
        String type = taskInfoArray[0].toUpperCase();

        // Create a new task variable without initialize it
        Task newTask;

        // Create task object
        try {
            switch (TaskType.valueOf(type)) {
            case TODO:
                checkValidCommand(taskInfoArray, TaskType.TODO);
                String todoInfo = taskInfoArray[1];
                newTask = new ToDo(todoInfo);
                break;
            case DEADLINE:
                checkValidCommand(taskInfoArray, TaskType.DEADLINE);
                String ddlInfo = taskInfoArray[1].split(" /by ")[0];
                String deadline = taskInfoArray[1].split(" /by ")[1];
                newTask = new Deadline(ddlInfo, deadline);
                break;
            case EVENT:
                checkValidCommand(taskInfoArray, TaskType.EVENT);
                String eventInfo = taskInfoArray[1].split(" /from ")[0];
                String[] timeInfo = taskInfoArray[1].split(" /from ")[1].split(" /to ");
                String startTime = timeInfo[0];
                String endTime = timeInfo[1];
                newTask = new Event(eventInfo, startTime, endTime);
                break;
            default:
                throw new DukeException();
            }
        } catch (IllegalArgumentException e) {
            String msg = "Oops! It seems you enter a invalid type.";
            String guide = "Please enter a valid task type: todo/deadline/event";
            throw new DukeException(msg + "\n" + guide);
        }

        // Send message to user indicate the successful add and return the task created
        System.out.println("Got it. I've added this task:");
        return newTask;
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

    /**
     * Returns basic information of the task.
     *
     * @return task specific information and complete status.
     */
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return statusIcon + " " + this.task;
    }
}
