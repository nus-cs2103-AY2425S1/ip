package panorama.command;

import panorama.Message;

/**
 * Represents a command to display help information, including available commands and their formats.
 */
public class HelpCommand implements Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_SHORTHAND = "h";

    @Override
    public Response execute() {
        StringBuilder sb = new StringBuilder();

        sb.append("The following commands are available.\n\n");

        sb.append("Note that the part in [] indicates a shorthand. For example, ");
        sb.append(Message.CommandFormat.LIST + " means that both list and l are valid ways to use the command.\n\n");

        sb.append(Message.CommandFormat.LIST + "\n");
        sb.append(Message.CommandFormat.FIND + "\n");

        sb.append(Message.CommandFormat.BYE + "\n");
        sb.append(Message.CommandFormat.HELP + "\n");

        sb.append(Message.CommandFormat.TODO + "\n");
        sb.append(Message.CommandFormat.DEADLINE + "\n");
        sb.append(Message.CommandFormat.EVENT + "\n");

        sb.append(Message.CommandFormat.MARK + "\n");
        sb.append(Message.CommandFormat.UNMARK + "\n");
        sb.append(Message.CommandFormat.DELETE);

        return new Response(sb.toString(), false);
    }
}
