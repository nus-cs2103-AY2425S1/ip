package milo.parser;

import milo.command.*;
import milo.tasks.TaskList;
import milo.tasks.TaskTypes;

/**
* Represents Milo's logic system, how the commands are interpreted
* these commands include: list, mark, unmark, delete, to-do, deadline, event,
* bye
 */
public class Parser {

    /**
    * Interprets user input as one of the aforementioned command
    * subsequently, executes the respective command
    * given that it is formatted correctly
    * else an error is thrown and printed
     */
    public Command readInput(String userInput, TaskList todoList) {
        String[] arrOfInput = userInput.split(" ", 2);
        String action = arrOfInput[0];
        return switch (action) {
            // List command
            case "list" -> new ListCommand();
            // Find task command
            case "find" -> new FindCommand(arrOfInput);
            // Mark as complete command
            case "mark" -> new MarkCommand(Integer.parseInt(arrOfInput[1]) - 1);
            // Mark as incomplete command
            case "unmark" -> new UnmarkCommand(Integer.parseInt(arrOfInput[1]) - 1);
            // Delete task command
            case "delete" -> new DeleteCommand(Integer.parseInt(arrOfInput[1]) - 1);
            // Add task commands
            // Todos
            case "todo" -> new AddCommand(arrOfInput, TaskTypes.TaskType.TODO);
            // Deadline
            case "deadline" ->
                // Check case where deadline empty
                    new AddCommand(arrOfInput, TaskTypes.TaskType.DEADLINE);
            // Event
            case "event" -> new AddCommand(arrOfInput, TaskTypes.TaskType.EVENT);
            case "bye" ->
                // Bye users
                    new GreetCommand("bye");
            case "hi" ->
                // Bye users
                    new GreetCommand("hi");
            default -> new InvalidCommand();
        };
    }
}
