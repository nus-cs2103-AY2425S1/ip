package asura.commands;

import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

public class HelpCommand extends Command{

    public HelpCommand() {}
    
    /**
     * Outputs the help page to the user
     * @param tasklist The list of tasks of the user.
     * @param ui The UI object to give user feedback.
     * @param storage The storage object to save/load tasks.
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        output.append("Asura Help Page\n");
        output.append("-----------------------\n");
        output.append("Commands\n");
        output.append("list                                displays the items in the task list.\n");
        output.append("todo <Task name>                     Adds a new todo task into the task list.\n");
        output.append("deadline <Task name> /by <Date> <Time>      Adds a new deadline task into the task list.\n");
        output.append("event <Task name> /from <DateTime> /to <DateTime>      Adds a new deadline task into the task list.\n");
        output.append("mark <Task number>                  Marks the indicated task as completed.\n");
        output.append("unmark <Task number>                Marks the indicated task as uncompleted.\n");
        output.append("delete <Task number>                Deletes the indicated task from the task list.\n");
        output.append("find <Task name>                    Searches the task list to find matching tasks to the input.\n");
        output.append("-----------------------\n");
        return output.toString();
    }

    /**
     * Indicates that the user does not want to terminate the program.
     * @return A boolean representing whether the program is to be terminated, in this case false.
     */
    public boolean isExit() {
        return false;
    }
}
