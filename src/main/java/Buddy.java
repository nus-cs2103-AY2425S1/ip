import java.util.Scanner;

public class Buddy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int tracker = 0;
        System.out.println("    ____________________________________");
        System.out.println("    Hey there! I'm Buddy\n    What do ya need help with?");
        System.out.println("    ____________________________________\n");

        while (true) {
            String userInput = scanner.nextLine().trim();

            System.out.println("\n    ____________________________________");

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    Bye! See ya soon!");
                System.out.println("    ____________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                if (tracker == 0) {
                    System.out.println("    List is empty!!");
                } else {
                    System.out.println("    Here are the tasks in your list: ");
                    for (int i = 0; i < tracker; i++) {
                        System.out.printf("    %d. [%s] %s%n", i+1, list[i].getStatusIcon(),list[i].description);
                    }
                }


            } else if (userInput.startsWith("mark ") && userInput.matches("mark \\d+$")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;

                if (taskIndex >= 0 && taskIndex < tracker) {
                    System.out.println("    Nice one buddy! Marked this as done...");
                    list[taskIndex].markAsDone();
                    System.out.printf("    [%s] %s%n", list[taskIndex].getStatusIcon(),list[taskIndex].description);
                } else {
                    System.out.println("    I don't think task is on your list buddy...");
                }
            } else if (userInput.startsWith("unmark ") && userInput.matches("unmark \\d+$")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;

                if (taskIndex >= 0 && taskIndex < tracker) {
                    System.out.println("    Alright buddy, let's give that task another shot!");
                    list[taskIndex].markAsUndone();
                    System.out.printf("    [%s] %s%n", list[taskIndex].getStatusIcon(),list[taskIndex].description);
                } else {
                    System.out.println("I don't think task is on your list buddy...");
                }

            } else if (userInput.isBlank()) {
                System.out.println("    Not too sure about that, buddy");
            }

            else {
                Task t = new Task(userInput);
                list[tracker] = t;
                tracker++;

                System.out.println("    added: " + t.description);

            }
            System.out.println("    ____________________________________\n");
        }
        scanner.close();

    }
}
