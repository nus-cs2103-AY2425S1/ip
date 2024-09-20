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
        //sb.append("____________________________________________________________\n");
        sb.append("Hello from\n").append(Logo.getLogo()).append("\n");
        sb.append(" What can I do for you?\n");
        //sb.append("____________________________________________________________\n");
        return sb.toString();

//        System.out.println("____________________________________________________________");
//        System.out.println("Hello from\n" + Logo.getLogo());
//        System.out.println(" What can I do for you?");
//        System.out.println("____________________________________________________________");
    }
}
