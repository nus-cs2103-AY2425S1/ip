public class Ui {
    public void showWelcomeMessage() {
        String logo = "  ____      _ _\n"
                + " / ___|___ | | |__  _   _\n"
                + "| |   / _ \\| | '_ \\| | | |\n"
                + "| |__| (_) | | |_) | |_| |\n"
                + "\\____\\___/|_|_.__/ \\__, /\n"
                + "                   |___/\n";
        System.out.println("Hello! I'm\n" + logo + "\n" + "What can I do for you?\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("  Bye bye! Hope to see you again soon! :)");
        System.out.println("_________________________________________________");
    }
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Alright, I have added this task to the list:\n"
                + "    " + task + "\n"
                + "  Your list now has " + taskCount + (taskCount == 1 ? " task" : " tasks") + " :)");
    }

    public void showTaskList(TaskList taskList) {
        System.out.println("Here's all the tasks you have to do:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + taskList.getTask(i).toString());
        }
    }

    public void showTaskMarked(Task task) {
        System.out.println("Great job! I have now marked this task as done!\n"
                + "    [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Alright, I have marked this task as not done yet.\n"
                + "    [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public void showTaskDeleted(Task task) {
        System.out.println("Okay, I have removed this task from your list:\n" +
                "    " + task.toString());
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty task list.");
    }

    public void showUnknownCommandMessage() {
        System.out.println("Sorry!! I'm not sure how to add that to the list for you, try specifying the type of task!");
    }
}
