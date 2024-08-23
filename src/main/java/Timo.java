import java.util.Scanner;
import java.util.*;

class Task {
    private boolean mark;
    private String val;
    public Task(boolean mark, String val) {
        this.mark = mark;
        this.val = val;
    }

    public void markDone() {
        this.mark = true;
        return;
    }

    public void markUndone() {
        this.mark = false;
        return;
    }

    public String getStatusIcon() {
        return (this.mark ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.val;
    }
}

class Todo extends Task {
    private final String tasktype = "T";


    public Todo(boolean mark, String val) {
        super(mark, val);
    }

    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString();
    }
}

class Deadline extends Task {
    private String date;
    private final String tasktype = "D";

    public Deadline(boolean mark, String val, String date){
        super(mark, val);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString() + " (by: " + this.date + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;
    private final String tasktype = "E";

    public Event(boolean mark, String val, String from, String to) {
        super(mark, val);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}

class TimoException extends Exception {

    public TimoException(String errorMessage) {
        super(errorMessage);
    }

}



public class Timo{
    public static void main(String[] args) throws TimoException {
        //greet
        System.out.println("----------------------------");
        System.out.println("Hello! I'm Timo\nWhat can I do for you?");
        System.out.println("----------------------------");

        //Scanner to receive input
        Scanner echo = new Scanner(System.in);

        //initialise array to store the values
        List<Task> arr = new ArrayList<Task>();


        String input = "";

        //boolean to know whether input = bye
        boolean is_bye = false;
        while (!is_bye) {
            input = echo.nextLine();
            //determine actions based on input
            switch (input) {
                case "bye":
                    System.out.println("----------------------------");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("----------------------------");
                    is_bye = true;
                    break;

                case "list":
                    System.out.println("----------------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= arr.size(); i++) {
                        Task chosen = arr.get(i - 1);
                        System.out.println(i + ". " + chosen);
                    }
                    System.out.println("----------------------------");
                    break;

                default:
                    if (input.startsWith("mark")) {
                        //to mark
                        String num = String.valueOf(input.charAt(input.length() - 1));
                        int target = Integer.parseInt(num);
                        System.out.println(target);
                        Task chosen = arr.get(target - 1);
                        chosen.markDone();
                        System.out.println("----------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(chosen);
                        System.out.println("----------------------------");
                    } else if (input.startsWith("unmark")) {
                        //to unmark
                        String num = String.valueOf(input.charAt(input.length() - 1));
                        int target = Integer.parseInt(num);
                        Task chosen = arr.get(target - 1);
                        chosen.markUndone();
                        System.out.println("----------------------------");
                        System.out.println("Nice! I've marked this task as not done yet:");
                        System.out.println(chosen);
                        System.out.println("----------------------------");
                    } else if (input.startsWith("todo")) {
                        //have an array that splits the input by space to obtain task
                        try {
                            String[] tmp = input.split(" ", 2);
                            if (tmp.length != 2) {
                                throw new TimoException("Usage todo: todo <task> (need argument)");
                            }
                            Todo task = new Todo(false, tmp[1]);
                            arr.add(task);
                            System.out.println("----------------------------");
                            System.out.println("Got it. I've added this task:");
                            System.out.println(task.toString());
                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
                            System.out.println("----------------------------");
                        } catch (TimoException ex) {
                            System.out.println("----------------------------");
                            System.out.println(ex);
                            System.out.println("----------------------------");
                        }
                    } else if (input.startsWith("deadline")) {
                        String[] tmp = input.split("deadline |/by ");
                        Deadline task = new Deadline(false, tmp[1], tmp[2]);
                        arr.add(task);
                        System.out.println("----------------------------");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        System.out.println("----------------------------");
                    } else if (input.startsWith("event")) {
                        String[] tmp = input.split("event |/from |/to ");
                        Event task = new Event(false, tmp[1], tmp[2], tmp[3]);
                        arr.add(task);
                        System.out.println("----------------------------");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        System.out.println("----------------------------");
                    } else if (input.startsWith("delete")) {
                        String num = String.valueOf(input.charAt(input.length() - 1));
                        int target = Integer.parseInt(num);
                        Task task = arr.get(target - 1);
                        arr.remove(target - 1);
                        System.out.println("----------------------------");
                        System.out.println("Got it. I've removed this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        System.out.println("----------------------------");

                    } else {
                        try {
                            throw new TimoException("I'm sorry, I do not know what that means! Please try again with a different command!");
                        } catch (TimoException ex) {
                            System.out.println("----------------------------");
                            System.out.println(ex);
                            System.out.println("----------------------------");
                        }
                    }
            }
        }
    }
}
