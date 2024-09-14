package janet;

import java.util.Arrays;

/**
 * Represents a ToDo, with a description and symbol.
 */
public class ToDo extends Task {

    ToDo(String inputLine) {
        // inside the program this will be called
        this(createToDoFromUser(inputLine).getDescription(),
                createToDoFromUser(inputLine).getSymbol());
    }

    ToDo(String description, String symbol) {
        // this is used inside the static method: createToDo
        super(description, symbol);
    }

    ToDo(String[] textLine) {
        // this is used inside Storage to create ToDo object from .txt file
        this(createToDoFromTxt(textLine).getDescription(),
                createToDoFromTxt(textLine).getSymbol());
    }


    /**
     * Returns a ToDo object that was created from the user's command.
     * Based on the description.
     *
     * @param inputLine User's command that was typed into the command line.
     * @return A new ToDo object
     */
    public static ToDo createToDoFromUser(String inputLine) {
        // get the todo description and create a new Todo object
        String[] commandDetails = inputLine.split(" ");
        String[] todoItem = Arrays.copyOfRange(commandDetails, 1, commandDetails.length);
        String todoDescription = String.join(" ", todoItem);
        return new ToDo(todoDescription, "T");
    }

    public static ToDo createToDoFromTxt(String[] textLine) {
        // textLine - split according to "\\|"
        String symbol = textLine[0].trim();
        String description = textLine[2].trim();
        return new ToDo(description, symbol);
    }
}
