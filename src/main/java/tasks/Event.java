package tasks;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.*;


/**
 * Represents the Event task.
 * A <code>Event</code> task has a name, from date, to date and a flag to show if it is done
 */
public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructor for an Event task by taking in a string with the relevant information.
     * It includes the name, from date and to date of the Event task.
     *
     * @param inputString The string containing information of the task
     * @throws InvalidTaskNameException If no name is provided.
     * @throws InvalidDateException If invalid date is provided.
     * @throws EmptyTagException If tag provided is empty, usually because it's all whitespace
     * @throws SpaceInTagException If tag provided contains whitespace.
     */
    public Event(String inputString)
            throws InvalidTaskNameException, InvalidDateException, EmptyTagException, SpaceInTagException {

        if (!inputString.contains("/from ")) {
            throw new InvalidDateException("From date is not provided!");
        }
        if (!inputString.contains("/to ")) {
            throw new InvalidDateException("To date is not provided!");
        }
        int fromIndex = inputString.indexOf("/from ");
        int toIndex = inputString.indexOf("/to ");
        if (toIndex < fromIndex) {
            throw new InvalidDateException("Wrong date order provided!");
        }

        String[] nameAndTags = inputString.substring(0, fromIndex).split("#");
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

        String toDate = inputString.substring(toIndex + 4);
        if (toDate.isEmpty()) {
            throw new InvalidDateException("To date not provided");
        }
        try {
            this.toDate = LocalDate.parse(toDate.trim());
        } catch (DateTimeParseException ex) {
            throw new InvalidDateException("Invalid to date format given");
        }

        String fromDate = inputString.substring(fromIndex + 6, toIndex);
        if (fromDate.isEmpty()) {
            throw new InvalidDateException("From date not provided");
        }
        try {
            this.fromDate = LocalDate.parse(fromDate.trim());
        } catch (DateTimeParseException ex) {
            throw new InvalidDateException("Invalid from date format given");
        }
        if (this.toDate.isBefore(this.fromDate)) {
            throw new InvalidDateException("To date is before from date");
        }

        tags.addAll(newTags);
    }

    /**
     * Another Constructor for an Event task.
     * This constructor takes in an array of strings after they have been split.
     *
     * @param input The array of strings, each string contains a field of the Event task
     * @throws BadDataException When data loaded from file has incorrect format
     */
    public Event(String[] input) throws BadDataException {
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
            this.fromDate = LocalDate.parse(input[2].trim());
            this.toDate = LocalDate.parse(input[3].trim());

            if (input.length < 5) {
                return;
            }

            // add tags
            List<String> trimmedTags =
                    Arrays.asList(input).subList(4, input.length).stream().map(String::trim).toList();
            for (String s: trimmedTags) {
                if (s.isEmpty() || s.contains(" ")) {
                    throw new BadDataException();
                }
            }
            tags.addAll(trimmedTags);

        } catch (Exception e) {
            throw new BadDataException();
        }
    }

    /**
     * Returns the string representation an Event task.
     * Contains the type, name, from date and to date of the Event task, as well as whether it is done.
     *
     * @return string representation of the Event task.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[E]");
        res.append(super.toString());
        res.append(" (from: ");
        res.append(this.fromDate.toString());
        res.append(" to: ");
        res.append(this.toDate.toString());
        res.append(")");

        if (!tags.isEmpty()) {
            res.append("\n  Tags: ");
            tags.forEach(tag -> res.append("#").append(tag).append(" "));
        }

        return res.toString();
    }

    /**
     * Returns the string representation of how an Event task should be saved.
     * Contains the type, name, from date and to date of the Event task, as well as whether it is done.
     *
     * @return save format of Event task.
     */
    @Override
    public String toSave() {
        StringBuilder res = new StringBuilder("E|");
        res.append(this.isDone ? "1|" : "0|");
        res.append(this.name);
        res.append("|");
        res.append(this.fromDate.toString());
        res.append("|");
        res.append(this.toDate.toString());


        if (!tags.isEmpty()) {
            res.append("|");
            tags.forEach(tag -> res.append(tag.trim()).append("|"));
        }

        return res.toString();
    }
}
