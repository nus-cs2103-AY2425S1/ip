import java.util.Scanner;

public class Meow {
    private final String name = "Meow";
    private final String[] tasks = new String[100];
    private int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Meow MEOW = new Meow();

        System.out.println("_____________________________________________________________\n" +
                " Hello! I'm " + MEOW.name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < MEOW.taskCount; i++) {
                    System.out.println((i + 1) + ". " + MEOW.tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                if (MEOW.taskCount < 100) {
                    MEOW.tasks[MEOW.taskCount] = input;
                    MEOW.taskCount++;
                    System.out.println("added: " + input);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("Task list is full!");
                }
            }
        }
    }
}