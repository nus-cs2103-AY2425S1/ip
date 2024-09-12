package bob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bob.task.Task;

/**
 * Represents the chat bot Bob.
 */
public class Bob {

    private Scanner scanner = new Scanner(System.in);
    private List<Task> tasks = new ArrayList<>();

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
                return Parser.processTaskModificationCommands(userInput, tasks);
            } else if (userInput.startsWith("todo") 
                    || userInput.startsWith("deadline") 
                    || userInput.startsWith("event")) {
                return Parser.processTaskCreationCommands(userInput, tasks);
            } else {
                throw new BobException("I'm sorry, but I don't know what that means :(");
            }
        } catch (BobException | IOException e) {
            return e.getMessage();
        }
    }

}
