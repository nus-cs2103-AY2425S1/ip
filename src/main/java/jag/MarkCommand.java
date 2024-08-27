package jag;

import java.io.IOException;

/**
 * Represents and instance of a MarkCommand and it includes
 * methods to mark and unmark a task
 */
public class MarkCommand extends Command {
    Boolean isMark;

    /**
     * Custom constructor for this class
     *
     * @param isMark will be set as true if the task is to be marked
     *               and false if to be unmarked
     */
    public MarkCommand(Boolean isMark) {
        this.isMark = isMark;
    }

    /**
     * This method checks the attribute isMark to determine if
     * the task is to be marked or not, it also works with the Ui
     * instance to break down the command and get the required information
     * to be stored and replied.
     *
     * @param tasks TaskList instance to access the specific task, to setStatus
     *              accordingly
     * @param ui Ui instance to break down the command to get the index of the
     *           task to be marked / unmarked and to reply accordingly
     * @param storage Storage instance to save the updated task accordingly
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isMark) {
            // Mark jag.Command
            int index = ui.getMark();
            Task task = tasks.getTask(index-1);
            task.setStatus(true);

            // jag.Ui response
            ui.markResponse(task);

            // jag.Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }
        } else {
            // Unmark jag.Command
            int index = ui.getMark();
            Task task = tasks.getTask(index-1);
            task.setStatus(false);

            // jag.Ui response
            ui.unmarkResponse(task);

            // jag.Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }
        }
    }

    /**
     * Returns false so that Jag.java does not exit for loop
     *
     * @return a default false so the run() in Jag.java does not exit
     * the while loop
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
