package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;
import talker.task.TaskType;

public class AddCommand extends Command {

    private String input;
    private final TaskType taskType;

    public AddCommand(String input, TaskType taskType) {
        this.input = input;
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        switch (taskType) {
            case TODO:
                list.createToDo(input, ui);
                break;
            case DEADLINE:
                list.createDeadline(input, ui);
                break;
            case EVENT:
                list.createEvent(input, ui);
                break;
        }
    }
}
