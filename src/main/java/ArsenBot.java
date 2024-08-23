import java.util.Scanner;

public class ArsenBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ArsenBot");
        System.out.println("What can I do for you?\n");

        Task[] mem = new Task[105];
        int cnt = 0;
        Scanner reader = new Scanner(System.in);
        while (true) {
            String input = reader.nextLine();
            String[] parts = input.split(" ");

            if (parts[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }

            if (parts[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= cnt; i ++) {
                    System.out.println(i + "." + mem[i]);
                }
                continue;
            }

            if (parts[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int pos = Integer.parseInt(parts[1]);
                mem[pos].markAsDone();
                System.out.println(mem[pos]);
                continue;
            }

            if (parts[0].equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int pos = Integer.parseInt(parts[1]);
                mem[pos].markAsUndone();
                System.out.println(mem[pos]);
                continue;
            }

            mem[++ cnt] = new Task(input);
            System.out.println("added: " + input);
        }
    }
}
