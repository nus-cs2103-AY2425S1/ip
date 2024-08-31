package command;

import utility.Storage;
import utility.TaskList;
import utility.ImList;
import utility.Ui;

public class UnmarkCommand extends Command {
    private final ImList<Integer> listToUnmark;

    public UnmarkCommand() {
        super();
        this.listToUnmark = new ImList<Integer>();
    }

    private UnmarkCommand(ImList<Integer> listToUnmark) {
        super();
        this.listToUnmark = listToUnmark;
    }

    @Override
    public Command parseArguments(String unparsedArguments) {
        ImList<Integer> splittedArguments = new ImList<Integer>();
        for (String s : unparsedArguments.strip().split(" ")) {
            try {
                splittedArguments = splittedArguments.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                Ui.say("unable to convert argument from string to number\n");
            }
        } 
        return new UnmarkCommand(splittedArguments);
    }

    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        if (listToUnmark.size() == 0) {
            Ui.say("which number task would you like me to mark undone?\n");
            return taskList;
        } 
        for (int i : listToUnmark) {
            if (taskList.isValidIndex(i - 1)) {
                taskList = taskList.markTaskAsUndone(i - 1);
                Ui.say("OK, I've marked this task as not done yet:\n"
                        + taskList.get(i - 1).toString() + "\n");
            } else {
                Ui.say("index out of range\n");
            }
        }
        return taskList;
    }

}
