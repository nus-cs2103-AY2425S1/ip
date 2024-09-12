package duke.ui;

import duke.commands.*;
import duke.tasks.Storage;



/**
 * The main driver class of the chatbot, called by notGPT
 */
public class Parser {
    private Storage storage = new Storage();

    /**
     * Runs the entire program, reading user input and deciding what to do
     *
     * @param command first word of system input from textbox
     * @param text    remaining system input from textbox
     */
    public String parse(String command, String text) {
        switch (command) {
        case "list":
            return ListCommand.execute(storage);
        case "clear":
            return ClearCommand.execute(storage, text);
        case "delete":
            return NumberCommand.execute(storage, text, command);
        case "mark":
            return NumberCommand.execute(storage, text, command);
        case "unmark":
            return NumberCommand.execute(storage, text, command);
        case "todo":
            return TodoCommand.execute(storage, text);
        case "event":
            return EventCommand.execute(storage, text);
        case "deadline":
            return DeadlineCommand.execute(storage, text);
        case "find":
            return FindCommand.execute(storage, text);
        case "bye":
            return "It's finally over... *yawn*\nI'm heading to bed";
        default:
            return "what? type an actual command pls...";
        }
    }
}
