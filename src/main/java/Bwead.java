import java.util.ArrayList;
import java.util.Scanner;

public class Bwead {

    public static String name = "Bwead";
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Task> texts = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello from " + name + "!");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printlist();
            } else if (input.startsWith("mark")) {
                int toadd = Integer.valueOf(input.split(" ")[1]);
                Task task = texts.get(toadd - 1);
                task.done = true;
                System.out.println("Nice! I've marked this task as done: " + task.text);
            } else if (input.startsWith("unmark")) {
                int toadd = Integer.valueOf(input.split(" ")[1]);
                Task task = texts.get(toadd - 1);
                task.done = false;
                System.out.println("OK, I've marked this task as not done yet: " + task.text);
            } else {
                texts.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }

    public static void printlist() {
        for (int i = 1; i <= texts.size(); i++) {
            String done;
            Task task = texts.get(i-1);
            System.out.println(i + "." + task.toString());
        }
    }

}
