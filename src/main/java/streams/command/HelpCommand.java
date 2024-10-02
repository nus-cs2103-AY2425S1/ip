package streams.command;

import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to help user with command formats.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command, displaying the list of commands with formats to help the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display the list of commands
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        ui.showMessage("""
                               Below is a list of all possible commands and their formart :\s
                                list\s
                                done [task number]\s
                                unmark [task number]\s
                                delete [task number]\s
                                todo [task description]
                                deadline [task description] /by [YYYY-MM-dd HH:mm]\s
                                event [task description] /from [YYYY-MM-dd HH:mm]/to [YYYY-MM-dd HH:mm]\s
                                find [keyword]\s
                                sort-deadline\s
                                list-date [YYYY-MM-dd]\s
                                list-week\s
                                tag [task number] [tag description]\s
                                list-tag [keyword]\s
                                tag-remove [task number] [description]\s
                                bye\s""");
    }
}
