package johncena.commands;

import johncena.art.Logo;

public class ByeCommand implements Command {

    @Override
    public void execute() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("This was");
        System.out.println(Logo.getLogo());
        System.out.println("signing off!");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }
}
