package derek.command;

import derek.command.Command;

public class TaskCommand extends Command {
    public TaskCommand(String command) {
        super(command);
    }

    public String getTask(){
        String command = super.getCommand();
        int firstWord = command.indexOf(" ");
        return command.substring(firstWord + 1);

    }
}
