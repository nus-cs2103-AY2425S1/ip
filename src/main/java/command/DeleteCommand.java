package command;

import utility.ImList;
import utility.Storage;
import utility.TaskList;
import utility.Ui;

/**
 * {@link Command} that deletes {@link Task} from {@link TaskList}.
 */
public class DeleteCommand extends Command {
    private final ImList<Integer> listToDelete;

    /**
     * Creates a {@link DeleteCommand} object without and information on which tasks to
     * delete.
     */
    public DeleteCommand() {
        super();
        this.listToDelete = new ImList<Integer>();
    }

    private DeleteCommand(ImList<Integer> listToDelete) {
        super();
        this.listToDelete = listToDelete;
    }

    /**
     * Parses the input string accordingly into each respective attributes.
     * Requires at least one valid task index.
     *
     * @param unparsedArguments complete string of unparsed argument.
     * @return a new {@link DeleteCommand} with the correctly parsed argument.
     */
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

    /**
     * Delets {@link Task} from input {@link TaskList} via index.
     *
     * @param taskList {@link TaskList} of which the {@link Task} are removed.
     * @param storage not used in this command.
     * @return modified {@link TaskList} with all the deleted {@link Task}.
     */
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        if (listToDelete.size() == 0) {
            Ui.say("which number task would you like me to delete?\n");
            return taskList;
        }
        assert(!listToDelete.isEmpty());
        for (int i : listToDelete) {
            if (taskList.isValidIndex(i - 1)) {
                assert(i < taskList.size() && i >= 0);
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
