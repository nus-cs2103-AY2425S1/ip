class Ui {
    public void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("Hello! I'm MrIncredible");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public void sayBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public void handleError(String errorMessage) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    OOPS!!! " + errorMessage);
        System.out.println("    ____________________________________________________________");
    }

    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void showInvalidTaskIdError() {
        System.out.println("    Invalid task ID.");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void showDateTimeParseError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Error: Invalid date and time format. Please use 'yyyy-MM-dd HH:mm'.");
        System.out.println("    ____________________________________________________________");
    }

    public void showTaskMarked(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
        System.out.println("    ____________________________________________________________");
    }

    public void showTaskList(TaskStorage taskStorage) {
        System.out.println("    ____________________________________________________________");
        taskStorage.listTasks();
        System.out.println("    ____________________________________________________________");
    }
}