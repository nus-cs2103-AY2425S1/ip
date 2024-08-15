import java.util.Scanner;
import java.util.ArrayList;
import exception.CitadelException;
import exception.CitadelInvalidArgException;
import exception.CitadelInvalidCommandException;
import exception.CitadelTaskNoInput;

public class Citadel {
    public static ArrayList<Task> items = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = "Citadel";
        String intro = "Hello! I'm " + name + "\n";
        String question = "What can I do for you?\n";

        String input;

        String start = intro + question;
        System.out.println(start);

        while (true) {
            try {
                input = scanner.nextLine();
                String command = input.split(" ")[0].toUpperCase();
                if (Commands.valueOf(command).equals(Commands.BYE)) {
                    break;
                }
                if (Commands.valueOf(command).equals(Commands.LIST)) {
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println((i + 1) + ". " + items.get(i));
                    }
                } else if (Commands.valueOf(command).equals(Commands.MARK)) {
                    mark(input);
                } else if (Commands.valueOf(command).equals(Commands.UNMARK)) {
                    unmark(input);
                } else if (Commands.valueOf(command).equals(Commands.DELETE)) {
                    delete(input);
                } else {
                    if (Commands.valueOf(command).equals(Commands.DEADLINE)) {
                        handleDeadline(input);
                    } else if (Commands.valueOf(command).equals(Commands.EVENT)) {
                        handleEvent(input);
                    } else if (Commands.valueOf(command).equals(Commands.TODO)) {
                        handleTodo(input);
                    } else {
                        throw new CitadelInvalidCommandException();
                    }
                }

            } catch (CitadelException e) {
                System.out.println(e.toString());
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
            }

        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(goodbye);
    }

    private static void mark(String input) throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            items.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(items.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }

    private static void unmark(String input) throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            items.get(index - 1).unMark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(items.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
                throw new CitadelInvalidArgException();
        }
    }

    private static void delete(String input) throws CitadelException {
        try {
            //todo finish this
        String[] words = input.split(" ");
        int index = Integer.parseInt(words[1]);
        Task t = items.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + items.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
        }

    private static void handleDeadline(String input) throws CitadelException {
        Task t;
        String[] words = input.split(" /by ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }
        String task = words[0].substring(9).trim();
        String deadline = words[1].trim();

        if (task.isEmpty() || deadline.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        t = new Deadline(task, deadline);
        items.add(t);
        System.out.println("Got it! I have added: " + t);
        System.out.println();
        System.out.println("Now you have " + items.size() + " tasks in the list");
    }

    private static void handleEvent(String input) throws CitadelException {
        Task t;
        String[] words = input.split(" /from ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String task = words[0].substring(5).trim();
        String[] timeline = words[1].split(" /to ");

        if (timeline.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String from = timeline[0].trim();
        String to = timeline[1].trim();

        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        t = new Event(task, from, to);
        items.add(t);

        System.out.println("Got it! I have added: " + t);
        System.out.println();
        System.out.println("Now you have " + items.size() + " tasks in the list");
    }

    private static void handleTodo(String input) throws CitadelException {
            Task t;
            String todo = input.substring(5).trim();
            if (todo.isEmpty()) {
                throw new CitadelTaskNoInput();
            }
            t = new ToDo(todo);
            items.add(t);
            System.out.println("Got it! I have added: " + t);
            System.out.println("Now you have " + items.size() + " tasks in the list");
    }
}
