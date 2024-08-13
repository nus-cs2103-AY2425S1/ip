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
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]);
                items.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(items.get(index - 1));
            } else if (input.startsWith("unmark")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]);
                items.get(index - 1).unMark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(items.get(index - 1));
        } else {
                Task t = new Task("-1");

                if (input.startsWith("deadline")) {
                    String[] words = input.split(" /");
                    String task = words[0];
                    String deadline = words[1];
                    t = new Deadline(task, deadline);
                } else if (input.startsWith("event")) {
                    String[] words = input.split(" /");
                    String task = words[0];
                    String to = words[1];
                    String from = words[2];
                    t = new Event(task, to, from);
                } else if (input.startsWith("todo")) {
                    String[] words = input.split(" /");
                    String todo = words[0];
                    t =  new ToDo(todo);
                }
                items.add(t);
                System.out.println("added: " + input);
                System.out.println();
                System.out.println("Now you have " + items.size() + " tasks in the list");
            }
            }

        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(goodbye);
    }
}
