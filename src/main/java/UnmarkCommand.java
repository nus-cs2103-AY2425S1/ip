/**
 * UnmarkCommand represents the command to unmark a completed task as incomplete in the to-do list
 */
public class UnmarkCommand extends Command {

    /** Prefix used to invoke the mark command **/
    public static final String COMMAND_PREFIX = "unmark";

    public UnmarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Specify index to unmark");
        }

        int itemIndex = -1;

        // Check for valid index
        try {
            itemIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new TohruException(String.format("%s is not valid index", arguments));
        }

        list.markIncomplete(itemIndex);

        ui.showText("Alright! I have set this task as not done:");
        ui.showText(list.getItemStatus(itemIndex));

        store.saveTodoList(list.getTodoList());

    }

}
