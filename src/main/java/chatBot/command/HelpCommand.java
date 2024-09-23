package chatbot.command;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;

/**
 * HelpCommand is a command to help the user
 */
public class HelpCommand extends Command {
    private final String keyword;

    /** Constructor method */
    public HelpCommand(String s) {
        this.keyword = s.strip();
    }


    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return switch (keyword) {
        case "todo" -> "Command format: todo <description>";
        case "deadline" -> "Command format: deadline <description> /by <date>";
        case "event" -> "Command format: event <description> /from <date> /to <date>";
        case "mark" -> "Command format: mark <index>";
        case "unmark" -> "Command format: unmark <index>";
        case "delete" -> "Command format: delete <index>";
        case "find" -> "Command format: find <keyword>";
        case "list" -> "Command format: list";
        case "bye" -> "Command format: bye";
        default -> "These are the commands accepted: todo , deadline ,"
                + " event , list, mark , unmark , bye , delete, find\n"
                + "If you require any further assistance, please type "
                + "'help <command>'";
        };
    }
}
