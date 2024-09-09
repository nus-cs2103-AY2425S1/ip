package janet;

import java.util.Arrays;

/**
 * Represents a ToDo, with a description and symbol.
 */
public class ToDo extends Task {

    ToDo(String inputLine) {
        // inside the program this will be called
        this(createToDo(inputLine).getDescription(),
                createToDo(inputLine).getSymbol());
    }

    ToDo(String description, String symbol) {
        // this is used inside the static method: createToDo
        super(description, symbol, null);
    }


    /**
     * Returns a ToDo object that was created from the user's command.
     * Based on the description.
     *
     * @param inputLine User's command that was typed into the command line.
     * @return A new ToDo object
     */
    public static ToDo createToDo(String inputLine) {
        // get the todo description and create a new Todo object
        String[] commandDetails = inputLine.split(" ");
        String[] todoItem = Arrays.copyOfRange(commandDetails, 1, commandDetails.length);
        String todoDescription = String.join(" ", todoItem);
        return new ToDo(todoDescription, "T");
    }
}
