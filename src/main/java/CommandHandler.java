package ip.derrick;

import java.util.ArrayList;

/**
 * Handles the commands that the user inputs and execute the corresponding outputs.
 */
public class CommandHandler {

    private Commands command;
    private boolean isExit;

    /**
     * Initializes the class with the specified command.
     *
     * @param command enum value from Commands class
     */
    public CommandHandler(Commands command) {
        this.isExit = false;
        this.command = command;
    }

    /**
     * Checks whether the user would like to exit the program.
     *
     * @return A Boolean value indicating whether the user would like to exit the program.
     */
    public boolean checkExit() {
        return this.isExit;
    }

    /**
     * Executes the command given by the user.
     *
     * @param input The string input given by the user, typically a command.
     * @param tasks The TaskList containing all the tasks managed by the user.
     * @param storage The storage responsible for saving and loading the TaskList.
     * @param ui The Ui instance that handles user interactions.
     */
    public void execute(String input, TaskList tasks, Storage storage, Ui ui) {
        switch (command) {
        case BYE:
            this.isExit = true;
            break;
        case LIST:
            tasks.list();
            break;
        case MARK:
            try {
                tasks.markItem(input);
                storage.saveTasksToFile(tasks);
            } catch (MissingPositionException | MissingItemException e) {
                System.out.println(e.getMessage());
            }
            break;
        case UNMARK: {
            try {
                tasks.unmarkItem(input);
                storage.saveTasksToFile(tasks);
            } catch (MissingPositionException | MissingItemException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case FIND: {
            try {
                tasks.findItem(input);
            } catch (MissingPositionException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case DELETE: {
            try {
                tasks.delete(input);
                storage.saveTasksToFile(tasks);
            } catch (MissingItemException | MissingPositionException | EmptyListException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case TODO: {
            try {
                tasks.addTodo(input);
                storage.saveTasksToFile(tasks);
            } catch (InvalidDescriptionException | DuplicateItemException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case DEADLINE: {
            try {
                tasks.addDeadline(input);
                storage.saveTasksToFile(tasks);
            } catch (InvalidDescriptionException | DuplicateItemException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case EVENT: {
            try {
                tasks.addEvent(input);
                storage.saveTasksToFile(tasks);
            } catch (InvalidDescriptionException | DuplicateItemException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        case UNKNOWN: {
            System.out.println("Please specify the type of item that you wish to add ( Todo / Event / Deadline )");
            break;
        }
        }
    }
}
