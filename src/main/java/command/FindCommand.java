package command;

import exception.ParserException;
import tasklist.TaskList;
import ui.Ui;

public class FindCommand implements Command {
    private String pattern;
    public FindCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing argument pattern for command find");
        }
        this.pattern = args[1];
    }

    public void execute(TaskList tasks, Ui ui) {
        TaskList filteredTasks = tasks.find(this.pattern);
        ui.println("The filtered tasks list as follow:");
        for (int i = 0; i < filteredTasks.size(); ++i) {
            ui.println((i + 1) + ". " + filteredTasks.get(i));
        }
    }

    public boolean isExit() { return false; }
}
