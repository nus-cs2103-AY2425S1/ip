package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents the command called when the user adds a tag to a task.
 */
public class TagCommand extends Command {

    private int position;
    private String tag;

    /**
     * Constructor for the TagCommand object.
     *
     * @param position
     * @param tag
     */
    public TagCommand(int position, String tag) {
        this.position = position;
        this.tag = tag;
    }

    /**
     * Returns the command to tag the task at the position specified by the user.
     * Throws a KieTwoForOne exception when there is no task at the specified position.
     * Throws a KieTwoForOne exception when the file being saved to does not exist.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws KieTwoForOneException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        assert tasks != null: "TaskList cannot be null.";
        assert ui != null: "UI cannot be null.";
        assert storage != null: "Storage cannot be null.";
        String taggedTask = tasks.addTag(this.position, this.tag);
        ui.showTaggedTask(taggedTask);
        try {
            storage.saveFile(tasks.getTaskList());
        } catch (KieTwoForOneException e) {
            throw new KieTwoForOneException(e.getMessage());
        }
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
}
