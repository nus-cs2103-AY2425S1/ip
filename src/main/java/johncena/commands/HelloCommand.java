package johncena.commands;

import johncena.art.Logo;


/**
 * Represents the "hello" command.
 * A HelloCommand object corresponds to a command to greet the user.
 */
public class HelloCommand implements Command {

    /**
     * Executes the "hello" command.
     *
     * @return The response to the user.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello from\n").append(Logo.getLogo()).append("\n");
        sb.append("You can't see me! But I'm here to help. What can I do for you today?\n");
        return sb.toString();
    }
}
