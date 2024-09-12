package command;

import utility.ImList;
import utility.Storage;
import utility.TaskList;
import utility.Ui;

/**
 * Changes the {@link Task} fomr the {@link TaskList} back to not done.
 */
public class UnmarkCommand extends Command {
    private final ImList<Integer> listToUnmark;

    /**
     * Creates a {@link UnmarkCommand} object without any information on
     * the details of the {@link Task}.
     */
    public UnmarkCommand() {
        super();
        this.listToUnmark = new ImList<Integer>();
    }

    private UnmarkCommand(ImList<Integer> listToUnmark) {
        super();
        this.listToUnmark = listToUnmark;
    }

    /**
     * Parses the input string accordingly into each respective attributes.
     * Requires at least one valid task index.
     *
     * @param unparsedArguments complete string of unparsed argument.
     * @return a new {@link UnmarkCommand} with the correctly parsed argument.
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
        return new UnmarkCommand(splittedArguments);
    }

    /**
     * Changes the {@link Task} from the {@link TaskList} as not done and prints a succes message.
     *
     * @param taskList the {@link TaskList} of which the {@link Task} will be changed to not done.
     * @param storage  not used in this command.
     * @return modified {@link TaskList} with the correct {@link Task} changed as not done.
     */
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        if (listToUnmark.size() == 0) {
            Ui.say("which number task would you like me to mark undone?\n");
            return taskList;
        }
        assert(!listToUnmark.isEmpty());
        for (int i : listToUnmark) {
            if (taskList.isValidIndex(i - 1)) {
                assert(i < taskList.size() && i >= 0);
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
