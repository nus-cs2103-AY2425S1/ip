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
            System.out.println(counter + ". " + t.getName());
            counter++;
        }
    }

    private void line() {
        System.out.println("========================================");
    }

}
