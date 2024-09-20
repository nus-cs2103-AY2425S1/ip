package kafka;

public class Ui {

    public static final String LOGO = """
                   __  __            __     _
                  |  |/  /  ____   _/  /_  | |      ____
                  |     /  / _  | |_    _| | |/ /  / _  |
                  |     \\ | |_| |   |  |   |   <  | |_| |
                  |__|\\__\\ \\____|   |__|   |_|\\ \\  \\____|
                """;

    public static String greet() {
        return "  Hello from\n" + LOGO + "\n" + """
              Hello. Kafka here.
              We meet again.
            """ + "\n" + "  What do you need me for?";
    }

    public String goodbye() {
        return "  Farewell. I look forward to our next meeting, wherever destiny may lead us.";
    }

    public String addTask(Task task, TaskList taskList) {
        return "  Got it. I've added this task.\n"
                + "    " + task + "\n"
                + "  Now you have " + taskList.getSize() + " task(s) in the list.";
    }

    public String getList() {
        return "  Here are the tasks in your list:";
    }

    public String find() {
        return "  Here you go, these are the matching tasks in your list:";
    }

    public String mark(Task t) {
        return "  Good work on this task. Want a prize?:\n"
                + "    " + t;
    }

    public String unmark(Task t) {
        return "  Hurry up. This task is necessary for Elio's script:\n"
                + "    " + t;
    }

    public String delete(Task t, TaskList taskList) {
        return "  I've removed this task:\n"
                + "    " + t + "\n"
                + "  Now you have " + taskList.getSize() + " task(s) in the list.";
    }

    public String incorrectDateDetails() {
        return "  Ah, it seems you have made a mistake with the date. Please align your date with this format: yyyy-mm-dd hhmm (2024-01-01 2300)";
    }

    public String showError(Exception e) {
        return "  " + e.getMessage();
    }
}
