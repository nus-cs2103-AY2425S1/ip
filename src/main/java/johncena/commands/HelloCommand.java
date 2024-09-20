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
    public void execute() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + Logo.getLogo());
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
