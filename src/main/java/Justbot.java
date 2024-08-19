import java.util.Scanner;

public class Justbot {
    public static void main(String[] args) {
        final String chatbotName = "JustBot";
        String[] tasks = new String[100];
        int tasksIndex = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------");
        System.out.println("Hello I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("------------------------------------------");
                break;
            }

            if (input.equals("list")) {
                System.out.println("------------------------------------------");
                for(int i =0; i < tasksIndex; i++) {
                    int taskListCount = i + 1;
                    System.out.print(taskListCount + ". " + tasks[i] + "\n");
                }
                System.out.println("------------------------------------------");
                continue;
            }
            System.out.println("------------------------------------------");
            tasks[tasksIndex] = input;
            tasksIndex += 1;
            System.out.println( "added: " + input);
            System.out.println("------------------------------------------");
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");

    }

}
