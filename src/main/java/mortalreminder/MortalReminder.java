package mortalreminder;

import mortalreminder.backend.Processor;
import mortalreminder.backend.Storage;
import mortalreminder.backend.TaskList;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.io.FormattedPrinting;

/**
 * Main class of the MortalReminder App.
 */
public class MortalReminder {

    private final Processor processor = new Processor();

    private TaskList taskList = new TaskList();

    /**
     * This method loads all tasks from storage and greets the user.
     *
     * @return string of welcome message with initial greeting to the user.
     */
    public String welcome() {
        try {
            this.taskList = Storage.loadTaskListFromFile();
        } catch (MortalReminderException e) {
            return e.getMessage();
        }
        return FormattedPrinting.welcome();
    }

    public Processor getProcessor() {
        return this.processor;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

}
