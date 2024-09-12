package tuesday.command;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

public class EditCommand extends Command {
    private String responseMessage;
    private String priority;
    private int index;

    /**
     * Constructor for AddCommand
     *
     * @param commandType Type of command to be called
     */
    public EditCommand(String commandType, String postfix) {
        super(commandType);
        String[] postfixSplit = postfix.split(" /priority ", 2);
        System.out.println(postfixSplit[0]);
        this.index = Integer.parseInt(postfixSplit[0]);
        this.priority = postfixSplit[1];
    }

    /**
     * Checks the command type and invokes its corresponding methods
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        Task.getTaskArrayList().get(this.index - 1).setPriority(this.priority);
        this.responseMessage = "Task has been successfully updated.";
    }

    public String getString() {
        assert this.responseMessage != null : "The execute() method must be called first";
        return this.responseMessage;
    }

    /**
     * Use to exit the program
     *
     * @return false and do not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
