package commands;

import applemazer.*;
import tasks.*;
import java.util.ArrayList;

public class IntegerCommand extends Command {
    private final IntegerCommands command;
    private final int taskNumber;

    public IntegerCommand(IntegerCommands command, int taskNumber) {
        this.command = command;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {
        try {
            Task task = tasks.get(taskNumber);
            switch (command) {
                case Mark :
                    task.setDone();
                    task.printTaskSetDoneMessage();
                    storage.save();
                    break;
                case Unmark :
                    task.setUndone();
                    task.printTaskSetUndoneMessage();
                    storage.save();
                    break;
                case Delete :
                    tasks.remove(task);
                    task.printTaskDeletedMessage();
                    storage.save();
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            if (tasks.isEmpty()) {
                String emptyMessage = String.format("You have no tasks to %s.\n", command.toString().toLowerCase());
                System.err.println(emptyMessage);
            } else {
                int size = tasks.size();
                String message = String.format("""
                                               You currently have %d tasks.
                                               Please enter a number between 1 and %d.
                                               """, size, size);
                System.err.println(message);
            }
        }
    }

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
