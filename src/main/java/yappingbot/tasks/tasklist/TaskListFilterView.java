package yappingbot.tasks.tasklist;

import java.util.ArrayList;

import yappingbot.exceptions.YappingBotOobException;
import yappingbot.tasks.Task;

/**
 * Special TaskList with a parent TaskList that gives a filtered view.
 * Any changes to this filter will change the parent filter but from perspective of this filter.
 * (ie deleting item 3 of this filter will delete both item 3, and the same item in parent list)
 */
public class TaskListFilterView extends TaskList {
    final TaskList parentTaskList;
    final ArrayList<Integer> indexInParentTaskList;
    final String filterString;

    private TaskListFilterView(TaskList parentTaskList, String filterString) {
        this.parentTaskList = parentTaskList;
        this.filterString = filterString;
        indexInParentTaskList = new ArrayList<>();
    }

    /**
     * Get the parent TaskList.
     *
     * @return TaskList parent of this filter.
     */
    public TaskList getParent() {
        return parentTaskList;
    }

    /**
     * Gets the String used to create this filter.
     *
     * @return String used to create this filter.
     */
    public String getFilterString() {
        if (parentTaskList instanceof TaskListFilterView) {
            return ((TaskListFilterView) parentTaskList).getFilterString()
                   + " AND "
                   + this.filterString;
        } else {
            return this.filterString;
        }
    }

    /**
     * Filters the Tasks found in parent TaskList to form a new TaskListFilterView.
     *
     * @param searchString String to be searched in each Task.
     * @return TaskListFilterView with the new constraints, allowing manipulation of the parent
     *         Tasklist from the perspective of this filter
     */
    public static TaskListFilterView createFilter(TaskList parent, String searchString) {
        TaskListFilterView newFilter = new TaskListFilterView(parent, searchString);
        for (int index = 0; index < parent.size(); index++) { // dont ask me why index not i
            Task t = parent.get(index);
            if (t.isStringFoundInTask(searchString)) {
                newFilter.tasks.add(t);
                newFilter.indexInParentTaskList.add(index);
                newFilter.size += 1;
            }
        }
        return newFilter;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        parentTaskList.addTask(task);
        indexInParentTaskList.add(parentTaskList.size());
        size += 1;
    }

    @Override
    public Task deleteTask(int index) throws YappingBotOobException {
        tasks.remove(index);
        size -= 1;
        Task t = parentTaskList.deleteTask(indexInParentTaskList.get(index));
        indexInParentTaskList.remove(index);
        return t;
    }
}
