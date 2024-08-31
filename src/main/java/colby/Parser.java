package colby;

/**
 * Contains methods that take in the user's input and parse it to be used for the chatbot
 */
public class Parser {

    /**
     * Parses the first word of the input as the command and converts it to lowercase
     * @param input input that contains the command
     * @return the command part of the input
     */
    public static String parseCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    /**
     * Converts the number given in an input into an int
     * @param input user input that contains a number
     * @return the int version of the number in the string input
     */
    public static int parseIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Parses the input to create a ToDo task with the description given in the input
     * @param input the string that the user inputs starting with "todo"
     * @return new Task object
     */
    public static Task parseToDoTask(String input) {
        String description = input.substring(5);
        return new ToDo(description);
    }

    /**
     * Parses the input to create a Deadline task with the description given in the input
     * @param input the string that the user inputs starting with "deadline"
     * @return new Task object
     */
    public static Task parseDeadlineTask(String input) {
        String[] temp = input.split(" /by ");
        return new Deadline(temp[0].substring(9), temp[1]);
    }

    /**
     * Parses the input to create an Event task with the description given in the input
     * @param input the string that the user inputs starting with "event"
     * @return new Task object
     */
    public static Task parseEventTask(String input) {
        String[] temp = input.split(" /from | /to ");
        String description = temp[0].substring(6);
        String start = temp[1];
        String end = temp[2];
        return new Event(description, start, end);
    }

    public static Task parseTaskFromFile(String task) {
        return new Task(task);
    }
}
