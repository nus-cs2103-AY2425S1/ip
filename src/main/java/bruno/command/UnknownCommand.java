package bruno.command;

import bruno.task.TaskList;

public class UnknownCommand extends Command {
    public UnknownCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "I did not understand, please tell me something I can comprehend";
    }
}
