package tasks;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.*;


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
     * @throws EmptyTagException If tag provided is empty, usually because it's all whitespace
     * @throws SpaceInTagException If tag provided contains whitespace.
     *
     */
    public DeadLine(String inputString)
            throws InvalidTaskNameException, InvalidDateException, EmptyTagException, SpaceInTagException {
        inputString = inputString.trim();

        if (!inputString.contains("/by ")) {
            throw new InvalidDateException();
        }

        int index = inputString.indexOf("/by ");

        String[] nameAndTags = inputString.substring(0, index).split("#");
        ArrayList<String> newTags = new ArrayList<>();
        for (int i = 0; i < nameAndTags.length; i++) {
            nameAndTags[i] = nameAndTags[i].trim();

            //name checking done later
            if (i == 0) {
                continue;
            }
            if (nameAndTags[i].isEmpty()) {
                throw new EmptyTagException();
            }
            if (nameAndTags[i].contains(" ")) {
                throw new SpaceInTagException();
            }
            newTags.add(nameAndTags[i]);
        }

        String taskName = nameAndTags[0];
        if (taskName.isEmpty()) {
            throw new InvalidTaskNameException();
        }
        this.name = taskName;

        String byDate = inputString.substring(index + 4);
        if (byDate.isEmpty()) {
            throw new InvalidDateException();
        }
        try {
            this.endDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException ex) {
            throw new InvalidDateException("Invalid date format given");
        }

        // add tags
        tags.addAll(newTags);
    }

    /**
     * Another Constructor for a DeadLine task.
     * This constructor takes in an array of strings after they have been split.
     *
     * @param input The array of strings, each string contains a field of the DeadLine task
     * @throws BadDataException When data loaded from file has incorrect format
     */
    public DeadLine(String[] input) throws BadDataException {
        try {
            int isDone = parseInt(input[0]);
            if (isDone == 0) {
                this.isDone = false;
            } else if (isDone == 1) {
                this.isDone = true;
            } else {
               throw new BadDataException();
            }
            this.name = input[1].trim();
            this.endDate = LocalDate.parse(input[2].trim());

            if (input.length < 4) {
                return;
            }

            // add tags and trim
            List<String> trimmedTags =
                    Arrays.asList(input).subList(3, input.length).stream().map(String::trim).toList();

            for (String s: trimmedTags) {
                if (s.isEmpty() || s.contains(" ")) {
                    throw new BadDataException();
                }
            }
            tags.addAll(trimmedTags);

        //to catch other errors like index out of bound errors
        } catch (Exception e) {
            throw new BadDataException();
        }
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
            tags.forEach(tag -> res.append(tag.trim()).append("|"));
        }

        return res.toString();
    }
}
