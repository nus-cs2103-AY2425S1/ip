package darkpool.gui;

import darkpool.tasklist.TaskList;

/**
 * Represents the user interface of the application.
 */
public class Gui {

    final String bye = "contact me again and you'll regret it.";

    public String goodbye() {
        return bye;
    }

    public String list(TaskList taskList) {
        return taskList.toString();
    }

    public String mark(String task) {
        return "why do i have to mark this mess\n" + task;
    }

    public String unmark(String task) {
        return "why do i have to unmark this mess\n" + task;
    }

    public String delete(String task) {
        return "why do i have to delete this mess\n" + task;
    }

    /**
     * Returns a message to indicate that a task has been added to the list.
     *
     * @param task The task that has been added to the list.
     * @param size The number of tasks in the list.
     * @return A message to indicate that a task has been added to the list.
     */
    public String add(String task, int size) {
        return "i have dumped this nonsense on the list\n" + task + "\nnow you are stuck with " + size + " goddamn tasks";
    }

}