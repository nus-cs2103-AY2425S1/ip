package monique.command;

import java.util.stream.IntStream;

import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This command prints all tasks with their respective indices to the console.
 */
public class ListCommand extends Command {

    /**
     * Constructs a <code>ListCommand</code> instance.
     * This constructor initializes the command without any additional parameters.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the list command, which involves printing all tasks in the task list.
     * Each task is displayed with its index in the list. If the task list is empty,
     * an empty list message is shown using the <code>Ui</code> instance.
     *
     * @param tasks the <code>TaskList</code> containing tasks to be listed
     * @param ui the <code>Ui</code> instance used to display the empty list message
     * @param storage the <code>Storage</code> instance (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        IntStream.range(0, tasks.getNumItems())
                .forEach(i -> {
                    System.out.println((i + 1) + "." + tasks.getTask(i));
                });
        if (tasks.getNumItems() == 0) {
            ui.emptyListMessage();
        }
    }

    /**
     * Returns whether this chatbot will be active after the command executes.
     * @return true since bot should remain active after the list command
     */
    @Override
    public boolean isActive() {
        return true;
    }
}
