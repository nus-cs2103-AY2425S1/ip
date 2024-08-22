import java.util.ArrayList;

public class Response {
    public void greet() {
        line();
        System.out.println("Hello! I'm Tanjiro!");
        System.out.println("What can I do for you?");
        line();
    }
    public void goodbye() {
        System.out.println("Bye! Hope to see you again!");
        line();
    }

    public void added_task_message(String s) {
        System.out.println("added: " + s);
    }

    public void list_task_message(ArrayList<Task> task_list) {
        int counter = 1;
        for (Task t : task_list) {
            if (t.getStatus()) {
                System.out.println(counter + ". [X] " + t.getName());
            } else {
                System.out.println(counter + ". [ ] " + t.getName());
            }
            counter++;
        }
    }

    public void mark_message(String s) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + s);
    }

    public void unmark_message(String s) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + s);
    }
    private void line() {
        System.out.println("========================================");
    }

}
