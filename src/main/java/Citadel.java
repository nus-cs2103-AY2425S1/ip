import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Citadel {

    public static List<Task> items = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = "Citadel";
        String intro = "Hello! I'm " + name + "\n";
        String question = "What can I do for you?\n";

        String input = "";

        String start = intro + question;
        System.out.println(start);

        while(!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
            } else if (input.startsWith("mark")) {
                mark(input);
            } else if (input.startsWith("unmark")) {
                unmark(input);
            } else {
                if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else if (input.startsWith("todo")) {
                    handleTodo(input);
                }

            }
            }

        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(goodbye);
    }

    private static void unmark(String input) {
        String[] words = input.split(" ");
        int index = Integer.parseInt(words[1]);
        items.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(items.get(index - 1));
    }

    private static void mark(String input) {
        String[] words = input.split(" ");
        int index = Integer.parseInt(words[1]);
        items.get(index - 1).unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(items.get(index - 1));
    }

    private static void handleDeadline(String input) {
        Task t;
        String[] words = input.split(" /");
        String task = words[0];
        String deadline = words[1];
        t = new Deadline(task, deadline);
        items.add(t);
        System.out.println("added: " + input);
        System.out.println();
        System.out.println("Now you have " + items.size() + " tasks in the list");
    }

    private static void handleEvent(String input) {
        Task t;
        String[] words = input.split(" /");
        String task = words[0];
        String to = words[1];
        String from = words[2];
        t = new Event(task, to, from);
        items.add(t);
        System.out.println("added: " + input);
        System.out.println();
        System.out.println("Now you have " + items.size() + " tasks in the list");
    }

    private static void handleTodo(String input) {
        Task t;
        String[] words = input.split(" /");
        String todo = words[0];
        t =  new ToDo(todo);
        items.add(t);
        System.out.println("added: " + input);
        System.out.println();
        System.out.println("Now you have " + items.size() + " tasks in the list");
    }
}
