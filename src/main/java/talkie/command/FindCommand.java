package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieInvalidArgumentException;
import talkie.exception.TalkieMissingArgumentException;
import talkie.exception.TalkieNoTaskFoundException;
import talkie.task.TaskList;

public class FindCommand extends Command {

    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieMissingArgumentException, TalkieInvalidArgumentException, TalkieNoTaskFoundException {
        String[] temp = this.fullCommand.split(" ");

        // Check if user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'find' command requires a string as argument");
        }

        // Checks if the argument is a string
        if (temp[1] instanceof String) {
            String keyword = temp[1];
            ui.findTasks(tasks,keyword);

        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'find' command requires a string as argument");
        }
    }

    /**
     * Indicates that this command does not terminate the application.
     *
     * @return {@code false}, as this command does not end the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
