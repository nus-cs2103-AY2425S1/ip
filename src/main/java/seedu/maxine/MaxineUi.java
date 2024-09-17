package seedu.maxine;

import seedu.maxine.task.Task;

import java.util.ArrayList;

public interface MaxineUi {
    public String greet();
    public String goodbye();
    public String delete(Task task);
    public String mark(Task task);
    public String unmark(Task task);
    public String showList(MaxineList list);
    public String showLoadingError();
    public String showError(String e);
    public String search(ArrayList<Task> search);
    public String deleteAll();
}
