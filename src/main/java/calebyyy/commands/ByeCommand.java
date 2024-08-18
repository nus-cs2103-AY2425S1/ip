package calebyyy.commands;

import calebyyy.Calebyyy;

public class ByeCommand extends Command {
    public ByeCommand(Calebyyy calebyyy) {
        super(calebyyy);
    }

    @Override
    public void execute(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        calebyyy.stop();
    }
}