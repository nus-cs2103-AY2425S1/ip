import java.util.StringTokenizer;

public class Parser {

    private TaskList list;
    private Storage store;

    public Parser(TaskList list, Storage store) {
        this.list = list;
        this.store = store;
    }

    public Command parse(String originalInput) throws MaheshException {
        StringTokenizer tokenizedInput = new StringTokenizer(originalInput);
        String commandString = tokenizedInput.nextToken();
        CommandNames command = CommandNames.fromString(commandString);
        switch (command) {
        case LIST:
            return new PrintCommand(list);
        case BYE:
            return new ExitCommand();
        case MARK:
            try {
                return new MarkCommand(list, store, Integer.parseInt(tokenizedInput.nextToken()) - 1, true);
            } catch (NumberFormatException err) {
                Ui.printIncompleteCommandErr(new MaheshException("Please follow the given format: mark <index>, index must be an integer"));
                return null;
            }
        case UNMARK:
            try {
                return new MarkCommand(list, store, Integer.parseInt(tokenizedInput.nextToken()) - 1, false);
            } catch (NumberFormatException err) {
                Ui.printIncompleteCommandErr(new MaheshException("Please follow the given format: unmark <index>, index must be an integer"));
                return null;
            }
        case TODO:
            try {
                Todo todo = Todo.parseTodo(tokenizedInput);
                return new AddCommand(list, store, todo);
            } catch (MaheshException err) {
                Ui.printIncompleteCommandErr(err);
                return null;
            }
        case DEADLINE:
            try {
                Deadline deadline = Deadline.parseDeadline(tokenizedInput);
                return new AddCommand(list, store, deadline);
            } catch (MaheshException err) {
                Ui.printIncompleteCommandErr(err);
                return null;
            }
        case EVENT: 
            try {
                Event event = Event.parseEvent(tokenizedInput);
                return new AddCommand(list, store, event);
            } catch (MaheshException err) {
                Ui.printIncompleteCommandErr(err);
                return null;
            }
        case DELETE:
            try {
                return new DeleteCommand(list, store, Integer.parseInt(tokenizedInput.nextToken()) - 1);
            } catch (NumberFormatException err) {
                Ui.printIncompleteCommandErr(new MaheshException("Please follow the given format: delete <index>, index must be an integer"));
                return null;
            }
        default:
            return null;
        }
    }
}
