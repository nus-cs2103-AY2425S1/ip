package sentinel.command;

import sentinel.exception.SentinelException;
import sentinel.task.Task;
import sentinel.ui.Ui;
import sentinel.utils.Parser;
import sentinel.utils.SentinelList;
import sentinel.Sentinel;

public class CreateTaskCommand extends Command {
    private final Sentinel.CommandType commandType;

    public CreateTaskCommand(Ui ui, SentinelList list, Sentinel.CommandType commandType) {
        super(ui, list);
        this.commandType = commandType;
    }

    @Override
    public void execute(String input) throws SentinelException {
        Task newTask = Parser.parseTask(commandType, input, ui);
        if (newTask != null) {
            list.add(newTask);
            ui.showAddedTask(newTask);
        }
    }
}