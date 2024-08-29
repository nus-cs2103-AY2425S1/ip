package wenjigglybot;

public class Ui {

    /**
     * Displays an introduction message when the application starts.
     */
    public void intro() {
        String name = "WenJigglyBot";
        System.out.println("Sup im " + name);
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a separator line to visually divide different sections of output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task is being removed, along with the task details.
     *
     * @param tasks The {@link TaskList} containing the task.
     * @param idx   The index of the task to be removed.
     */
    public void showDeleteTask(TaskList tasks, int idx) {
        System.out.println("\tRemoving this task!");
        System.out.println("\t\t" + tasks.get(idx));
    }

    /**
     * Displays the current number of tasks in the task list.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     */
    public void showTaskCount(TaskList tasks) {
        System.out.println("You now have" + " " + tasks.size() + " tasks!");
    }

    /**
     * Displays a message indicating that a task has been completed, along with the task details.
     *
     * @param task The {@link Task} that was completed.
     */
    public void showCompletedTask(Task task) {
        System.out.println("\tYay! wenjigglybot.Task Completed!");
        System.out.println("\t" + task);
    }

    /**
     * Displays a message indicating that a task has not been completed, along with the task details.
     *
     * @param task The {@link Task} that is not yet completed.
     */
    public void showUncompletedTask(Task task) {
        System.out.println("\tGet to work boy, why not done!!!");
        System.out.println("\t" + task);
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The {@link Task} that was added.
     */
    public void showAddedTask(Task task) {
        System.out.printf("\tAdding %s\n", task.taskType());
        System.out.printf("\tDone! Added: %s\n", task.getDescription());
    }

    /**
     * Displays all tasks currently in the task list.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     */
    public void displayTasks(TaskList tasks) {
        showLine();
        System.out.println("Here are your tasks :)");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s%n", i + 1, tasks.get(i).toString());
        }
        showLine();
    }
}