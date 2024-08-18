package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.exceptions.InvalidArgumentException;

public class DeleteCommand extends Command {
    public DeleteCommand(Calebyyy calebyyy) {
        super(calebyyy);
    }

    @Override
    public void execute(String input) throws InvalidArgumentException{        
        String[] parts = input.split(" ");
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new InvalidArgumentException();
        }
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(' ') + 1)); 
        calebyyy.deleteTask(taskNumber);
    }
}
