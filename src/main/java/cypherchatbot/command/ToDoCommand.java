package cypherchatbot.command;

import cypherchatbot.CypherException;
import cypherchatbot.task.Task;
import cypherchatbot.task.ToDo;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

/**
 * The ToDo class represents a command to create a new ToDo task
 * and add it to the task list in the Cypher chat bot application.
 */
public class ToDoCommand extends Command {
    private String[] command;

    /**
     * Instantiates a ToDo command with a specific command.
     *
     * @param s The command argument, where the index 1 contains the description
     *          of the ToDo task to be created.
     */
    public ToDoCommand(String[] s) {
        this.command = s;
    }


    /**
     * Executes the ToDo command, creating a new Todo task, adding it to the TaskList,
     * outputting the result to the user via the Ui output method, and then finally
     * saving the task to storage.
     *
     * @param tasks   The TaskList to which the new ToDo task should be added.
     * @param ui      The Ui interface used to interact with the user.
     * @param storage The Storage file where the task data will be saved.
     * @return
     */

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CypherException{
        if (command.length != 2 || command[1].trim().isEmpty()) {
            throw new CypherException("No task is given. "
                    + "The format of the todo command is:\n todo <Description of task>");
        }

        Task todo = new ToDo(command[1]);
        tasks.addToList(todo);
        storage.addToStorage(todo.toStringInFile());

        return  ui.showAddMessage(todo, tasks.size());
    }

    /**
     * Returns false indicating that this command does not cause the application to exit.
     */
    public boolean showExitStatus() {
        return false;
    }

}
