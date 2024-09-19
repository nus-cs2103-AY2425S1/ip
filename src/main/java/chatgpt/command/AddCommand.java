package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.Deadlines;
import chatgpt.task.Events;
import chatgpt.task.Task;
import chatgpt.task.TaskList;
import chatgpt.task.ToDos;
import chatgpt.ui.Ui;


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
    public AddCommand(String taskType, String input) {
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
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        Task newTask = processNewTask();
        tasks.add(newTask);
        storage.save(tasks);
        return ui.showAddTask(newTask, tasks.size());
    }

    private Task processNewTask() throws ChatBotException {
        String task;
        String note = "NA";
        if (input.split("/note").length != 1) {
            note = input.split("/note")[1];
            input = input.split("/note")[0];
        }
        switch (taskType) {
        case TODO:
            return new ToDos(input, note);

        case DEADLINE:
            checkValidDeadlineField();
            task = input.split("/by")[0];
            String deadline = input.split("/by")[1];
            return new Deadlines(task, note, deadline);

        case EVENT:
            checkValidEventField();
            task = input.split("/from")[0];
            String date = input.split("/from")[1];
            String startDate = date.split("/to")[0];
            String endDate = date.split("/to")[1];
            return new Events(task, note, startDate, endDate);

        default:
            assert false : "Wrong task type was passed";
            return null;
        }
    }

    private void checkValidDeadlineField() throws ChatBotException {
        if (input.split("/by").length == 1) {
            throw new ChatBotException("\t Oh no!![@.@] Deadline cannot be empty"
                + "\n\t Enter the deadline in the format: deadline <Task> /by <Deadline>");
        }
    }

    private void checkValidEventField() throws ChatBotException {
        if (input.split("/from").length == 1 ||
            input.split("/to").length == 1) {
            throw new ChatBotException("\t Oh no!!(;-;) Event period cannot be empty"
                + "\n\t Enter the event in the format: event <Task> "
                + "/from <Start Date/Time> /to <End Date/Time>");
        }
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
