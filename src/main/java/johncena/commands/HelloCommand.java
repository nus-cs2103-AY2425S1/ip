package johncena.commands;

import johncena.art.Logo;

public class HelloCommand implements Command {

    @Override
    public void execute() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + Logo.getLogo());
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
