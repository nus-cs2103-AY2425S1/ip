package kafka;

public class Ui {

    public final String logo = """
                   __  __            __     _
                  |  |/  /  ____   _/  /_  | |      ____
                  |     /  / _  | |_    _| | |/ /  / _  |
                  |     \\ | |_| |   |  |   |   <  | |_| |
                  |__|\\__\\ \\____|   |__|   |_|\\ \\  \\____|
                """;

    public void greet() {
        System.out.println("  Hello from\n" + logo);
        System.out.println("""
              Hello. Kafka here.
              We meet again.
            """);
        System.out.println("  What do you need me for?");
    }

    public void goodbye() {
        System.out.println("  Farewell. I look forward to our next meeting, wherever destiny may lead us.");
    }

    public void addTask(Task task, TaskList taskList) {
        System.out.println("  Got it. I've added this task.");
        System.out.println("    " + task);
        System.out.println("  Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    public void getList() {
        System.out.println("  Here are the tasks in your list:");
    }

    public void find() {
        System.out.println("  Here you go, these are the matching tasks in your list:");
    }

    public void mark(Task t) {
        String message = "  Good work on this task. Want a prize?:\n"
                + "    " + t;
        System.out.println(message);
    }

    public void unmark(Task t) {
        String message = "  Hurry up. This task is necessary for Elio's script:\n"
                + "    " + t;
        System.out.println(message);
    }

    public void delete(Task t, TaskList taskList) {
        String message = "  I've removed this task:\n"
                + "    " + t;
        System.out.println(message);
        System.out.println("  Now you have " + taskList.getSize() + " task(s) in the list.");
    }

    public void incorrectDateDetails() {
        System.out.println("  Ah, it seems you have made a mistake with the date. Please align your date with this format: yyyy-mm-dd hhmm (2024-01-01 2300)");
    }

    public void showError(Exception e) {
        System.out.println("  " + e.getMessage());
    }
}
