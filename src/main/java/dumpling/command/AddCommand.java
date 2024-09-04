package dumpling.command;

import dumpling.DumplingException;
import dumpling.Pair;
import dumpling.task.Task;
import dumpling.task.TaskList;
import dumpling.Ui.Ui;
import dumpling.Storage;
import dumpling.Parser;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) {
        Pair<Task, String> pair = Parser.add(this.userInput, this.commandEnum, taskList.getNumItems());
        taskList.add(pair.getFirst());
        storage.save(taskList);
        return pair.getSecond();
    }

    public boolean isExit() {
        return false;
    }
}
