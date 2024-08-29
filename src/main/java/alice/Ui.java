package alice;

public class Ui {
    private static final String name = "Alice";
    private static final String line =
            "____________________________________________________________";

    public void listTasks(TaskList list) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        System.out.println(list.toString());
        System.out.println(line);
    }

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void todoMsg() {
        System.out.println(line);
        System.out.println("Command Format: todo [description]");
        System.out.println(line);
    }

    public void eventMsg() {
        System.out.println(line);
        System.out.println("Command Format: event [description] /from [dd-MM-yyyy HHmm] /to [dd-MM-yyyy HHmm]");
        System.out.println(line);
    }

    public void deadlineMsg() {
        System.out.println(line);
        System.out.println("Command Format: deadline [description] /by [dd-MM-yyyy HHmm]");
        System.out.println(line);
    }

    public void invalidMsg() {
        System.out.println(line);
        System.out.println("Invalid command, use command words: list, todo, deadline, event, mark, unmark & delete");
        System.out.println(line);
    }

    public void addToListMsg(Task task, int size) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    public void addFailMsg() {
        System.out.println("Fail to add the task: wrong format");
    }

    public void markMsg(Task task) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(line);
    }

    public void invalidNumMsg() {
        System.out.println(line);
        System.out.println("Invalid number");
        System.out.println(line);
    }

    public void unMarkMsg(Task task) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(line);
    }

    public void deleteMsg(Task task, int size) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    public void findMsg(TaskList list) {
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(list.toString());
        System.out.println(line);
    }

    public void noFindMsg() {
        System.out.println(line);
        System.out.println("No matching tasks in your list");
        System.out.println(line);
    }
}
