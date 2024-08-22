import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Socchat {
    private static Scanner scanner = new Scanner(System.in);
    enum Command{
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE
    }
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        greet();

        while (true) {
            String[] strToken;
            try{
                String input = scanner.next();
                Command command = getCommand(input);
                System.out.print("> ");
                switch (command) {
                    case BYE:
                        exit();
                        break;
                    case LIST:
                        list(tasks);
                        break;
                    case MARK:
                        setMark(tasks, true);
                        break;
                    case UNMARK:
                        setMark(tasks, false);
                        break;
                    case TODO:
                        strToken = stringTokenize("TODO");
                        addTodo(tasks, strToken);
                        break;
                    case DEADLINE:
                        strToken = stringTokenize("DEADLINE");
                        addDeadline(tasks, strToken);
                        break;
                    case EVENT:
                        strToken = stringTokenize("EVENT");
                        addEvent(tasks, strToken);
                        break;
                    case DELETE:
                        delete(tasks);
                        break;
                }
            } catch (SocchatException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    public static Command getCommand(String input) throws SocchatException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SocchatException("Uh Ohh! Socchat does not understand this...");
        }

    }
    public static String[] stringTokenize(String command) {
        String str = command + scanner.nextLine();
        String[] strToken = str.split(" /");
        return strToken;
    }

    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void setMark(ArrayList<Task> tasks, Boolean mark) throws SocchatException {
        try {
            String taskIndexString = scanner.nextLine().trim();
            int taskIndex = Integer.parseInt(taskIndexString);
            if (mark) {
                tasks.get(taskIndex - 1).mark();
            } else {
                tasks.get(taskIndex - 1).unmark();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid task number.");
        } catch (NumberFormatException e) {
            throw new SocchatException("Please enter a valid task number.");
        }
    }
    public static void delete(ArrayList<Task> tasks) throws SocchatException {
        try {
            String taskIndexString = scanner.nextLine().trim();
            int taskIndex = Integer.parseInt(taskIndexString);
            Task task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            System.out.println("Deleted "  + "\"" +  task.toString() + "\"");
            System.out.println("Now you have " + tasks.size() + " task(s).");
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid task number.");
        } catch (NumberFormatException e) {
            throw new SocchatException("Please enter a valid task number.");
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

