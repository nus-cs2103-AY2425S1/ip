import java.util.ArrayList;
import java.util.Scanner;

public class ChatBaby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        greet();

        String input = scanner.nextLine();
        while (true) {
            if (input.equals("bye")) {
                bye();
                break;
            }

            else if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
                System.out.println("\n____________________________________________________________");
            }

            else if (input.length() >= 6 && input.substring(0, 4).equals("mark")) {
                tasks.get(Integer.parseInt(input.substring(5)) - 1).markAsDone();
                System.out.println("____________________________________________________________\n"
                        + "Nice! I've marked this task as done:\n"
                        +  tasks.get(Integer.parseInt(input.substring(5)) - 1).toString());
                System.out.println();
            }

            else if (input.length() >= 8 && input.substring(0, 6).equals("unmark")) {
                tasks.get(Integer.parseInt(input.substring(7)) - 1).unMarkAsDone();
                System.out.println("____________________________________________________________\n"
                        + "OK, I've marked this task as not done yet:\n"
                        +  tasks.get(Integer.parseInt(input.substring(7)) - 1).toString());
                System.out.println();
            }

            else {
                tasks.add(new Task(input));
                System.out.println("____________________________________________________________\n"
                        + "added: " + input + "\n"
                        + "____________________________________________________________");
            }

            input = scanner.nextLine();
        }
    }

    public static void greet() {
        System.out.println("____________________________________________________________\n"
                + "Hello! I'm ChatBaby\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
    }

    public static void bye() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________");
    }

    public static void echo(String input) {
        System.out.println("____________________________________________________________\n"
                + input + "\n"
                + "____________________________________________________________");
    }
}
