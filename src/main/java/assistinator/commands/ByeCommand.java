package assistinator.commands;

import java.util.Timer;
import java.util.TimerTask;

import assistinator.Storage;
import assistinator.TaskList;

/**
 * Represents bye command.
 */
public class ByeCommand extends Command {
    public ByeCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.saveTasks(tasks.getTasks());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 400);
        return "Goodbye. I hope to compute your evil schemes again soon.";
    }
}
