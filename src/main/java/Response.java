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

//    public void added_task_message(String s) {
//        System.out.println("added: " + s);
//    }

    public void list_task_message(ArrayList<Task> task_list) {
        int counter = 1;
        for (Task t : task_list) {
            if (t.getStatus()) {
                if (t.getTag().equals("T")) {
                    System.out.println(counter + ".[T][X] " + t.getName());
                } else if (t.getTag().equals("D")) {
                    System.out.println(counter + ".[D][X] " + t.getName());
                } else if (t.getTag().equals("E")) {
                    System.out.println(counter + ".[E][X] " + t.getName());
                } else {
                    System.out.println(counter + ".[-][X] " + t.getName());
                }
            } else {
                if (t.getTag().equals("T")) {
                    System.out.println(counter + ".[T][ ] " + t.getName());
                } else if (t.getTag().equals("D")) {
                    System.out.println(counter + ".[D][ ] " + t.getName());
                } else if (t.getTag().equals("E")) {
                    System.out.println(counter + ".[E][ ] " + t.getName());
                } else {
                    System.out.println(counter + ".[-][ ] " + t.getName());
                }
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

    public void empty_todo(){
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    public void empty_deadline(){
        System.out.println("OOPS!!! The format of a deadline is wrong.");
    }

    public void empty_event(){
        System.out.println("OOPS!!! The format of a event is wrong.");
    }

    public void invalid_input(){
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
