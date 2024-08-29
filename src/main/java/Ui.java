public class Ui {
    public static void showWelcome() {
        System.out.println("Hello! I'm Velma\nWhat can I do for you?");
    }

    public static void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showLoadingError() {
        System.out.println("Error loading file");
    }

//    public static void showLoadingSuccess() {
//        System.out.println("File loaded successfully");
//    }

    public static void showSavingError() {
        System.out.println("Error saving file");
    }

    public static void showSavingSuccess() {
        System.out.println("File saved successfully");
    }

    public static void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public static void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void showMatchingTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void showTaskNotFound() {
        System.out.println("Task not found");
    }

    public static void showTaskAlreadyDone() {
        System.out.println("Task is already done");
    }

    public static void showTaskAlreadyUndone() {
        System.out.println("Task is already undone");
    }

}
