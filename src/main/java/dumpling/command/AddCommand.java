package dumpling.command;

import dumpling.DumplingException;
import dumpling.Pair;
import dumpling.Parser;
import dumpling.Storage;
import dumpling.task.Task;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

/**
 * AddCommand class, inherits Command
 */
public class AddCommand extends Command {

    private String userInput;
    private CommandEnum commandEnum;

    /**
     * Command that adds a Task to a TaskList. The Task can be either ToDo, Deadline or Event.
     * @param userInput Full command string provided by the user
     * @param commandEnum Command enum that indicates if a ToDo, Deadline or Event is to be added.
     */
    public AddCommand(String userInput, CommandEnum commandEnum) {
        this.userInput = userInput;
        this.commandEnum = commandEnum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DumplingException {
        ui.echo(executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) throws DumplingException {
        Pair<Task, String> pair = Parser.add(this.userInput, this.commandEnum, taskList.getNumItems());
        taskList.add(pair.getFirst());
        storage.save(taskList);
        return pair.getSecond();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
