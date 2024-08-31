package yappingbot.tasks.tasklist;

import java.util.ArrayList;

import yappingbot.tasks.Task;

/**
 * Special TaskList with a parent TaskList that gives a filtered view.
 * Any changes to this filter will change the parent filter but from perspective of this filter.
 * (ie deleting item 3 of this filter will delete both item 3, and the same item in parent list)
 */
public class TaskListFilterView extends TaskList {
    final TaskList parentTaskList;
    ArrayList<Boolean> areTaskIndicesInFilter;

    private TaskListFilterView(TaskList parentTaskList) {
        this.parentTaskList = parentTaskList;
        areTaskIndicesInFilter = new ArrayList<>(parentTaskList.size());
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
     * Filters the Tasks found in parent TaskList to form a new TaskListFilterView.
     *
     * @param searchString String to be searched in each Task.
     * @return TaskListFilterView with the new constraints, allowing manipulation of the parent
     *         Tasklist from the perspective of this filter
     */
    public static TaskListFilterView createFilter(TaskList parent, String searchString) {
        TaskListFilterView newFilter = new TaskListFilterView(parent);
        int index = 0;
        for (Task t : newFilter.getParent()) {
            if (t.isStringFoundInTask(searchString)) {
                newFilter.add(t);
                newFilter.areTaskIndicesInFilter.set(index, true);
            } else {
                newFilter.areTaskIndicesInFilter.set(index, false);
            }
        }
        return newFilter;
    }
}
