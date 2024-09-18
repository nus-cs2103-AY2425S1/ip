package seedu.maxine.ui;

import java.util.ArrayList;

import seedu.maxine.task.MaxineList;
import seedu.maxine.task.Task;

/**
 * The MaxineUi interface defines the methods for user interface interactions
 * in the Maxine application. It provides methods for greeting the user,
 * displaying messages related to tasks, and handling errors.
 */
public interface MaxineUi {
    String greet();
    String goodbye();
    String delete(Task task);
    String mark(Task task);
    String unmark(Task task);
    String showList(MaxineList list);
    String showError(String e);
    String search(ArrayList<Task> search);
    String deleteAll();
}
