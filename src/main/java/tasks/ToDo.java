package tasks;

import static java.lang.Integer.parseInt;

import exceptions.BadDataException;
import exceptions.EmptyTagException;
import exceptions.InvalidTaskNameException;
import exceptions.SpaceInTagException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Represents the ToDo task.
 * A <code>ToDo</code> task only has a name and a flag to show if it is done
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo task by taking in a string with the relevant information
     *
     * @param inputStr The string containing information of the task, which is the name and tags
     * @throws InvalidTaskNameException If no name is provided.
     * @throws EmptyTagException If the provided tag is empty, usually because it is just whitespace.
     * @throws SpaceInTagException If the provided tag contains whitespace.
     */
    public ToDo(String inputStr) throws InvalidTaskNameException , EmptyTagException, SpaceInTagException {
        String[] args = inputStr.split("#");
        ArrayList<String> newTags = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
            // name checking is done later, this is for checking tags
            if (i == 0) {
                continue;
            }

            if (args[i].isEmpty()) {
                throw new EmptyTagException();
            } else if (args[i].contains(" ")) {
                throw new SpaceInTagException();
            }

            //if tags are valid, add them to tags arraylist
            newTags.add(args[i]);
        }

        String name = args[0];
        if (name.isEmpty()) {
            throw new InvalidTaskNameException();
        }
        this.name = name;

        // adding tags
        tags.addAll(newTags);
    }

    /**
     * Another Constructor for a ToDo task.
     * This constructor takes in an array of strings after they have been split.
     *
     * @param input The array of strings, each string contains a field of the ToDo task
     * @throws BadDataException If the stored data is in the incorrect format.
     */
    public ToDo(String[] input) throws BadDataException {
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
            if (input.length < 3) {
                return;
            }

            // adding tags
            List<String> trimmedTags = Arrays.asList(input).subList(2, input.length)
                    .stream().map(String::trim).toList();

            //even in data, tags should have correct formatting without white space
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
     * Returns the string representation a ToDo task.
     * Contains the type and name of the ToDo task, as well as whether it is done.
     *
     * @return string representation of the ToDo task.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[T]");
        res.append(super.toString());

        if (!tags.isEmpty()) {
            res.append("\n   Tags: ");
            tags.forEach(tag -> res.append("#").append(tag).append(" "));
        }
        return res.toString();
    }

    /**
     * Returns the string representation of how a ToDo task should be saved.
     * Contains the type and name of the ToDo task, as well as whether it is done.
     *
     * @return save format of ToDo task.
     */
    @Override
    public String toSave() {
        StringBuilder res = new StringBuilder("T|");
        res.append(this.isDone ? "1|" : "0|");
        res.append(this.name);

        if (!tags.isEmpty()) {
            res.append("|");
            tags.forEach(tag -> res.append(tag.trim()).append("|"));
        }

        return res.toString();
    }
}
