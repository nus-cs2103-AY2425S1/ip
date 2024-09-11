package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents the command called when the user filters the task list by word.
 */
public class FindWordCommand extends Command {

    private String keyword;

    /**
     * Constructor for a new FindWordCommand object.
     *
     * @param keyword
     */
    public FindWordCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to filter the task list by the keyword.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMatchingTask(tasks.getTaskList(), this.keyword);
    }

    /**
     * Returns a boolean to determine whether to close the chatbot.
     * True if the command closes the chatbot and false otherwise.
     *
     * @return Boolean to determine whether to close the chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Matching tasks listed";
    }

}
