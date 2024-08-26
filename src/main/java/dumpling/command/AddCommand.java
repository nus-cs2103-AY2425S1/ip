package dumpling.command;

import dumpling.DumplingException;
import dumpling.Pair;
import dumpling.task.Task;
import dumpling.task.TaskList;
import dumpling.Ui;
import dumpling.Storage;

public class AddCommand extends Command {

    private String userInput;
    private CommandEnum commandEnum;

    public AddCommand(String userInput, CommandEnum commandEnum) {
        this.userInput = userInput;
        this.commandEnum = commandEnum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DumplingException {
        Pair<Task, String> pair = storage.add(this.userInput, this.commandEnum, tasks.getNumItems());
        tasks.add(pair.getFirst());
        ui.echo(pair.getSecond());
    }

    public boolean isExit() {
        return false;
    }
}
