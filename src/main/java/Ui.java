import java.util.Iterator;

public class Ui {

    static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void showWelcome() {
        PrintUtility.wrapPrintWithHorizontalLines("Hello! I'm Nixy", "What can I do for you?");
    }

    public static void showGoodbye() {
        PrintUtility.wrapPrintWithHorizontalLines("Bye. Hope to see you again soon!");
    }

    public static void showList(TaskList tasks) {
        PrintUtility.indentPrint(HORIZONTAL_LINE);
        PrintUtility.indentPrint("Here are the tasks in your list:");
        Iterator<Task> tasksIterator = tasks.getTasksIterator();
        for (int i = 0; tasksIterator.hasNext(); i++) {
            Task task = tasksIterator.next();
            PrintUtility.indentPrint(String.format("%d. %s", i + 1, task));
        }
        PrintUtility.indentPrint(HORIZONTAL_LINE);
    }

    public static void showMarkedAsDone(String taskString) {
        PrintUtility.wrapPrintWithHorizontalLines("Nice! I've marked this task as done:",
            "  " + task);
    }

    public static void showMarkedAsUndone(String taskString) {
        PrintUtility.wrapPrintWithHorizontalLines("OK, I've marked this task as not done yet:",
            "  " + task);
    }

    public static void showDeletedTask(String taskString, int taskCount) {
        PrintUtility.wrapPrintWithHorizontalLines("Noted. I've removed this task:",
            "  " + task, String.format("Now you have %d tasks in the list.", taskCount));
    }

    public static void showNixyException(NixyException e) {
        PrintUtility.wrapPrintWithHorizontalLines(e.getMessage());
    }

    public static void showAddedTask(Task task, int taskCount) {
        PrintUtility.wrapPrintWithHorizontalLines("Got it. I've added this task:",
            "  " + task, String.format("Now you have %d tasks in the list.", taskCount));
    }

    public static void showUnknownWarning() {
        PrintUtility.wrapPrintWithHorizontalLines("HEY YOU ARE TYPING WEIRD THINGS! I don't understand.");
    }


}
