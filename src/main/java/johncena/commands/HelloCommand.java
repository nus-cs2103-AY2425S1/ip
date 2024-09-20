package johncena.commands;

import johncena.art.Logo;


/**
 * The {@code HelloCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "hello" command, which prints a welcome message
 * and the ASCII art logo.
 */
public class HelloCommand implements Command {

    /**
     * Executes the "hello" command. Prints a welcome message and the ASCII art logo.
     */
    @Override
    public String  execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello from\n").append(Logo.getLogo()).append("\n");
        sb.append(" What can I do for you?\n");
        return sb.toString();
    }
}
