package main.commands;
import main.source.*;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.outputList(tasks);
    }
}
