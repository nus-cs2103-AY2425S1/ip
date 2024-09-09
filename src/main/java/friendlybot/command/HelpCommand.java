package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;

/**
 * HelpCommand is a Command that displays helpful information to the user.
 */
public class HelpCommand extends Command {
    private String keyword;

    public HelpCommand(String keyword) {
        this.keyword = keyword;
    }

    public HelpCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder helpMessage = new StringBuilder();

        if (this.keyword == null) {
            helpMessage.append("This is the list of all my available commands:\n");
            helpMessage.append("help list todo deadline event mark unmark delete find date bye\n");
            helpMessage.append("Use help <command> for more information!");

            Ui.print("This is the list of all my available commands:");
            Ui.print("help list todo deadline event mark unmark delete find date bye");
            Ui.print("Use help <command> for more information!");

            return helpMessage.toString();
        }

        return switch (this.keyword) {
        case "help" -> this.toString();
        case "list" -> new ListCommand().toString();
        case "todo", "deadline", "event" -> new AddCommand().toString();
        case "mark", "unmark" -> new MarkCommand().toString();
        case "delete" -> new DeleteCommand().toString();
        case "find" -> new FindCommand().toString();
        case "date" -> new DateCommand().toString();
        case "bye" -> new ExitCommand().toString();
        default -> {
            helpMessage.append("I could not find that command.\n");
            helpMessage.append("This is the list of all my available commands:\n");
            helpMessage.append("help list todo deadline event mark unmark delete find date bye\n");
            helpMessage.append("Use help <command> for more information!");

            yield helpMessage.toString();
        }
        };
    }

    @Override
    public String toString() {
        return """
                help - Shows the information of all available commands.
                help <command> - Shows the information of the command specified.""";
    }
}
