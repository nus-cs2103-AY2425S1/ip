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
    /** The keyword {@link String} */
    private final String keyword;

    /**
     * Creates a new {@link FindCommand}.
     * @param keyword The {@link String} to search for in each {@link Task}.
     */
    public FindCommand(String keyword) throws EkudException {
        if (keyword == null || keyword.isEmpty()) {
            throw new EkudException("If you are going to waste my time, could you at least "
                    + "not try to get me to search for nothing!!");
        }
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        super.execute(tasks, ui, storage);

        ui.addToBuffer("""
                Urrgh! Why do you bother me with this task!
                Fine, fine.... I will go look for them...
                Here are the results of my search:""");

        int found = 0; // number of tasks matched
        for (Task task: tasks) {
            if (task.hasMatchingDescription(keyword)) {
                found++;
                String foundTask = String.format("%d. %s", found, task);
                ui.addToBuffer(foundTask);
            }
        }

        // no tasks found
        if (found == 0) {
            ui.addToBuffer("...I've found nothing!!\nWhat a big waste of time! ALL THANKS TO YOU!!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
