package command;

import utility.Storage;
import utility.TaskList;
import utility.ImList;
import utility.Ui;

public class MarkCommand extends Command {
    private final ImList<Integer> listToMark;

    public MarkCommand() {
        this.listToMark = new ImList<Integer>();
    }

    private MarkCommand(ImList<Integer> listToMark) {
        super();
        this.listToMark = listToMark;
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
        return new MarkCommand(splittedArguments);
    }
    
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        if (listToMark.size() == 0) {
            Ui.say("which number task would you like me to mark as done?\n");
            return taskList;
        } 
        for (int i : listToMark) {
            if (taskList.isValidIndex(i - 1)) {
                taskList = taskList.markTaskAsDone(i - 1);
                Ui.say("Nice! I've marked this task as done:\n"
                        + taskList.get(i - 1) + "\n");
            } else {
                Ui.say("index out of range\n");
            }
        }
        return taskList;
    }

}
