package Command;

import Tools.Storage;
import Tools.TaskList;
import Exception.MissingDateException;
import Exception.EmptyDescriptionException;
import Tools.Ui;

/**
 * This class represents an addition command that modifies the task list based on user input.
 * It can handle adding new tasks such as todos and deadlines, as well as deleting tasks from the list.
 */
public class AddCommand extends Command {

    /**
     * Constructs an AddCommand with references to the task list, storage, and the specific command string.
     *
     * @param tasks    The task list to manipulate.
     * @param storage  The storage utility to handle task data persistence.
     * @param command  The command string describing the specific operation to be performed.
     */
    public AddCommand(TaskList tasks, Storage storage, String command) {
        super(tasks, storage, command);
    }

    /**
     * Executes the command as specified by the initial command string. This method can modify the task list
     * by adding or deleting tasks based on the command type.
     * It handles exceptions related to task creation such as missing descriptions or dates.
     */
    private void execute() {
        try {
            if (command.startsWith("delete")) {
                tasks.deleteTask(Integer.parseInt(command.substring(7)) - 1);
            } else if (command.startsWith("todo")) {
                tasks.addTodoTask(command);
            } else if (command.startsWith("deadline")) {
                tasks.addDeadlineTask(command);
            }
        } catch (EmptyDescriptionException | MissingDateException e) {
            System.out.println(e.getMessage());
        }
    }
}
