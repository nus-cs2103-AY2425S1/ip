package Tuesday.command;

import Tuesday.task.Deadline;
import Tuesday.task.Event;
import Tuesday.task.Task;
import Tuesday.task.ToDo;
import Tuesday.util.Storage;
import Tuesday.util.Ui;

public class AddCommand extends Command{
    // variables
    private final String commandType;
    private final String commandPostfix;

    /**
     * Constructor for AddCommand
     *
     * @param commandType Type of command to be called
     * @param commandPostfix The postfix of the command call
     */
    public AddCommand(String commandType, String commandPostfix) {
        super(commandType);
        this.commandType = commandType;
        this.commandPostfix = commandPostfix;
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
        switch (this.commandType) {
            case "todo" -> {
                ToDo taskItem = new ToDo(this.commandPostfix);
                storage.save_to_datafile(taskItem);
                ui.showTaskCount();
            }
            case "deadline" -> {
                String[] commandPostfixSplitBy = this.commandPostfix.split("/by ", 2);
                Deadline deadlineItem = new Deadline(commandPostfixSplitBy[0], commandPostfixSplitBy[1]);
                storage.save_to_datafile(deadlineItem);
                ui.showTaskCount();
            }
            case "event" -> {
                String[] SplitFrom = this.commandPostfix.split("/from ", 2);
                String[] SplitTo = SplitFrom[1].split(" /to ", 2);
                Event eventItem = new Event(SplitFrom[0], SplitTo[0], SplitTo[1]);
                storage.save_to_datafile(eventItem);
                ui.showTaskCount();
            }
        }
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
