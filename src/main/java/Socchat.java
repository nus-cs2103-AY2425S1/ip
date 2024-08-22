import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Socchat {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        greet();

        while (true) {
            try {
                String input = scanner.next();
                System.out.print("> ");
                if (input.equals("bye")) {
                    exit();
                    break;
                } else if (input.equals("list")) {
                    list(tasks);
//                    scanner.nextLine();
                } else if (input.equals("mark")) {
                    setMark(tasks, true);

                } else if (input.equals("unmark")) {
                    setMark(tasks, false);
                } else {
                    String str = input + scanner.nextLine();
                    String[] strToken = str.split(" /");
                    Task t;
                    String des;
                    if (input.equals("todo")) {
                        addTodo(tasks, strToken);
                    } else if (input.equals("event")) {
                        addEvent(tasks, strToken);
                    } else if (input.equals("deadline")) {
                        addDeadline(tasks, strToken);
                    } else {
                        throw new SocchatException("Uh Ohh! Socchat does not understand this...");
                    }
                }
            } catch (SocchatException e) {
                    System.out.println(e.getMessage());
                }
            }

        }

    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void setMark(ArrayList<Task> tasks, Boolean mark) {
        try {
            String taskIndexString = scanner.nextLine().trim();
            int taskIndex = Integer.parseInt(taskIndexString);
            if (mark) {
                tasks.get(taskIndex - 1).mark();
            } else {
                tasks.get(taskIndex - 1).unmark();
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task index.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }
    }
    public static void list(ArrayList<Task> tasks) {
        System.out.println("Your task list:");
        for(int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.print((i + 1) + ": ");
            System.out.println(curr.toString());
        }
    }
    public static void addTodo(ArrayList<Task> tasks, String[] strToken) throws SocchatException {
        try {
            String des = strToken[0].substring("todo ".length());
            Task t = new Todo(des);
            tasks.add(t);
            System.out.print("added: ");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " task(s).");
        } catch (IndexOutOfBoundsException e) {
            // scanner.nextLine();
            throw new SocchatException("Invalid Todo format: Description is empty");

        }
    }
    public static void addEvent(ArrayList<Task> tasks, String[] strToken) throws SocchatException {
        try {
            String des = strToken[0].substring("event ".length());
            String from = strToken[1].substring("from ".length());
            String to = strToken[2].substring("to ".length());

            Task t = new Event(des, from, to);
            tasks.add(t);
            System.out.print("added: ");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " task(s).");
        } catch (IndexOutOfBoundsException e) {

            throw new SocchatException("Invalid Event format: event <description> /from <startTime> /to <endTime>");
        }
    }
    public static void addDeadline(ArrayList<Task> tasks, String[] strToken) throws SocchatException {
        try {
            String des = strToken[0].substring("deadline ".length());
            String by = strToken[1].substring("by ".length());
            Task t = new Deadline(des, by);
            tasks.add(t);
            System.out.print("added: ");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " task(s).");
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Deadline format: deadline <description> /by <deadline>");
        }

    }

}

