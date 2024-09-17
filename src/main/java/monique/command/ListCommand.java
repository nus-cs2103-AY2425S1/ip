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
    public static final String COMMAND_TYPE = "list";
    private String listResult = "";
    /**
     * Constructs a <code>ListCommand</code> instance.
     * This constructor initializes the command without any additional parameters.
     */
    public ListCommand() {
        super(COMMAND_TYPE);
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
        StringBuilder sb = new StringBuilder("Here are the tasks on your list:\n");
        IntStream.range(0, tasks.getNumItems())
                .forEach(i -> {
                    sb.append((i + 1)).append(".").append(tasks.getTask(i)).append("\n");
                });
        if (tasks.getNumItems() == 0) {
            this.listResult = ui.emptyListMessage();
        } else {
            this.listResult = sb.toString();
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

    /**
     * Retrieves the response message from the execution of the ListCommand.
     *
     * @param ui the user interface instance used to format the list results (not used in this method)
     * @return a string containing the result of the list command
     */
    public String getResponse(Ui ui) {
        return this.listResult;
    }
}
