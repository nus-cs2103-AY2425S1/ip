package seedu.maxine.parser;

import seedu.maxine.Storage;
import seedu.maxine.TaskList;
import seedu.maxine.Ui;
import seedu.maxine.command.Command;
import seedu.maxine.exception.MaxineException;
/**
 * The Parser class handles the parsing of user inputs in the Maxine application.
 * It interprets commands related to task management and delegates the operations
 * to the appropriate command handlers.
 */
public class Parser {
    private Ui ui;
    private TaskList list;
    private Storage storage;
    private Command command;

    /**
     * Constructs new instance of Parser class
     */
    public Parser() {
        ui = new Ui();
        list = new TaskList();
        storage = new Storage("data/maxine.txt");
        command = new Command(storage, ui, list);
    }

    /**
     * Parses the user's inputs and sends the corresponding response.
     * The parser accepts the commands bye, list, delete, mark, unmark, find,
     * todo, deadline and event.
     * <p>
     * The method handles exceptions internally when users do not follow
     * the correct format of typing out a task
     * @param input Input string that is to be parsed for corresponding action
     */
    public String parse(String input) {
        try {
            String[] answer = input.toLowerCase().split(" ");
            switch (answer[0]) {
            case ("bye"):
                return command.handleBye(input);
            case ("list"):
                return command.handleList(input);
            case ("mark"):
                return command.handleMark(input);
            case ("unmark"):
                return command.handleUnmark(input);
            case ("todo"):
                return command.handleTodo(input);
            case ("deadline"):
                return command.handleDeadline(input);
            case ("event"):
                return command.handleEvent(input);
            case ("delete"):
                return command.handleDelete(input);
            case ("find"):
                return command.handleFind(input);
            default:
                throw new MaxineException("Oh no.. this command is not recognised");
            }
        } catch (MaxineException e) {
            return ui.showError(e.getMessage());
        }
    }
}
