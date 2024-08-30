package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.BeeBooExceptions;
import beeboo.task.Tasks;

public class FindCommand extends Command{
    String command;

    public FindCommand(String command) {
        super(command);
        this.command = command;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BeeBooExceptions {
        String list = "Here are the matching results: \n";
        for (int i = 0; i < tasks.getSize() ; i++) {
            Tasks task = tasks.get(i);
            if(task.toString().contains(command)) {
                list = list + task.toString() + "\n";
            }
        }

        ui.produceList(list);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
