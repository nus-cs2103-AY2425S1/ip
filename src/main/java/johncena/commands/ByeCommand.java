package johncena.commands;

import johncena.art.Logo;


/**
 * The {@code ByeCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "bye" command, which prints a farewell message
 * and the ASCII art logo, then exits the application.
 */
public class ByeCommand implements Command {


    /**
     * Executes the "bye" command. Prints a farewell message and the ASCII art logo,
     * then exits the application.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(" Bye. Hope to see you again soon!\n");
        sb.append("This was\n");
        sb.append(Logo.getLogo()).append("\n");
        sb.append("signing off!\n");
        sb.append("____________________________________________________________\n");
        System.exit(0);
        return sb.toString();
//        System.out.println("____________________________________________________________");
//        System.out.println(" Bye. Hope to see you again soon!");
//        System.out.println("This was");
//        System.out.println(Logo.getLogo());
//        System.out.println("signing off!");
//        System.out.println("____________________________________________________________");
//        System.exit(0);
    }
}
