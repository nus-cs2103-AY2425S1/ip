import java.util.Scanner;

public class ArsenBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ArsenBot");
        System.out.println("What can I do for you?\n");

        String[] mem = new String[105];
        int cnt = 0;
        Scanner reader = new Scanner(System.in);
        while (true) {
            String input = reader.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }

            if (input.equals("list")) {
                for (int i = 1; i <= cnt; i ++) {
                    System.out.println(i + ". " + mem[i]);
                }
                continue;
            }

            mem[++ cnt] = input;
            System.out.println("added: " + input);
        }
    }
}
