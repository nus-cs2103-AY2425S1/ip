package elster;

import java.util.ArrayList;

import elster.tasks.Task;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    protected static void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

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
            throw new Elseption();
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

        return task.markAsDone();
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

        return task.unmarkAsUndone();
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
            throw new Elseption();
        }

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
}
