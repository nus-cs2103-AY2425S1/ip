package elster;

import java.util.ArrayList;
import java.util.List;

import elster.tasks.Task;

/**
 * TaskList component, handles logic related to the entire list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the task list.
     */
    public void addToList(Task task) {
        list.add(task);
    }

    /**
     * Returns task at the index specified in the parameter.
     *
     * @param index Index of the task to be returned.
     * @return Task at the index of the task list.
     * @throws Elseption If an index that is out of bounds of the task list is given.
     */
    public Task getTask(int index) throws Elseption {
        Task task;

        try {
            task = list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new Elseption("Ain't no such task in the middle of these woods");
        }

        return task;
    }

    /**
     * Returns size of the task list
     */
    public Integer getSize() {
        return list.size();
    }

    /**
     * Returns a boolean that represents whether the task list is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param index Index of task to be marked as done.
     * @return Boolean representing success or failure in marking.
     * @throws Elseption If getTask throws an Elseption.
     */
    public boolean markTaskAsDone(int index) throws Elseption {
        Task task = getTask(index);

        boolean isMarkedSuccessfully = task.markAsDone();

        if (isMarkedSuccessfully) {
            return true;

        } else {
            throw new Elseption("So uh, the task is already done");
        }
    }

    /**
     * Unmarks a task in the task list as undone.
     *
     * @param index Index of task to be unmarked as undone.
     * @return Boolean representing success or failure in unmarking.
     * @throws Elseption If getTask throws an Elseption.
     */
    public boolean unmarkTaskAsUndone(int index) throws Elseption {
        Task task = getTask(index);

        boolean isUnmarkedSuccessfully = task.unmarkAsUndone();

        if (isUnmarkedSuccessfully) {
            return true;

        } else {
            throw new Elseption("So uh, the task already is not done");
        }
    }


    /**
     * Deletes a task from the task list.
     *
     * @param index Index of task to be deleted.
     * @return Boolean representing success or failure in deletion.
     * @throws Elseption If the index is out of bounds of the task list.
     */

    public Task deleteTask(int index) throws Elseption {
        Task task;

        try {
            task = list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new Elseption("Ain't no such task in the middle of these woods");
        }
        assert !list.contains(task) : "Bug when removing task";
        return task;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    /**
     * Returns a string representation of the task list for the user to view.
     */
    public String printString() {
        StringBuilder returnStr = new StringBuilder();

        if (list.isEmpty()) {
            returnStr.append("    No tasks to do? that's pretty goooood.");

        } else {
            assert !list.isEmpty() : "Bug when printing task list as string";
            returnStr.append("    So here's the tasks in your list, you should proooobably do them\n");
            for (int i = 0; i < list.size(); i++) {
                returnStr.append("    ").append(i + 1).append(". ").append(list.get(i)).append("\n");
            }
        }

        return returnStr.toString().stripTrailing();
    }

    /**
     * Returns a string representation of the task list formatted for writing to the save file.
     */
    public String fileString() {
        StringBuilder returnStr = new StringBuilder();

        for (Task task : list) {
            returnStr.append(task.toFileString()).append(System.lineSeparator());
        }

        return returnStr.toString();
    }

    /**
     * Finds all tasks in the list that has a description that matches the user input.
     *
     * @param input User input to match description with.
     * @return List of tasks that have a description matching user input.
     */
    public List<Task> findByDescription(String input) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task i : list) {
            if (i.descContains(input)) {
                foundTasks.add(i);
            }
        }
        return foundTasks;
    }

    /**
     * Tags a task in the task list.
     *
     * @param index Index of task to be tagged.
     * @throws Elseption If Task::tag throws an Elseption.
     */
    public void tagTask(int index, String tag) throws Elseption {
        Task task = getTask(index);
        task.tag(tag);
    }
}
