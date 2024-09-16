package milutrock.tasks;

import milutrock.exceptions.InvalidTaskFormatException;

/**
 * A task that needs to be done.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * Create a new ToDo object from user input.
     * 
     * @param input String input from the user.
     * @return A ToDo object created from the given input. 
     */
    public static ToDo getToDoFromInput(String input) throws InvalidTaskFormatException {
        if (input.length() < 5) {
            throw new InvalidTaskFormatException("ToDo");
        }

        return new ToDo(input.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
