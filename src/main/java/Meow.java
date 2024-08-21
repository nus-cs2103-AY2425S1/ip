import java.util.Scanner;

public class Meow {
    private final String name = "Meow";
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;

    private void line() {
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Meow MEOW = new Meow();

        System.out.println("_____________________________________________________________\n" +
                " Hello! I'm " + MEOW.name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            String input = scanner.nextLine();
            MEOW.line();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                MEOW.line();
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < MEOW.taskCount; i++) {
                    System.out.println((i + 1) + "." + MEOW.tasks[i]);
                }
                MEOW.line();
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5).trim()) - 1;
                if (index >= 0 && index < MEOW.taskCount) {
                    MEOW.tasks[index].mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(MEOW.tasks[index]);
                } else {
                    System.out.println("Invalid task number.");
                }
                MEOW.line();
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7).trim()) - 1;
                if (index >= 0 && index < MEOW.taskCount) {
                    MEOW.tasks[index].unMark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(MEOW.tasks[index]);
                } else {
                    System.out.println("Invalid task number.");
                }
                MEOW.line();
            } else {
                MEOW.tasks[MEOW.taskCount] = new Task(input);
                MEOW.taskCount++;
                System.out.println("added: " + input);
                MEOW.line();
            }

        }
    }
}