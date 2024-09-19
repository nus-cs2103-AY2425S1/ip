package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

public class TagCommand extends Command {

    private int position;
    private String tag;

    public TagCommand(int position, String tag) {
        this.position = position;
        this.tag = tag;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
