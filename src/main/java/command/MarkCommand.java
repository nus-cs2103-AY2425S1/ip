package command;

import utility.ImList;
import utility.Storage;
import utility.TaskList;
import utility.Ui;

/**
 * Marks {@link Task} within the {@link TaskList}.
 */
public class MarkCommand extends Command {
    private final ImList<Integer> listToMark;

    /**
     * Creates a {@link MarkCommand} object without any information on
     * the details of the {@link Task}.
     */
    public MarkCommand() {
        this.listToMark = new ImList<Integer>();
    }

    private MarkCommand(ImList<Integer> listToMark) {
        super();
        this.listToMark = listToMark;
    }

    /**
     * Parses the input string accordingly into each respective attributes.
     * Requires at least one valid task index.
     *
     * @param unparsedArguments complete string of unparsed argument.
     * @return a new {@link MarkCommand} with the correctly parsed argument.
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
        return new MarkCommand(splittedArguments);
    }

    /**
     * Marks the {@link Task} from the {@link TaskList} as done and prints a succes message.
     *
     * @param taskList the {@link TaskList} of which the {@link Task} will be marked as done.
     * @param storage  not used in this command.
     * @return modified {@link TaskList} with the correct {@link Task} marked as done.
     */
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        if (listToMark.size() == 0) {
            Ui.say("which number task would you like me to mark as done?\n");
            return taskList;
        }
        assert(!listToMark.isEmpty());
        for (int i : listToMark) {
            if (taskList.isValidIndex(i - 1)) {
                assert(i < taskList.size() && i >= 0);
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
