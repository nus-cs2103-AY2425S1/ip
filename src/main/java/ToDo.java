import java.util.Arrays;

public class ToDo extends Task {

    ToDo(String inputLine) {
        // inside the program this will be called
        super(createToDo(inputLine).getDescription(), createToDo(inputLine).getSymbol());
    }

    ToDo(String description, String symbol) {
        // this is used inside the static method: createToDo
        super(description, symbol);
    }

    public static ToDo createToDo(String inputLine) {
        // get the todo description and create a new Todo object
        String[] commandDetails = inputLine.split(" ");
        String[] todoItem = Arrays.copyOfRange(commandDetails, 1, commandDetails.length);
        String todoDescription = String.join(" ", todoItem);
        return new ToDo(todoDescription, "T");
    }
}
