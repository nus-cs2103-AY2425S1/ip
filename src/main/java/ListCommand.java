/**
 * ListCommand represents the command to list the entries in the to-do list
 */
public class ListCommand extends Command {

    /** Prefix used to invoke the list command **/
    public static final String COMMAND_PREFIX = "list";

    public ListCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TodoList list, Ui ui, FileStore store) {
        ui.showText(String.format("These are %s entries on your todo:", list.getTotal()));
        ui.showText(String.join("\n", list.listItems()));
    }

}
