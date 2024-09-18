package lebron;

/**
 * The Ui class is responsible for handling interactions with the user. It
 * provides methods to generate messages for various scenarios, such as when a
 * task is added, deleted, marked, unmarked, rescheduled, or when an error
 * occurs.
 */
public class Ui {

    /**
     * Returns an error message indicating that there was an error in loading
     * the chatbot.
     *
     * @return A string representing the loading error message.
     */
    public String showLoadingError() {
        return "There was an error in loading the chatbot";
    }

    /**
     * Returns a welcome message to greet the user when the chatbot starts.
     *
     * @return A string representing the welcome message.
     */
    public String showWelcomeMessage() {
        return "Wassup! I'm lebron";
    }

    /**
     * Returns a goodbye message when the user exits the chatbot.
     *
     * @return A string representing the goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye! I'm leaving now.";
    }

    /**
     * Returns a message indicating that a task has been added to the task list.
     * The message includes the details of the task added and the current number
     * of tasks in the list.
     *
     * @param task The task that has been added.
     * @param size The current number of tasks in the list.
     * @return A string representing the task added message.
     */
    public String showTaskAdded(Task task, int size) {
        String taskDetails = "Gotchu, added the task: \n" + task.toString();
        String taskCount = String.format("Now you have %d tasks in the list", size);
        return taskDetails + "\n" + taskCount;
    }

    /**
     * Returns a message indicating that a task has been deleted from the task
     * list. The message includes the details of the deleted task.
     *
     * @param task The task that has been deleted.
     * @return A string representing the task deleted message.
     */
    public String showTaskDeleted(Task task) {
        return "Alright bro, I've deleted that task.\n" + task.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as done. The
     * message includes the details of the marked task.
     *
     * @param task The task that has been marked as done.
     * @return A string representing the task marked message.
     */
    public String showTaskMarked(Task task) {
        return "Alright bro, I've marked that task\n" + task.toString();
    }

    /**
     * Returns a message indicating that a task has been unmarked (marked as not
     * done). The message includes the details of the unmarked task.
     *
     * @param task The task that has been unmarked.
     * @return A string representing the task unmarked message.
     */
    public String showTaskUnmarked(Task task) {
        return "Alright bro, I've unmarked that task\n" + task.toString();
    }

    /**
     * Returns a message indicating that a task has been rescheduled to a new
     * date. The message includes the details of the rescheduled task.
     *
     * @param task The task that has been rescheduled.
     * @return A string representing the task rescheduled message.
     */
    public String showTaskRescheduled(Task task) {
        return "Alright bro, I've rescheduled that task\n" + task.toString();
    }

    /**
     * Returns a string representing the list of tasks currently in the task
     * list. Each task is listed with its index and details.
     *
     * @param taskList The task list containing all tasks.
     * @return A string representing the list of tasks.
     * @throws LeBronException If an error occurs while retrieving tasks.
     */
    public String showTaskList(TaskList taskList) throws LeBronException {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            result.append(String.format("%d. %s\n", i, task.toString()));
        }
        return result.toString();
    }

    /**
     * Returns a string representing the list of tasks that match the given
     * keyword. Each matching task is listed with its index and details.
     *
     * @param taskList The task list containing all tasks.
     * @param keyword The keyword to search for in the task descriptions.
     * @return A string representing the list of matching tasks.
     * @throws LeBronException If an error occurs while retrieving tasks.
     */
    public String showMatchingTasks(TaskList taskList, String keyword) throws LeBronException {
        StringBuilder result = new StringBuilder();
        result.append("Here's what I've got bro\n");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                result.append(String.format("%d. %s\n", i, task.toString()));
            }
        }
        return result.toString();
    }

}
