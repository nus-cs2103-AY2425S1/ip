package bob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bob.task.Task;

/**
 * Represents the chat bot Bob.
 */
public class Bob {

    private final List<Task> tasks = new ArrayList<>();

    /**
     * Prints a help message. Will be run when the user types "help", or at the start of the program.
     */
    public static String printHelpString() {
        String helpString = "Hi! Here are the list of commands availible:\n" +
            "0. list - Lists all tasks\n" +
            "1. todo <description> - Adds a todo task\n" +
            "2. deadline <description> /by <date> <time> - Adds a deadline task\n" +
            "3. event <description> /at <date> <time> - Adds an event task\n" +
            "4. mark <task number> - Marks a task as done\n" +
            "5. unmark <task number> - Unmarks a task as done\n" +
            "6. delete <task number> - Deletes a task\n" +
            "7. find <keyword> - Finds tasks with the keyword\n" +
            "8. help - Displays the help message\n" +
            "9. bye - Exits the program";

        return helpString;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {

        try {
            if (userInput.equalsIgnoreCase("bye")) {
                return "Bye. Hope to see you again soon!";
            } else if (userInput.equalsIgnoreCase("list")) {
                return Parser.executeListCommand(tasks);
            } else if (userInput.startsWith("mark") 
                    || userInput.startsWith("unmark") 
                    || userInput.startsWith("delete")) {
                return Parser.executeTaskModificationCommands(userInput, tasks);
            } else if (userInput.startsWith("todo") 
                    || userInput.startsWith("deadline") 
                    || userInput.startsWith("event")
                    || userInput.startsWith("find")) {
                return Parser.executeTaskCreationCommands(userInput, tasks);
            } else if (userInput.startsWith("help")) {
                return printHelpString();
            } else {
                throw new BobException("I'm sorry, but I don't know what that means :(");
            }
        } catch (BobException | IOException e) {
            return e.getMessage();
        }
    }

}
