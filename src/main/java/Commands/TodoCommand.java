package commands;

import applemazer.*;
import tasks.*;

public class TodoCommand extends Command {
    private final String desc;

    /**
     * Constructor for a TodoCommand object.
     * @param desc Description of the task.
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the "to-do" command by adding a To-do task to the task list.
     * @param tasks   The task list to use.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task task = new Todo(desc);
        tasks.add(task);
        task.printTaskAddedMessage();
        storage.save();
    }

    /**
     * @return Returns true as the chatbot should continue running after executing the "to-do" command.
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
