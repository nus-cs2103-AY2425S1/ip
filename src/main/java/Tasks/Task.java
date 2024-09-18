package tasks;

import exceptions.CommandNotRecognisedException;
import exceptions.DeadlineNoDateException;
import exceptions.EventNoTimeException;
import exceptions.TaskDescriptionEmptyException;
import exceptions.TestamentException;

/**
 * Represents a task stored in the testament chatbot.
 */
public abstract class Task {
    protected String taskname;

    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param s Description of task.
     */
    public Task(String s) {
        taskname = s;
        isDone = false;
    }

    /**
     * Factory method for task.
     * Depending on the user input, produces either a ToDo, Deadline, or Events
     *
     * @param userInput String that user inputs
     * @return Task created from user input
     * @throws TestamentException thrown when user input does not fit the format for creating tasks
     */
    public static Task of(String userInput) throws TestamentException {
        String[] splitUserInput = userInput.split(" ", 2);
        String identifier = splitUserInput[0];

        if (identifier.equals("todo")) {
            return createToDo(splitUserInput);
        } else if (identifier.equals("deadline")) {
            return createDeadline(splitUserInput);
        } else if (identifier.equals("event")) {
            return createEvent(splitUserInput);
        } else {
            //task not recognised
            throw new CommandNotRecognisedException();
        }
    }

    /**
     * Handles creation of to do in factory method
     *
     * @param splitUserInput
     * @return
     */
    private static Task createToDo(String[] splitUserInput) throws TestamentException {
        if (splitUserInput.length < 2) {
            throw new TaskDescriptionEmptyException("todo");
        }
        return new ToDo(splitUserInput[1]);
    }

    private static Task createDeadline(String[] splitUserInput) throws TestamentException {
        if (splitUserInput.length < 2) {
            throw new TaskDescriptionEmptyException("deadline");
        }

        String[] details = splitUserInput[1].split("/by ", 2);
        if (details.length < 2) {
            throw new DeadlineNoDateException();
        }
        return new Deadline(details[0], details[1]);
    }

    private static Task createEvent(String[] splitUserInput) throws TestamentException {
        if (splitUserInput.length < 2) {
            throw new TaskDescriptionEmptyException("event");
        }

        String[] details = splitUserInput[1].split("/from ", 2);
        if (details.length < 2) {
            throw new EventNoTimeException();
        }
        String info = details[0];
        String[] dates = details[1].split(" /to ", 2);
        if (dates.length < 2) {
            throw new EventNoTimeException();
        }
        return new Events(info, dates[0], dates[1]);
    }

    /**
     * Returns String that states whether a task is done, along with the task details.
     *
     * @return String containing information.
     */
    public String getDetails() {
        String str = "";
        if (isDone) {
            str = "[D] / ";
        } else {
            str = "[N] / ";
        }
        return str + taskname;
    }

    /**
     * Returns String to be stored in memory representing this Task.
     *
     * @return String to be stored in memory.
     */
    public abstract String infoForFile();

    /**
     * Sets done to true.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Sets done to false.
     */
    public void undone() {
        isDone = false;
    }

    /**
     * Returns toString representation of a task.
     *
     * @return toString representation of task.
     */
    @Override
    public String toString() {
        String str = "";
        if (isDone) {
            str = "[X] ";
        } else {
            str = "[ ] ";
        }
        str = str + taskname;
        return str;
    }
}
