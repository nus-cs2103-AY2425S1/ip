package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.task.TaskList;
import chatgpt.task.Task;
import chatgpt.task.ToDos;
import chatgpt.task.Deadlines;
import chatgpt.task.Events;
import chatgpt.ui.Ui;
import chatgpt.storage.Storage;

/**
 *  The AddCommand class represents a command to add a Task to the TaskList.
 */
public class AddCommand extends Command {
    /** Enum of the possible task types **/
    private enum Type {
        TODO,
        EVENT,
        DEADLINE
    }
    /** Input for task creation; description and timing(s) if applicable **/
    private String input;
    /** The type of task to be created **/
    private Type taskType;

    /**
     * The constructor for an AddCommand with the given inputs and task type.
     *
     * @param taskType
     * @param input
     */
    public AddCommand(String taskType, String input){
        this.taskType = Type.valueOf(taskType);
        this.input = input;
    }

    /**
     * {@inheritDoc}
     *
     * In AddCommand, it creates a new Task subclass based on the task type given,
     * adds it to the local tasklist and saves it to the text file.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     * @throws ChatBotException if there are missing inputs or arguments with wrong format
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        Task newTask = null;
        String task;
        switch (taskType) {
        case TODO:
            newTask = new ToDos(input);
            break;

        case DEADLINE:
            task = input.split("/by")[0];
            String deadline = input.split("/by")[1];
            newTask = new Deadlines(task, deadline);
            break;

        case EVENT:
            task = input.split("/from")[0];
            String date = input.split("/from")[1];
            String startDate = date.split("/to")[0];
            String endDate = date.split("/to")[1];
            newTask = new Events(task, startDate, endDate);
            break;
        }
        tasks.add(newTask);
        ui.showAddTask(newTask, tasks.size());
        storage.save(tasks);
    }
    /**
     * {@inheritDoc}
     *
     * In AddCommand, it will always return false.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
