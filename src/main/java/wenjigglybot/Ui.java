package wenjigglybot;

public class Ui {

    /**
     * Displays an introduction message when the application starts.
     */
    public String intro() {
        String name = "WenJigglyBot";
        System.out.println("Sup im " + name);
        System.out.println("What can I do for you?");
        return String.format("Sup im %s! What can I do for you?", name);
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
    public String showDeleteTask(TaskList tasks, int idx) {
        System.out.println("\tRemoving this task!");
        System.out.println("\t\t" + tasks.get(idx));
        return String.format("Removing this task!\n%s", tasks.get(idx));
    }

    /**
     * Displays the current number of tasks in the task list.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     */
    public String showTaskCount(TaskList tasks) {
        String output = String.format("You now have" + " " + tasks.size() + " tasks!");
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that a task has been completed, along with the task details.
     *
     * @param task The {@link Task} that was completed.
     */
    public String showCompletedTask(Task task) {
        System.out.println("\tYay! Task Completed!");
        System.out.println("\t" + task);
        return String.format("Yay! Task completed!\n%s", task);
    }

    /**
     * Displays a message indicating that a task has not been completed, along with the task details.
     *
     * @param task The {@link Task} that is not yet completed.
     */
    public String showUncompletedTask(Task task) {
        System.out.println("\tGet to work boy, why not done!!!");
        System.out.println("\t" + task);
        return String.format("Get to work boy, why not done!!!\n%s", task);
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The {@link Task} that was added.
     */
    public String showAddedTask(Task task) {
        System.out.printf("\tAdding %s\n", task.taskType());
        System.out.printf("\tDone! Added: %s\n", task.getDescription());
        return String.format("Adding %s\nDone! Added: %s\n", task.taskType(), task.getDescription());
    }

    /**
     * Displays all tasks currently in the task list.
     *
     * @param tasks The {@link TaskList} containing the tasks.
     */
    public String displayTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return "No tasks added! You lazy pig...";
        }
        showLine();
        System.out.println("Here are your tasks :)");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s%n", i + 1, tasks.get(i).toString());
            output.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        showLine();
        return output.toString();
    }
}