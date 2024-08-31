package task;
import exception.InputFormatException;

/**
 * Represents a task with a description and a completion status.
 */
public abstract class Task {
    private final String description;
    private boolean done;

    /**
     * Create task with description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task (String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Checks if the task's description contains the specified target word.
     *
     * @param targetWord The word to search for within the task's description.
     * @return true if the description contains the target word, false otherwise.
     */
    public boolean find(String targetWord) {
        return description.contains(targetWord);
    }


    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * The format is: "doneStatus | description", where doneStatus is 1 if the task is done, 0 otherwise.
     *
     * @return A string representation of the task for file storage.
     */
    public String toFileFormatString() {
        return String.format("%d | %s", done? 1 : 0, description);
    }

    /**
     * Returns a string representation of the task, including its status and description.
     * The format is: "[statusIcon] description", where statusIcon is "X" if the task is done, " " otherwise.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(), description);
    }


    public void markAsDone() {
        done = true;
    }

    public void markAsUndone() {
        done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    /**
     * Checks if the input string matches the pattern for unmarking a task.
     * The expected input format is "unmark {number}".
     *
     * @param input The input string to be checked.
     * @return The number following the "unmark" keyword.
     * @throws InputFormatException If the input format is incorrect.
     */
    public static int matchesUnmark(String input) throws InputFormatException{
        String[] inputArr = input.split(" ");
        if (inputArr.length != 2) {
            throw new InputFormatException("Please specify a number after \"unmark\"");
        }
        try {
            return Integer.parseInt(inputArr[1]);
        } catch (NumberFormatException e){
            throw new InputFormatException("Please input a number after \"unmark\"");
        }
    }

    /**
     * Checks if the input string matches the pattern for marking a task as done.
     * The expected input format is "mark {number}".
     *
     * @param input The input string to be checked.
     * @return The number following the "mark" keyword.
     * @throws InputFormatException If the input format is incorrect.
     */
    public static int matchesMark(String input) throws InputFormatException{
        String[] inputArr = input.split(" ");
        if (inputArr.length != 2) {
            throw new InputFormatException("Please specify a number after \"mark\"");
        }
        try {
            return Integer.parseInt(inputArr[1]);
        } catch (NumberFormatException e){
            throw new InputFormatException("Please input a number after \"mark\"");
        }
    }
}
