import java.util.ArrayList;

public class Ui {
    public void printLogo() {
        String logo = "    o      o    \n"
                + " ____\\____/____\n"
                + "|   _      _   |\n"
                + "|  / \\    / \\  |   /\n"
                + "|  \\_/    \\_/  |  /\n"
                + "|              | /\n"
                + "|______________|/\n"
                + "\n";
        System.out.println(logo);
    }
    public void printGreet() {
        String greet = "Hello! I'm Cookie\n"
                + "How can I help you?\n"
                + "Here are some commands you can use:\n"
                + "todo, deadline, event, mark, unmark, delete, list";
        System.out.println(greet);
    }
    public void printQuit() {
        String bye = "Bye. See you soon!";
        System.out.println(bye);
    }

    public void printNoTasksInList(ArrayList<Task> taskArrayList) {
        if (taskArrayList.size() == 1) {
            System.out.println("Now you have " + taskArrayList.size() + " task in the list.\n");
        } else {
            System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.\n");
        }
    }

    public void printLatestTask(Task task) {
        System.out.println("Got it. Cookie has added this task:\n  "
                + task.toString());
    }

    public void printDeleteTask(Task task) {
        String delete = "Cookie has removed the following task from your list:\n" +
                task.toString();
        System.out.println(delete);
    }
    public void printMarkTask(Task task) {
        String mark = "Cookie has marked this as done! Good job! \n" +
                task.toString();
        System.out.println(mark);
    }

    public void printUnmarkTask(Task task) {
        String unmark = "Cookie has unmarked this task! \n" +
                task.toString();
        System.out.println(unmark);
    }

}
