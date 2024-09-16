package command;

import task.Task;
import tasklist.TaskList;
import exception.ParserException;
import exception.TaskListException;
import ui.Ui;

public class MarkCommand implements Command {
    private int idx;
    public MarkCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing argument idx for mark command");
        } 
        try {
            this.idx = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException("Invalid argument idx for mark command: " + args[1]);
        }
    }

    public void execute(TaskList tasks, Ui ui) throws TaskListException {
        Task task = tasks.mark(this.idx);
        ui.println("A task is marked");
        ui.println(task);
    }
    
    public boolean isExit() { return false; }
}
