package tasks;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;


/**
 * Represents the DeadLine task.
 * A <code>DeadLine</code> task has a name, end date and a flag to show if it is done
 */
public class DeadLine extends Task {

    protected LocalDate endDate;

    /**
     * Constructor for a DeadLine task by taking in a string with the relevant information.
     * It includes the name and end date of the DeadLine task.
     *
     * @param inputString The string containing information of the task
     * @throws InvalidTaskNameException If no name is provided.
     * @throws InvalidDateException If invalid date is provided.
     *
     */
    public DeadLine(String inputString) throws InvalidTaskNameException, InvalidDateException {
        inputString = inputString.trim();

        if (!inputString.contains("/by ")) {
            throw new InvalidDateException();
        }

        int index = inputString.indexOf("/by ");
        String[] nameAndTags = inputString.substring(0, index).trim().split("#");
        String taskName = nameAndTags[0];
        String byDate = inputString.substring(index + 4);
        if (taskName.isEmpty()) {
            throw new InvalidTaskNameException();
        }

        if (byDate.isEmpty()) {
            throw new InvalidDateException();
        }

        this.name = taskName;
        try {
            this.endDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException ex) {
            throw new InvalidDateException("Invalid date format given");
        }

        if (nameAndTags.length < 2) {
            return;
        }

        // add tags
        tags.addAll(Arrays.asList(nameAndTags).subList(1, nameAndTags.length));

    }

    /**
     * Another Constructor for a DeadLine task.
     * This constructor takes in an array of strings after they have been split.
     *
     * @param input The array of strings, each string contains a field of the DeadLine task
     */
    public DeadLine(String[] input) {
        int isDone = parseInt(input[0]);
        if (isDone == 0) {
            this.isDone = false;
        } else if (isDone == 1) {
            this.isDone = true;
        } else {
            System.out.println("Error: problem with storing data, cannot have isDone having a value that is " +
                    "not 1 or 0");
            System.exit(-1);
        }
        this.name = input[1].trim();
        this.endDate = LocalDate.parse(input[2].trim());

        if (input.length < 4) {
            return;
        }

        // add tags
        tags.addAll(Arrays.asList(input).subList(3, input.length));
    }

    /**
     * Returns the string representation a DeadLine task.
     * Contains the type, name and end date of the DeadLine task, as well as whether it is done.
     *
     * @return string representation of the DeadLine task.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[D]");
        res.append(super.toString());
        res.append(" (by: ").append(this.endDate.toString()).append(")");

        if (!tags.isEmpty()) {
            res.append("\n   Tags: ");
            tags.forEach(tag -> res.append("#").append(tag).append(" "));
        }

        return res.toString();
    }

    /**
     * Returns the string representation of how a DeadLine task should be saved.
     * Contains the type, name and end date of the DeadLine task, as well as whether it is done.
     *
     * @return save format of DeadLine task.
     */
    @Override
    public String toSave() {
        StringBuilder res = new StringBuilder("D|");
        res.append(this.isDone ? "1|" : "0|");
        res.append(this.name);
        res.append("|");
        res.append(this.endDate.toString());

        if (!tags.isEmpty()) {
            res.append("|");
            tags.forEach(tag -> res.append(tag).append("|"));
        }

        return res.toString();
    }
}
