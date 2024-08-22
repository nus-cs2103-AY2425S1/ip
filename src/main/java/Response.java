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
    public void delete_message(Task t) {
        System.out.println("Noted. I've removed this task:");
        if (t instanceof ToDos) {
            if (t.getStatus()) {
                System.out.println("[T][X] " + t.getName());
            } else {
                System.out.println("[T][ ] " + t.getName());
            }
        } else if (t instanceof Deadlines) {
            if (t.getStatus()) {
                System.out.println("[D][X] " + t.getName() + "(by: " + t.getDay() + ")");
            } else {
                System.out.println("[D][ ] " + t.getName() + "(by: " + t.getDay() + ")");
            }
        } else if (t instanceof Events) {
            if (t.getStatus()) {
                System.out.println("[E][X] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
            } else {
                System.out.println("[E][ ] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
            }
        }
        System.out.println("Now you have " + t.get_list_size() + " tasks in the list.");
    }

    public void list_task_message(ArrayList<Task> task_list) {
        int counter = 1;
        for (Task t : task_list) {
            if (t.getStatus()) {
                if (t.getTag().equals("T")) {
                    if (t instanceof ToDos) {
                        System.out.println(counter + ".[T][X] " + t.getName());
                    }
                } else if (t.getTag().equals("D")) {
                    if (t instanceof Deadlines) {
                        System.out.println(counter + ".[D][X] " + t.getName() + "(by: " + t.getDay() + ")");
                    }
                } else if (t.getTag().equals("E")) {
                    if (t instanceof Events) {
                        System.out.println(counter + ".[E][X] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
                    }
                } else {
                    System.out.println(counter + ".[-][X] " + t.getName());
                }
            } else {
                if (t.getTag().equals("T")) {
                    if (t instanceof ToDos) {
                        System.out.println(counter + ".[T][ ] " + t.getName());
                    }
                } else if (t.getTag().equals("D")) {
                    if (t instanceof Deadlines) {
                        System.out.println(counter + ".[D][ ] " + t.getName() + "(by: " + t.getDay() + ")");
                    }
                } else if (t.getTag().equals("E")) {
                    if (t instanceof Events) {
                        System.out.println(counter + ".[E][ ] " + t.getName() + "(from: " + t.getStart() + " to: " + t.getEnd() + ")");
                    }
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

    public void empty_todo() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    public void empty_deadline() {
        System.out.println("OOPS!!! The format of a deadline is wrong.");
    }

    public void empty_event() {
        System.out.println("OOPS!!! The format of a event is wrong.");
    }

    public void invalid_input() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
