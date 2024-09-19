package ekud.commands;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.task.Task;
import ekud.ui.Ui;

/**
 * Represents a {@link Command} that can find tasks in some {@link TaskList}
 * which description contains some keyword.
 *
 * @author uniqly
 */
public class FindCommand extends Command {
    private static final String ERROR_MESSAGE =
            "If you are going to waste my time, could you at least not try to get me to search for nothing!!";

    /** The keyword {@link String} */
    private final String keyword;

    /**
     * Creates a new {@link FindCommand}.
     * @param keyword The {@link String} to search for in each {@link Task}.
     */
    public FindCommand(String keyword) throws EkudException {
        boolean isEmptyKeyword = (keyword == null || keyword.isEmpty());
        if (isEmptyKeyword) {
            throw new EkudException(ERROR_MESSAGE);
        }

        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);
        String responseAcknowledge = """
                Urrgh! Why do you bother me with this task!
                Fine, fine.... I will go look for them...
                Here are the results of my search:""";
        String responseTaskFoundFormat = "%d. %s";
        String responseNoTaskFound = "...I've found nothing!!\nWhat a big waste of time! ALL THANKS TO YOU!!";

        ui.addFormattedToBuffer(responseAcknowledge);

        int found = 0; // number of tasks matched
        for (Task task: tasks) {
            if (task.hasMatchingDescription(keyword)) {
                found++;
                ui.addFormattedToBuffer(responseTaskFoundFormat, found, task.toString());
            }
        }

        // no tasks found
        if (found == 0) {
            ui.addToBuffer(responseNoTaskFound);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
