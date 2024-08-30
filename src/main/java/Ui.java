public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Gale, your friendly windy assistant.");
        System.out.println("I'll keep your deadlines, to-do's and events in my memory. What do you have to do?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Aw, it's time for you to go huh?");
        System.out.println("Catch you on the next gust!");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAddedTask(Task task, int taskCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Whoosh! Task \"" + task + "\" added to my windy memory.");
        System.out.println("Now you have " + taskCount + " task" + (taskCount == 1 ? "" : "s")
            + " in the air.");
        System.out.println("Anything else?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showDeletedTask(Task task, int taskCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Poof! The wind has blown away this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " task" + (taskCount == 1 ? "" : "s")
            + " in your windy list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void displayTaskList(TaskList taskList) {
        System.out.println(HORIZONTAL_LINE);
        if (taskList.isEmpty()) {
            System.out.println("No tasks breezing around now!");
        } else {
            System.out.println("You are breezy with these tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.getTask(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void showMarkedTask(Task task, boolean isDone) {
        System.out.println(HORIZONTAL_LINE);
        if (isDone) {
            System.out.println("Good work! You breezed through this task!");
        } else {
            System.out.println("Tough, this task is back in the windy realm.");
        }
        System.out.println(" " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showException(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showLoadingError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oops! The wind blew away your tasks. Starting with a clean slate.");
        System.out.println(HORIZONTAL_LINE);
    }
}
