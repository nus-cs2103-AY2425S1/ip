package Tasks;

import Exceptions.*;

/**
 * Represents a task stored in the testament chatbot.
 */
public abstract class Task {
    protected String taskname;
    private boolean done;

    /**
     * Constructor for task.
     *
     * @param s Description of task.
     */
    public Task(String s) {
        taskname = s;
        done = false;
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
            if (splitUserInput.length < 2) {
                throw new TaskDescriptionEmptyException("todo");
            }
            return new ToDo(splitUserInput[1]);
        } else if (identifier.equals("deadline")) {
            if (splitUserInput.length < 2) {
                throw new TaskDescriptionEmptyException("deadline");
            }

            String[] details = splitUserInput[1].split("/by ", 2);
            if (details.length < 2) {
                throw new DeadlineNoDateException();
            }
            return new Deadline(details[0], details[1]);
        } else if (identifier.equals("event")) {
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
        } else {
            //task not recognised
            throw new CommandNotRecognisedException();
        }
    }

    /**
     * Returns String that states whether a task is done, along with the task details.
     *
     * @return String containing information.
     */
    public String getDetails() {
        String str = "";
        if (done) {
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
        done = true;
    }

    /**
     * Sets done to false.
     */
    public void undone() {
        done = false;
    }

    /**
     * Returns toString representation of a task.
     *
     * @return toString representation of task.
     */
    @Override
    public String toString() {
        String str = "";
        if (done) {
            str = "[X] ";
        } else {
            str = "[ ] ";
        }
        str = str + taskname;
        return str;
    }
}
