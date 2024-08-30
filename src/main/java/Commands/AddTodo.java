package Commands;

import Tasks.Todos;
import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;

/**
 * The AddTodo class represents a command to add a todo task to the reminder list.
 * This command parses the input to extract the task description.
 * If the input is invalid or incomplete, an exception is thrown.
 */

public class AddTodo extends Command {

    /**
     * Executes the AddTodo command, adding a new todo task to the reminder.
     *
     * @param input The user input string containing the task description.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If the input is incomplete or incorrectly formatted.
     */

    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            Todos todoTask = new Todos(command[1]);
            reminder.addTodo(todoTask);
            ui.print("Got it. I've added this task:");
            ui.print("    " + todoTask.toString());
            ui.print("Now you have " + reminder.size() + " tasks in the list.");
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M TODO-ING!");
        }
        return true;
    }
}
