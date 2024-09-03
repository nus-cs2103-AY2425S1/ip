package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;
import talker.task.TaskType;

/**
 * Represents a command to add a new Task
 */
public class AddCommand extends Command {

    // String representing task
    private String input;
    // type of task
    private final TaskType taskType;

    /**
     * Constructor for new AddCommand object
     *
     * @param input String representing task to be added
     * @param taskType type of task to be added
     */
    public AddCommand(String input, TaskType taskType) {
        this.input = input;
        this.taskType = taskType;
    }

    /**
     * Executes adding command
     *
     * @param list list task is to be added into
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @return String representing outcome of this event
     * @throws TalkerException if unable to create task
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        switch (taskType) {
        case TODO:
            return list.createToDo(input, ui);
        case DEADLINE:
            return list.createDeadline(input, ui);
        case EVENT:
            return list.createEvent(input, ui);
        default:
            throw new TalkerException("Invalid task type");
        }

    }
}
