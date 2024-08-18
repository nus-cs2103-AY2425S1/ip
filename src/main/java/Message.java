public class Message {
    /**
     * Print a string in the dialog box.
     * @param text The content of the dialog box.
     */
    public static void print(String text) {
        printDialogBox(text);
    }

    /**
     * Print the message after adding a task.
     * @param task The task that was added.
     * @param todoList The todoList the task was added to.
     */
    public static void printAddedTask(Task task, TodoList todoList) {
        String content = "Got it. I've added this task:\n"
                + "  "
                + task + "\n"
                + todoList.count();
        printDialogBox(content);
    }

    /**
     * Print the message after marking a task.
     * @param task The task to be marked.
     */
    public static void printMarked(Task task) {
        String content = "Nice! I've marked this task as done:\n  " + task;
        printDialogBox(content);
    }

    /**
     * Print the message after unmarking a task.
     * @param task The task to be unmarked.
     */
    public static void printUnmarked(Task task) {
        String content = "OK, I've marked this task as not done yet:\n  "  + task;
        printDialogBox(content);
    }

    /**
     * Print the content and dialog box.
     * @param content The content to be printed in the dialog box.
     */
    private static void printDialogBox(String content) {
        String line = "____________________________________________________________";
        System.out.println(line
                + "\n"
                + content + "-Mooooo"
                + "\n"
                + line
                + "\n");
    }
}
