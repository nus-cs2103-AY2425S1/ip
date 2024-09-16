package command;

import task.Task;
import tasklist.TaskList;
import exception.ParserException;
import exception.TaskListException;
import ui.Ui;

public class UnmarkCommand implements Command {
    private int idx;
    public UnmarkCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing argument idx for unmark command");
        } 
        try {
            this.idx = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException("Invalid argument idx for unmark command: " + args[1]);
        }
    }

    public void execute(TaskList tasks, Ui ui) throws TaskListException {
        Task task = tasks.unmark(this.idx);
        ui.println("A task is unmarked");
        ui.println(task);
    }
    
    public boolean isExit() { return false; }
}
