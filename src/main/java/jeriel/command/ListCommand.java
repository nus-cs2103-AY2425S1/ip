package jeriel.command;
import jeriel.task.*;
import jeriel.util.*;
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
