package nen.tasks;

import java.time.format.DateTimeParseException;

import nen.exceptions.ArgumentMissingException;
import nen.exceptions.DateTimeFormatIncorrectException;
import nen.exceptions.EmptyDescriptionException;
import nen.exceptions.FailToParseDataException;
import nen.exceptions.InvalidInputException;


/**
 * This class represents a Task, super class of Todo, Event and Deadline
 * @author Gan Ren Yick (A0276246X)
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @param text input by user
     * @return an instant of task which can be either Deadline, Todo or Event type depend on text
     * @throws InvalidInputException is thrown when user input is not recognised
     * @throws ArgumentMissingException is thrown when argument(s) of task(Event and Deadline) is missing
     * @throws EmptyDescriptionException is thrown when description of tasks is missing
     */
    public static Task of(String text) throws InvalidInputException,
            ArgumentMissingException,
            EmptyDescriptionException,
            DateTimeFormatIncorrectException {
        String action = text.split(" ")[0];
        try {
            Task t = switch (action) {
            case "todo" -> new Todo(Task.getDescription(text));
            case "deadline" -> new Deadline(Task.getDescription(text), Task.findArgumentOf("by", text));
            case "event" -> new Event(Task.getDescription(text),
                    Task.findArgumentOf("from", text),
                    Task.findArgumentOf("to", text));
            default -> throw new InvalidInputException("Im not smart enough to understand that :(");
            };
            return t;
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatIncorrectException("I don't understand this format of date :(\n"
                    + "pls use yyyy-mm-dd :)");
        }
    }

    private static String findArgumentOf(String parameter, String text) throws InvalidInputException {
        String[] fragments = text.split("/");
        for (int i = 1; i < fragments.length; i++) {
            String[] args = fragments[i].split(" ");
            if (args[0].equals(parameter)) {
                args[0] = "";
                String out = String.join(" ", args);
                if (!out.isEmpty()) {
                    return out.substring(1);
                }
            }
        }
        throw new InvalidInputException("OOPSIEE where is your /" + parameter + " argument");
    }

    private static String getDescription(String text) throws EmptyDescriptionException {
        String[] arr = text.split("/")[0].split(" ");
        arr[0] = "";
        String out = String.join(" ", arr);
        if (out.isEmpty()) {
            throw new EmptyDescriptionException(text.split(" ")[0] + " what?? Wheres your description?");
        }
        return out.substring(1);
    }

    public String getDescription() {
        return description;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * mark the task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * @return a string which will be written into data file
     */
    public abstract String toData();

    /**
     * @param data read by scanner line by line from file
     * @return Task base on data
     * @throws FailToParseDataException when data read is in wrong format
     */
    public static Task parseData(String data) throws FailToParseDataException {
        String[] arr = data.split("/");
        Task t = null;
        switch (arr[0]) {
        case "T":
            t = new Todo(arr[2]);
            break;
        case "D":
            t = new Deadline(arr[2], arr[3]);
            break;
        case "E":
            t = new Event(arr[3], arr[4], arr[5]);
            break;
        default:
            throw new FailToParseDataException("Fail to parse data");
        };
        if (arr[1].equals("true")) {
            t.markAsDone();
        }
        return t;
    }
}
