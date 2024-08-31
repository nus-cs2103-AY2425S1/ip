package command;

import utility.Storage;
import utility.TaskList;
import utility.ImList;
import utility.Ui;

public class DeleteCommand extends Command {
    private final ImList<Integer> listToDelete;

    public DeleteCommand() {
        super();
        this.listToDelete = new ImList<Integer>();
    }

    private DeleteCommand(ImList<Integer> listToDelete) {
        super();
        this.listToDelete = listToDelete;
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
        return new DeleteCommand(splittedArguments);
    }

    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        if (listToDelete.size() == 0) {
            Ui.say("which number task would you like me to delete?\n");
            return taskList;
        } 
        for (int i : listToDelete) {
            if (taskList.isValidIndex(i - 1)) {
                Ui.say("Noted. I've removed this task:\n"
                        + taskList.get(i - 1) + "\n");
                taskList = taskList.deleteTask(i - 1);
            } else {
                Ui.say("index out of range\n");
            }
        }
        return taskList;
    }

}
