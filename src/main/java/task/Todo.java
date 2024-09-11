package task;

import java.util.Arrays;

/**
 * The Todo class represents a task that needs to be done without any due date
 * It extends the Task class
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with an empty description and a type of "T".
     */
    public Todo() {
        super("", "T");
    }

    /**
     * Converts user input into a Todo task.
     *
     * @param slicedStrings The array of strings representing the user input.
     */
    public void convertStringToTask(String[] slicedStrings) {
        String[] task = Arrays.copyOfRange(slicedStrings, 1, slicedStrings.length);
        this.description = String.join(" ", task);
    }

    /**
     * Converts saved data into a Todo task.
     *
     * @param dataArr The array of strings representing the saved data.
     */
    public void convertSavedDataToTask(String[] dataArr) {
        this.setMarkStatus(dataArr[1].equals("1"));
        this.description = dataArr[2];
    }
}
