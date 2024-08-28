import java.util.Scanner;

public class Opus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Hello! I'm Opus");
        System.out.println(" What can I do for you?");

        String[] tasks = new String[100];
        int taskCount = 0;

        while(true) {
            String s = scanner.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount++] = s;
                System.out.println("added: " + s);
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
    }
}

