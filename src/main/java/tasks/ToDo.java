package tasks;

import static java.lang.Integer.parseInt;

import exceptions.InvalidTaskNameException;

import java.util.Arrays;


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
     */
    public ToDo(String inputStr) throws InvalidTaskNameException {
        String[] args = inputStr.split("#");
        String name = args[0];
        if (name.length() == 0) {
            throw new InvalidTaskNameException();
        }
        this.name = name;

        if (args.length < 2) {
            return;
        }

        // adding tags
        tags.addAll(Arrays.asList(args).subList(1, args.length));
    }

    /**
     * Another Constructor for a ToDo task.
     * This constructor takes in an array of strings after they have been split.
     *
     * @param input The array of strings, each string contains a field of the ToDo task
     */
    public ToDo(String[] input) {
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
        this.name = input[1];
        if (input.length < 3) {
            return;
        }

        // adding tags
        tags.addAll(Arrays.asList(input).subList(2, input.length));
    }


    /**
     * Returns the string representation a ToDo task.
     * Contains the type and name of the ToDo task, as well as whether it is done.
     *
     * @return string representation of the ToDo task.
     */
    @Override
    public String toString() {
        String res = "[T]";
        res += super.toString();
        return res;
    }

    /**
     * Returns the string representation of how a ToDo task should be saved.
     * Contains the type and name of the ToDo task, as well as whether it is done.
     *
     * @return save format of ToDo task.
     */
    @Override
    public String toSave() {
        String res = "T|";
        res = res.concat(this.isDone ? "1|" : "0|");
        res = res.concat(this.name);
        return res;
    }
}
