package hinlok.command;

import java.util.ArrayList;

import hinlok.exceptions.HinlokException;
import hinlok.file.TaskFile;
import hinlok.parser.Parser.CommandType;
import hinlok.tasks.Task;
import hinlok.tasks.TaskList;
import hinlok.ui.Ui;

public class AddCommand extends Command {
    private final String taskDetails;
    private final CommandType type;

    public AddCommand(String taskDetails, CommandType type) {
        this.taskDetails = taskDetails;
        this.type = type;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskFile taskFile) throws HinlokException {
        switch (type) {
        case TODO:
            taskList.addTodo(taskDetails);
            break;
        case DEADLINE:
            taskList.addDeadline(taskDetails);
            break;
        case EVENT:
            taskList.addEvent(taskDetails);
            break;
        default:
            throw new HinlokException("unknown task type");
        }
    }

}
