package seedu.maxine;

import seedu.maxine.task.Task;

import java.util.ArrayList;

public interface MaxineStorage {
    public ArrayList<Task> load();
    public void refreshStorage(MaxineList list);
}
