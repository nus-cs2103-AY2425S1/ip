package botty.commands;

import botty.exceptions.BottyException;
import botty.tasks.DeadlineData;
import botty.tasks.EventData;
import botty.tasks.Task;
import botty.tasks.TaskData;
import botty.tasks.TaskManager;
import botty.tasks.TodoData;

/**
 * Defines the behaviour of the delete command
 */
public class UpdateCommand extends Command {
    /**
     * Executes the update command, updating the task at the given index
     * @param taskManager
     * @param parsedInput
     * @return success message
     * @throws BottyException if given input is invalid
     */
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            int taskIndex = Integer.parseInt(parsedInput.getArgument("main")) - 1;
            Task<? extends TaskData> task = taskManager.getTask(taskIndex);
            TaskData data;
            switch(task.getTaskType()) {
            case TODO:
                data = new TodoData(parsedInput);
                break;
            case DEADLINE:
                data = new DeadlineData(parsedInput);
                break;
            case EVENT:
                data = new EventData(parsedInput);
                break;
            default:
                throw new BottyException("Invalid task type");
            }
            taskManager.updateTask(taskIndex, data);

            return "Got it! I have updated the following task:\n" + task;
        } catch (NumberFormatException ex) {
            throw new BottyException("I don't quite know what you want me to do. "
                    + "Do indicate which task to edit with its number!");
        }
    }

    /**
     * Returns true as the command will edit the task list
     */
    @Override
    public boolean changesTaskList() {
        return true;
    }
}
