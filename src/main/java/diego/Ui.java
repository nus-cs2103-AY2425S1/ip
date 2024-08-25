package diego;
public class Ui {

    public void showWelcomeMessage() {
        System.out.println("""
            ____________________________________________________________
            Hello! I'm Diego
            What can I do for you?
            ____________________________________________________________
            """);
    }

    public void showGoodbyeMessage() {
        System.out.println("""
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________
            """);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void showTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Ok! I've marked this task as not done:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskCount);
        System.out.println("____________________________________________________________");
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskCount);
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Something went wrong while loading tasks.");
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks The task list containing the matching tasks.
     */
    public void showFoundTasks(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
